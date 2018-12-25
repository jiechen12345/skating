package com.oppo.api;

import com.oppo.Entity.Departemt;
import com.oppo.Entity.Member;
import com.oppo.annotation.Action;
import com.oppo.business.MemberService;
import com.oppo.dao.DepartemtDao;
import com.oppo.dao.MemberDao;
import com.oppo.dto.MemberDto;
import com.oppo.dto.MemberPage;
import com.oppo.request.MemberReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Optional;


/**
 * Created by JieChen on 2018/7/24.
 */
@CrossOrigin(origins = "*")
@Controller
public class MemberApi {
    @Autowired
    private DepartemtDao departemtDao;
    @Autowired
    private MemberService memberService;
    Integer[] pageSizeOption = {5, 10, 15, 20};
    @Autowired
    private MemberDao memberDao;

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
            return "member";

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
//        return "member";
//
//    }

    //查詢分頁會員列表及修改pageSize
    @Action("MemberApi[changePageSize]")
    @GetMapping("/members")
    public String changePageSize(@RequestParam(required = false, defaultValue = "1") Integer page,
                                 @RequestParam(required = false, defaultValue = "5") Integer pageSize, Model model) {
        MemberPage memberPage = memberService.getAllForm(page, pageSize);
        List<MemberDto> memberDtoList = memberService.findAll();
//        model.addAttribute("members", memberPage.getContents());
        model.addAttribute("members", memberDtoList);
        model.addAttribute("indexPage", memberPage.getCurrentPage());
        model.addAttribute("totalPages", memberPage.getTotalPages());
        model.addAttribute("pageSize", pageSize);
        model.addAttribute("count", memberPage.getCount());
        model.addAttribute("pageSizeOption", pageSizeOption);
        return "member";
    }

    @Action("MemberApi[toAddModal]")
    @RequestMapping(value = "/toAddMember", method = RequestMethod.GET)
    public String toAddModal(Model model) throws IOException {
        List<Departemt> departments = departemtDao.findAll();
        model.addAttribute("depts", departments);
        return "member::addModalContens";

    }

    //新增會員
    @Action("MemberApi[AddModal]")
    @RequestMapping(value = "/member", method = RequestMethod.POST)
    public String AddModal(@RequestBody MemberReq memberReq, Model model) throws IOException {
        memberService.create(memberReq);
        List<MemberDto> memberDtoList = memberService.findAll();
        model.addAttribute("members", memberDtoList);
        return "member";
        //return "redirect:/members.html";
    }

    @Action("MemberApi[toModifyModal]")
    @RequestMapping(value = "/toModifyMember/{id}", method = RequestMethod.GET)
    public String toModifyModal(@PathVariable Integer id, Model model) throws IOException {
        MemberDto memberDto = memberService.findOne(id);
        List<Departemt> departments = departemtDao.findAll();
        model.addAttribute("depts", departments);
        model.addAttribute("memberDto", memberDto);
        return "member::modifyModalContens";

    }

    //修改會員
    @Action("MemberApi[modifyMember]")
    @RequestMapping(value = "/member", method = RequestMethod.PUT)
    public String modifyMember(@RequestBody MemberReq memberReq, Model model) throws IOException {
        memberService.update(memberReq);
        List<MemberDto> memberDtoList = memberService.findAll();
        model.addAttribute("members", memberDtoList);
        return "member";
        //return "redirect:/members.html";
    }


    @Action("MemberApi[deleteMember]")
    @RequestMapping(value = "/member/{id}", method = RequestMethod.DELETE)
    public String deleteMember(@PathVariable Integer id) {
        memberService.delete(id);
        //return "member";
        return "redirect:/members.html";
    }

    @Action("MemberApi[addOrder]")
//    @GetMapping("/addOrder")
    @RequestMapping(value = "/addOrder", method = RequestMethod.POST)
    public String addOrder(@RequestParam(required = false, defaultValue = "1") String newName, @RequestParam(required = false, defaultValue = "1") String newAccount,
                           @RequestParam(required = false, defaultValue = "1") String cPassword, @RequestParam(required = false, defaultValue = "1") String depId,
                           Model model) throws IOException {
        MemberReq memberReq = new MemberReq(newName, newAccount, cPassword, depId);
        System.out.println("***");


            Member member = new Member();
            member.setAccount(newAccount);
            member.setPassword(cPassword);
            member.setName(newName);
            Departemt departemt = departemtDao.findById(Integer.parseInt(depId)).get();
            member.setDepartemt(departemt);
            try {
                memberDao.save(member);
            } catch (Exception e) {
//                    LOGGER.error(e.toString());
                memberDao.delete(member);
                model.addAttribute("errMsg", "系統發生異常請再嘗試，或者洽系統相關人員!");
            }
        return "redirect:/members";
    }

    public Boolean checkAccount(String account) {
        Boolean flag = false;
        Integer num = memberDao.countAllByAccount(account);
        if (num == 0) {
            flag = true;
        }
        return flag;
    }
}

