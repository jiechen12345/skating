package com.oppo.api;

import com.oppo.Entity.Accommodate;
import com.oppo.Entity.Holiday;
import com.oppo.Entity.Project;
import com.oppo.business.BookService;
import com.oppo.dao.AccommodateDao;
import com.oppo.dao.HolidayDao;
import com.oppo.dao.ProjectDao;
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
    //@Value("${holiday.defaultTitle}")
    private String defaultTitle;
    //假日代號
    private String holidayFlag="H";

    @RequestMapping(value = "/load", method = RequestMethod.POST)
    public List<Holiday> load(@RequestBody HolidayReq holidayReq) {
        System.out.println(holidayReq.toString());
        //bookService.create(bookReq);
        List<Holiday> holidays = holidayDao.findAll((root, query, cb) -> {
            query.orderBy(cb.asc(root.get("holidat")));
            List<Predicate> predicates = new LinkedList<>();
            Optional.ofNullable(holidayReq.getStartDat()).filter(it -> !it.isEmpty()).ifPresent(startDat -> {
                predicates.add(cb.greaterThanOrEqualTo(root.get("holidat"), (startDat)));
            });
            Optional.ofNullable(holidayReq.getEndDat()).filter(it -> !it.isEmpty()).ifPresent(endDat -> {
                predicates.add(cb.lessThanOrEqualTo(root.get("holidat"), (endDat)));
            });

            return cb.and(predicates.toArray(new Predicate[predicates.size()]));
        });
        return holidays;
//        Holiday holiday= new Holiday("2018-11-02");
//        Holiday holiday2= new Holiday("2018-11-12");
//        list.add(holiday);
//        list.add(holiday2);
        // return list;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Holiday add(@RequestBody Date addDat) {
        String title = "假日";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = sdf.format(addDat);
        if (defaultTitle != null && !"".equals(defaultTitle)) title = defaultTitle;
        Accommodate accommodate= accommodateDao.findById(holidayFlag).get();
        Holiday holiday = new Holiday(dateString, title,accommodate);
        holidayDao.save(holiday);
        return holiday;

    }

    @RequestMapping(value = "/remove", method = RequestMethod.DELETE)
    public void remove(@RequestBody Date removeDat) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = sdf.format(removeDat);
        Holiday holiday = holidayDao.findById(dateString).get();
        holidayDao.delete(holiday);
    }


}
