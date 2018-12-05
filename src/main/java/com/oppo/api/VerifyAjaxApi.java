package com.oppo.api;

import java.util.Properties;

import ch.qos.logback.classic.gaffer.PropertyUtil;
import com.oppo.Entity.Enrollment;
import com.oppo.Entity.PreOrder;
import com.oppo.Entity.Sessions;
import com.oppo.Entity.Status;
import com.oppo.common.MailUtil;
import com.oppo.dao.EnrollmentDao;
import com.oppo.dao.PreorderDao;
import com.oppo.dao.SessionsDao;
import com.oppo.dao.StatusDao;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailSender;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;


import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.Properties;
import java.io.File;
import javax.mail.Authenticator;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;


/**
 * Created by JieChen on 2018/11/19.
 */
@RestController
@RequestMapping(value = "/verify", produces = "application/json")
public class VerifyAjaxApi {
    Logger LOGGER = LoggerFactory.getLogger(VerifyAjaxApi.class);
    @Value("${upload.uploadingdir}")
    String uploadingdir;
    @Autowired
    private PreorderDao preorderDao;
    @Autowired
    private StatusDao statusDao;
    @Autowired
    private SessionsDao sessionsDao;
    @Autowired
    private EnrollmentDao enrollmentDao;
    @Autowired
    MailUtil mailUtil;

    @RequestMapping(value = "/execute", method = RequestMethod.PUT)
    public void verifyExecute(@RequestBody String ids) {
        //System.out.println(ids);
        String[] idArr = ids.split(",");
        if (idArr.length > 0 && "1".equals(idArr[0])) {
            Status status = statusDao.findById(5).get();
            for (int i = 1; i < idArr.length; i++) {
                PreOrder preOrder = preorderDao.findById(idArr[i]).get();
                 if ((preOrder.getVerifyTime() == null || "".equals(preOrder.getVerifyTime()) && preOrder.getStatus().getId() < 4)) {
                preOrder.setVerifyTime(DateTime.now().toDate());
                preOrder.setStatus(status);
                preorderDao.save(preOrder);
                String sessionId = preOrder.getSessions().getId().toString();
                    for (int j = 1; j <= preOrder.getGroupNum(); j++) {
                        saveEnrollment(sessionId, preOrder);
                    }
                //寄信
                sendMail(preOrder,1);
                 }
            }
        } else if (idArr.length > 0 && "2".equals(idArr[0])) { //不通過
            Status status = statusDao.findById(4).get();
            for (int i = 1; i < idArr.length; i++) {
                PreOrder preOrder = preorderDao.findById(idArr[i]).get();
                if ((preOrder.getVerifyTime() == null || "".equals(preOrder.getVerifyTime())) && preOrder.getStatus().getId() < 4) {
                    preOrder.setVerifyTime(DateTime.now().toDate());
                    preOrder.setStatus(status);
                    preorderDao.save(preOrder);
                    Sessions sessions = preOrder.getSessions();
                    sessions.setReserved(sessions.getReserved() - preOrder.getGroupNum());
                    sessionsDao.saveAndFlush(sessions);
                    delDir(new File(uploadingdir, preOrder.getId()));
                    sendMail(preOrder,2);
                }
            }
        }

    }

    public void delDir(File path) {
        if (!path.exists()) {
            return;
        }
        if (path.isFile()) {
            path.delete();
            return;
        }
        File[] files = path.listFiles();
        for (int i = 0; i < files.length; i++) {
            delDir(files[i]);
        }
        path.delete();
    }

    //註冊已成功預約的號碼牌排序
    public void saveEnrollment(String sessionId, PreOrder preOrder) {
        try {
            Enrollment enrollment = enrollmentDao.findFirstByIdStartingWithOrderByIdDesc(sessionId);
            if (enrollment != null && enrollment.getId().length() == 6) {
                LOGGER.info(enrollment.getId());
                Long maxId = Long.parseLong(enrollment.getId());
                maxId = maxId + 1;
                enrollmentDao.saveAndFlush(new Enrollment(maxId.toString(), preOrder, false, false));
            } else {
                enrollmentDao.saveAndFlush(new Enrollment(sessionId + "001", preOrder, false, false));
            }
        } catch (Exception e) {
            LOGGER.info(e.toString());
        }
    }

    public void sendMail(PreOrder preOrder,Integer type) {
        String to = preOrder.getApplicantEmail();
        if(type==1){
            mailUtil.sendSimpleMail(to, "審核通過", "恭喜");
        }else if(type==2){
            mailUtil.sendSimpleMail(to, "審核失敗", "GG");
        }

    }

}
