package com.oppo.api;

import com.oppo.Entity.Customer;
import com.oppo.Entity.Departemt;
import com.oppo.business.MemberService;
import com.oppo.business.VerfyService;
import com.oppo.dao.DepartmentDao;
import com.oppo.dto.*;
import com.oppo.request.BookReq;
import com.oppo.request.MemberReq;
import com.oppo.request.PreorderReq;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by JieChen on 2018/7/24.
 */
@Controller
public class VerifyApi {
    Logger LOGGER = LoggerFactory.getLogger(VerifyApi.class);
    @Autowired
    private DepartmentDao departmentDao;
    @Autowired
    private MemberService memberService;
    @Autowired
    private VerfyService verfyService;
    Integer[] pageSizeOption = {5, 10, 15, 20};
    PreorderReq preorderReq = new PreorderReq();
//    List<Integer> pageSizeOption=new ArrayList<Integer>()

    //查詢分頁會員列表及修改pageSize
    @GetMapping("/verify")
    public String findAll(@RequestParam(required = false, defaultValue = "1") Integer page,
                          @RequestParam(required = false, defaultValue = "5") Integer pageSize,
                          Model model) {
        preorderReq = new PreorderReq();
        LOGGER.info("findAll.page= " + page);
        LOGGER.info("findAll.pageSize= " + pageSize);
        PreorderPage preorderPage = verfyService.getAllForm(page, pageSize);
        List<ProjectDto> projectDtos = null; //for查詢用的ProjectDtoList
        model.addAttribute("preorders", preorderPage.getContents());
        model.addAttribute("indexPage", preorderPage.getCurrentPage());
        model.addAttribute("totalPages", preorderPage.getTotalPages());
        model.addAttribute("pageSize", pageSize);
        model.addAttribute("count", preorderPage.getCount());
        model.addAttribute("pageSizeOption", pageSizeOption);
        model.addAttribute("preorderReq", preorderReq);
        return "verify";
    }
    //查詢分頁會員列表及修改pageSize
    @GetMapping("/verifyChangePage")
    public String changePageSize(@RequestParam(required = false, defaultValue = "1") Integer page,
                                 @RequestParam(required = false, defaultValue = "5") Integer pageSize, Model model) {
        PreorderPage preorderPage = verfyService.getAllForm(page, pageSize);
        //List<MemberDto> memberDtoList = memberService.findAll();
        model.addAttribute("members", preorderPage.getContents());
        model.addAttribute("indexPage", preorderPage.getCurrentPage());
        model.addAttribute("totalPages", preorderPage.getTotalPages());
        model.addAttribute("pageSize", pageSize);
        model.addAttribute("count", preorderPage.getCount());
        model.addAttribute("pageSizeOption", pageSizeOption);
        return "verify";
    }

//    @RequestMapping(value = "/toAddMember", method = RequestMethod.GET)
//    public String toAddModal(Model model) throws IOException {
//        List<Departemt> departments = departmentDao.findAll();
//        model.addAttribute("depts", departments);
//        return "verify::addModalContens";
//
//    }

//    //新增會員
//    @RequestMapping(value = "/member", method = RequestMethod.POST)
//    public String AddModal(@RequestBody MemberReq memberReq, Model model) throws IOException {
//        LOGGER.info("**** " + memberReq.toString());
//        memberService.create(memberReq);
//        List<MemberDto> memberDtoList = memberService.findAll();
//        model.addAttribute("members", memberDtoList);
//        return "verify";
//        //return "redirect:/members.html";
//    }

//    @RequestMapping(value = "/toModifyMember/{id}", method = RequestMethod.GET)
//    public String toModifyModal(@PathVariable Integer id, Model model) throws IOException {
//        LOGGER.info("******** " + id.toString());
//        MemberDto memberDto = memberService.findOne(id);
//        List<Departemt> departments = departmentDao.findAll();
//        model.addAttribute("depts", departments);
//        model.addAttribute("memberDto", memberDto);
//        return "verify::modifyModalContens";
//
//    }

//    //修改會員
//    @RequestMapping(value = "/member", method = RequestMethod.PUT)
//    public String modifyMember(@RequestBody MemberReq memberReq, Model model) throws IOException {
//        LOGGER.info("***** " + memberReq.toString());
//        memberService.update(memberReq);
//        List<MemberDto> memberDtoList = memberService.findAll();
//        model.addAttribute("members", memberDtoList);
//        return "verify";
//        //return "redirect:/members.html";
//    }
//
//    @RequestMapping(value = "/member/{id}", method = RequestMethod.DELETE)
//    public String deleteMember(@PathVariable Integer id) {
//        memberService.delete(id);
//        //return "member/list";
//        return "redirect:/verify.html";
//    }
}
