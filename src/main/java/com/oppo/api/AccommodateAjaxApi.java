package com.oppo.api;

import com.oppo.Entity.Accommodate;
import com.oppo.Entity.Holiday;
import com.oppo.common.Common;
import com.oppo.dao.AccommodateDao;
import com.oppo.dao.HolidayDao;
import com.oppo.dto.MemberDto;
import com.oppo.request.AccommodateReq;
import com.oppo.request.MemberReq;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

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
    private HolidayDao holidayDao;
    //平日代號
    @Value("${accommodate.normaldayFlag}")
    private String normaldayFlag;
    //假日代號
    @Value("${accommodate.holidayFlag}")
    private String holidayFlag;
    //特別日的描述
    private String SpecialdayTitle="特";

    //存檔
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public void save(@RequestBody AccommodateReq accommodateReq) throws IOException {
        LOGGER.info("**** " + accommodateReq.toString());
        Accommodate normaldayAccommodate = accommodateDao.findById(normaldayFlag).get();
        normaldayAccommodate.setNum(Common.get(accommodateReq.getNormaldayNum()));
        accommodateDao.saveAndFlush(normaldayAccommodate);
        Accommodate holidayAccommodate = accommodateDao.findById(holidayFlag).get();
        holidayAccommodate.setNum(Common.get(accommodateReq.getHolidayNum()));
        accommodateDao.saveAndFlush(holidayAccommodate);
        //----特別日
        String spDat = Common.get(accommodateReq.getSpecialDat());
        Integer spNum = Common.get(accommodateReq.getSpecialDayNum());
        if (!"".equals(spDat) && 0 != spNum) {
            Accommodate SpecialdayAccommodate = new Accommodate(spDat, spNum);
            accommodateDao.saveAndFlush(SpecialdayAccommodate);
            //一併加入日曆
            Holiday holiday = new Holiday(spDat, SpecialdayTitle, SpecialdayAccommodate);
            holidayDao.save(holiday);
        }


        //return "redirect:/members.html";
    }
}
