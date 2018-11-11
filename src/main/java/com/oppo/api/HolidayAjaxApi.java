package com.oppo.api;

import com.oppo.Entity.Accommodate;
import com.oppo.Entity.Holiday;
import com.oppo.Entity.Project;
import com.oppo.Entity.Sessions;
import com.oppo.business.BookService;
import com.oppo.dao.AccommodateDao;
import com.oppo.dao.HolidayDao;
import com.oppo.dao.ProjectDao;
import com.oppo.dao.SessionsDao;
import com.oppo.dto.BookDto;
import com.oppo.dto.ProjectDto;
import com.oppo.request.BookReq;
import com.oppo.request.HolidayReq;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.w3c.tidy.Tidy;
import org.xhtmlrenderer.pdf.ITextRenderer;
import sun.util.calendar.BaseCalendar;

import javax.persistence.criteria.Predicate;
import java.io.*;
import java.nio.file.FileSystems;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

import static com.itextpdf.text.pdf.BaseFont.EMBEDDED;
import static com.itextpdf.text.pdf.BaseFont.IDENTITY_H;
import static org.thymeleaf.templatemode.TemplateMode.HTML;

/**
 * Created by JieChen on 2018/10/5.
 */

@RestController
@RequestMapping(value = "/holiday", produces = "application/json")
public class HolidayAjaxApi {
    Logger LOGGER = LoggerFactory.getLogger(HolidayAjaxApi.class);
    @Autowired
    private HolidayDao holidayDao;
    @Autowired
    private AccommodateDao accommodateDao;
    @Autowired
    private SessionsDao sessionsDao;
    @Value("${sessions.Holiday.name}")
    private String holidaySessionName;
    //假日代號
    @Value("${accommodate.normaldayFlag}")
    private String normaldayFlag;
    @Value("${sessions.Holiday.startTime}")
    String startTime = "14:00";
    @Value("${sessions.Holiday.endTime}")
    String endTime = "15:30";

    @RequestMapping(value = "/load", method = RequestMethod.POST)
    public List<Sessions> load(@RequestBody HolidayReq holidayReq) {
        //System.out.println(holidayReq.toString());
        //bookService.create(bookReq);
        List<Sessions> SessionsList = sessionsDao.findAll((root, query, cb) -> {
            //query.groupBy(root.get("dat"));
            query.orderBy(cb.asc(root.get("dat")));
            List<Predicate> predicates = new LinkedList<>();
            Optional.ofNullable(holidayReq.getStartDat()).filter(it -> !it.isEmpty()).ifPresent(startDat -> {
                predicates.add(cb.greaterThanOrEqualTo(root.get("dat"), (startDat)));
            });
            Optional.ofNullable(holidayReq.getEndDat()).filter(it -> !it.isEmpty()).ifPresent(endDat -> {
                predicates.add(cb.lessThanOrEqualTo(root.get("dat"), (endDat)));
            });

            return cb.and(predicates.toArray(new Predicate[predicates.size()]));
        });
        return SessionsList;

//        List<Sessions> list = new ArrayList<Sessions>();
//        Sessions s1 = new Sessions("2018-11-01",new Accommodate("H", 150));
//        Sessions s2 = new Sessions("2018-11-01",new Accommodate("H", 150));
//        list.add(s1);
//        list.add(s2);
//        return list;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Sessions add(@RequestBody Date addDat) {
        Sessions sessions;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = sdf.format(addDat);
        //List<Sessions> existSession=sessionsDao.findAllByDat(dateString);
        if (sessionsDao.findAllByDat(dateString) != null && sessionsDao.findAllByDat(dateString).size() < 4) {
            Accommodate accommodate = accommodateDao.findById(normaldayFlag).get();//人數還是以平日的為主
            //使追加值當天一致
            //Integer extra=existSession.get(0).getExtra();
            sessions = new Sessions(dateString, holidaySessionName, startTime, endTime, accommodate, 0, 0);
            sessionsDao.saveAndFlush(sessions);
        } else {
            sessions = new Sessions(9999);
        }
        return sessions;

    }

    @RequestMapping(value = "/remove", method = RequestMethod.DELETE)
    public Boolean remove(@RequestBody Date removeDat) {
        Boolean flag = false;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = sdf.format(removeDat);
        List<Sessions> sessionsList = sessionsDao.findAllByDatOrderByStartTime(dateString);
        if (sessionsList != null && sessionsList.size() == 4) {
            System.out.print(sessionsList);
            Sessions sessions = sessionsList.get(0);
            sessionsDao.delete(sessions);
            flag = true;
        }
        return flag;
    }


}
