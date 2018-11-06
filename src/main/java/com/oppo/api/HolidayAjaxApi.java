package com.oppo.api;

import com.oppo.Entity.Project;
import com.oppo.business.BookService;
import com.oppo.dao.ProjectDao;
import com.oppo.dto.BookDto;
import com.oppo.dto.ProjectDto;
import com.oppo.request.BookReq;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.w3c.tidy.Tidy;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.*;
import java.nio.file.FileSystems;
import java.util.ArrayList;
import java.util.List;
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


    @RequestMapping(value = "/load", method = RequestMethod.POST)
    public List<ProjectDto> load(@RequestBody String date) {
        System.out.println(date.toString());
        //bookService.create(bookReq);
        List list= new ArrayList<ProjectDto>();
        ProjectDto projectDto= new ProjectDto(1,"2018-11-02");
        ProjectDto projectDto2= new ProjectDto(1,"2018-11-12");
        list.add(projectDto);
        list.add(projectDto2);
        return list;
    }




}
