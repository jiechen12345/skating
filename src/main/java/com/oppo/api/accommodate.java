package com.oppo.api;

import com.oppo.Entity.Customer;
import com.oppo.Entity.Project;
import com.oppo.business.BookService;
import com.oppo.dao.CustomerDao;
import com.oppo.dao.ProjectDao;
import com.oppo.dto.BookPage;
import com.oppo.dto.ProjectDto;
import com.oppo.request.BookReq;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
public class accommodate {
    Logger LOGGER = LoggerFactory.getLogger(accommodate.class);
    @Autowired
    private ProjectDao projectDao;
    @Autowired
    private CustomerDao customerDao;
    @Autowired
    private BookService bookService;
    Integer[] pageSizeOption = {5, 10, 15, 20};
    BookReq bookReq = new BookReq();

    //查詢分頁會員列表及修改pageSize
    @GetMapping("/accommodate")
    public String findAll(Model model) {
        //BookReq bookReq = new BookReq(q_id, q_id2, q_amt, q_amt2, q_invYM, q_invYM2, q_paidDat, q_paidDat2, q_incomeOrExpend, q_invNo, q_customerId, q_projectId, q_invoice, q_paid, q_description);
        return "accommodate";
    }
}
