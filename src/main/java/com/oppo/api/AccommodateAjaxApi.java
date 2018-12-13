package com.oppo.api;

import com.oppo.Entity.Accommodate;
import com.oppo.Entity.Sessions;
import com.oppo.annotation.Action;
import com.oppo.common.Common;
import com.oppo.dao.AccommodateDao;
import com.oppo.dao.HolidayDao;
import com.oppo.dao.SessionsDao;
import com.oppo.request.AccommodateReq;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

/**
 * Created by JieChen on 2018/11/9.
 */
@RestController
@RequestMapping(value = "/accommodate", produces = "application/json")
public class AccommodateAjaxApi {
    Logger LOGGER = LoggerFactory.getLogger(AccommodateAjaxApi.class);
    @Autowired
    private AccommodateDao accommodateDao;
    @Autowired
    private SessionsDao sessionsDao;
    //平日代號
    @Value("${accommodate.normaldayFlag}")
    private String normaldayFlag;
    //假日代號
    @Value("${accommodate.holidayFlag}")
    private String holidayFlag;
    //特別日的描述
    private String SpecialdayTitle = "特";

    //存檔
    @Action("AccommodateAjaxApi[save]")
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public void save(@RequestBody AccommodateReq accommodateReq) throws IOException {
        //LOGGER.info("**** " + accommodateReq.toString());
        Accommodate normaldayAccommodate = accommodateDao.findById(normaldayFlag).get();
        normaldayAccommodate.setNum(Common.get(accommodateReq.getNormaldayNum()));
        accommodateDao.saveAndFlush(normaldayAccommodate);
        Accommodate holidayAccommodate = accommodateDao.findById(holidayFlag).get();
        holidayAccommodate.setNum(Common.get(accommodateReq.getHolidayNum()));
        accommodateDao.saveAndFlush(holidayAccommodate);
        //----特別日
        String spDat = Common.get(accommodateReq.getSpecialDat());
        Integer spNum = Common.get(accommodateReq.getSpecialDayNum());
        Integer sessionsId = Common.get(accommodateReq.getSessionsId());
        if (!"".equals(spDat) && 0 != spNum && sessionsId != null) {
            if (sessionsId != 0) {
                Optional<Sessions> optional = sessionsDao.findById(sessionsId);
                if (optional != null) {
                    Sessions sessions = optional.get();
                    sessions.setExtra(spNum);
                    sessionsDao.saveAndFlush(sessions);
                }
            } else {
                //修改'當天所有'場次場次容納人數
                List<Sessions> sessionsList = sessionsDao.findAllByDat(spDat);
                for (Sessions sessions : sessionsList) {
                    sessions.setExtra(spNum);
                    sessionsDao.saveAndFlush(sessions);
                }
            }
        }
    }

    @RequestMapping(value = "/findSessionsBySpecialDat", method = RequestMethod.POST)
    @Action("AccommodateAjaxApi[findSessionsBySpecialDat]")
    public List<Sessions> findSessionsBySpecialDat(@RequestBody String dat) {
        List<Sessions> sessionsList = sessionsDao.findAllByDatOrderByStartTime(dat);
        return sessionsList;
//        JSONArray jsArr = JSONArray.fromObject(projects);
//        return jsArr;
    }
}
