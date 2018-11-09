package com.oppo.api;

import com.oppo.Entity.Accommodate;
import com.oppo.Entity.Customer;
import com.oppo.Entity.Holiday;
import com.oppo.Entity.Project;
import com.oppo.business.BookService;
import com.oppo.dao.AccommodateDao;
import com.oppo.dao.CustomerDao;
import com.oppo.dao.ProjectDao;
import com.oppo.dto.BookPage;
import com.oppo.dto.ProjectDto;
import com.oppo.request.BookReq;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by JieChen on 2018/10/2.
 */
@Controller
public class AccommodateApi {
    Logger LOGGER = LoggerFactory.getLogger(AccommodateApi.class);
    @Autowired
    private AccommodateDao accommodateDao;
    //平日代號
    @Value("${accommodate.normaldayFlag}")
    private String normaldayFlag;
    //假日代號
    @Value("${accommodate.holidayFlag}")
    private String holidayFlag;

    //帶入目前相關值
    @GetMapping("/accommodate")
    public String findAll(Model model) {
        Accommodate normaldayAccommodate = accommodateDao.findById(normaldayFlag).get();
        Accommodate holidayAccommodate = accommodateDao.findById(holidayFlag).get();
        model.addAttribute("normaldayNum", normaldayAccommodate.getNum());
        model.addAttribute("holidayNum", holidayAccommodate.getNum());
        return "accommodate";
    }

}
