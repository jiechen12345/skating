package com.oppo.api;

import com.oppo.Entity.Departemt;
import com.oppo.annotation.Action;
import com.oppo.business.MemberService;
import com.oppo.dao.DepartmentDao;
import com.oppo.dto.MemberDto;
import com.oppo.dto.MemberPage;
import com.oppo.request.MemberReq;
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
@CrossOrigin(origins = "*")
@Controller
public class MemberApi {
    @Autowired
    private DepartmentDao departmentDao;
    @Autowired
    private MemberService memberService;
    Integer[] pageSizeOption = {5, 10, 15, 20};

//    List<Integer> pageSizeOption=new ArrayList<Integer>()

    /*
        //查詢會員列表
        @GetMapping("/members")
        public String list( Model model) {
            int pageSize=3;
            MemberPage memberPage = memberService.getAllForm(1,pageSize);
            model.addAttribute("members", memberPage.getContents());
            model.addAttribute("indexPage", memberPage.getCurrentPage());
            model.addAttribute("totalPages", memberPage.getTotalPages());
            model.addAttribute("count", memberPage.getCount());
            model.addAttribute("pageSize", pageSize);
            return "member/list";

        }
    */
    //查詢分頁會員列表(暫無條件)
//    @GetMapping("/members/{page}")
//    public String getAllForms(@PathVariable(required = false) Integer page, Model model) {
//        int pageSize = 5;
//        MemberPage memberPage = memberService.getAllForm(page, pageSize);
//        //List<MemberDto> memberDtoList = memberService.findAll();
//        model.addAttribute("members", memberPage.getContents());
//        model.addAttribute("indexPage", memberPage.getCurrentPage());
//        model.addAttribute("totalPages", memberPage.getTotalPages());
//        model.addAttribute("pageSize", pageSize);
//        model.addAttribute("count", memberPage.getCount());
//        model.addAttribute("pageSizeOption", pageSizeOption);
//        return "member/list";
//
//    }

    //查詢分頁會員列表及修改pageSize
    @Action("MemberApi[changePageSize]")
    @GetMapping("/members")
    public String changePageSize(@RequestParam(required = false, defaultValue = "1") Integer page,
                                 @RequestParam(required = false, defaultValue = "5") Integer pageSize, Model model) {
        MemberPage memberPage = memberService.getAllForm(page, pageSize);
        //List<MemberDto> memberDtoList = memberService.findAll();
        model.addAttribute("members", memberPage.getContents());
        model.addAttribute("indexPage", memberPage.getCurrentPage());
        model.addAttribute("totalPages", memberPage.getTotalPages());
        model.addAttribute("pageSize", pageSize);
        model.addAttribute("count", memberPage.getCount());
        model.addAttribute("pageSizeOption", pageSizeOption);
        return "member/list";
    }

    @Action("MemberApi[toAddModal]")
    @RequestMapping(value = "/toAddMember", method = RequestMethod.GET)
    public String toAddModal(Model model) throws IOException {
        List<Departemt> departments = departmentDao.findAll();
        model.addAttribute("depts", departments);
        return "member/list::addModalContens";

    }

    //新增會員
    @Action("MemberApi[AddModal]")
    @RequestMapping(value = "/member", method = RequestMethod.POST)
    public String AddModal(@RequestBody MemberReq memberReq, Model model) throws IOException {
        memberService.create(memberReq);
        List<MemberDto> memberDtoList = memberService.findAll();
        model.addAttribute("members", memberDtoList);
        return "member/list";
        //return "redirect:/members.html";
    }

    @Action("MemberApi[toModifyModal]")
    @RequestMapping(value = "/toModifyMember/{id}", method = RequestMethod.GET)
    public String toModifyModal(@PathVariable Integer id, Model model) throws IOException {
        MemberDto memberDto = memberService.findOne(id);
        List<Departemt> departments = departmentDao.findAll();
        model.addAttribute("depts", departments);
        model.addAttribute("memberDto", memberDto);
        return "member/list::modifyModalContens";

    }

    //修改會員
    @Action("MemberApi[modifyMember]")
    @RequestMapping(value = "/member", method = RequestMethod.PUT)
    public String modifyMember(@RequestBody MemberReq memberReq, Model model) throws IOException {
        memberService.update(memberReq);
        List<MemberDto> memberDtoList = memberService.findAll();
        model.addAttribute("members", memberDtoList);
        return "member/list";
        //return "redirect:/members.html";
    }

    @Action("MemberApi[deleteMember]")
    @RequestMapping(value = "/member/{id}", method = RequestMethod.DELETE)
    public String deleteMember(@PathVariable Integer id) {
        memberService.delete(id);
        //return "member/list";
        return "redirect:/members.html";
    }
}
