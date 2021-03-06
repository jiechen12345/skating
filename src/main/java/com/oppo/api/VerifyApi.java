package com.oppo.api;

import com.oppo.annotation.Action;
import com.oppo.business.VerfyService;
import com.oppo.dto.*;
import com.oppo.request.PreorderReq;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by JieChen on 2018/7/24.
 */
@Controller
public class VerifyApi {
    @Autowired
    private VerfyService verfyService;
    @Value("${upload.uploadingdir}")
    String uploadingdir;
    Integer[] pageSizeOption = {5, 10, 15, 20};
    PreorderReq preorderReq = new PreorderReq();
//    List<Integer> pageSizeOption=new ArrayList<Integer>()

    //查詢分頁會員列表及修改pageSize
    @Action("VerifyApi[findAll]")
    @GetMapping("/verify")
    public String findAll(@RequestParam(required = false, defaultValue = "1") Integer page,
                          @RequestParam(required = false, defaultValue = "5") Integer pageSize,
                          Model model) {
        preorderReq = new PreorderReq();
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
    @Action("VerifyApi[changePageSize]")
    @GetMapping("/verifyChangePage")
    public String changePageSize(@RequestParam(required = false, defaultValue = "1") Integer page,
                                 @RequestParam(required = false, defaultValue = "5") Integer pageSize, Model model) {
        PreorderPage preorderPage = verfyService.getAllForm(page, pageSize);
        //List<MemberDto> memberDtoList = memberService.findAll();
        model.addAttribute("preorders", preorderPage.getContents());
        model.addAttribute("indexPage", preorderPage.getCurrentPage());
        model.addAttribute("totalPages", preorderPage.getTotalPages());
        model.addAttribute("pageSize", pageSize);
        model.addAttribute("count", preorderPage.getCount());
        model.addAttribute("pageSizeOption", pageSizeOption);
        return "verify";
    }

    @Action("VerifyApi[openImgContent]")
    @GetMapping("/imgContent/{id}")
    public String openImgContent(@PathVariable String id, Model model) throws Exception {
        List<String> list = new ArrayList<String>();
        String errMsg="";
        File file = new File(uploadingdir, id);
        //String fileString=file.listFiles()[0];
        String fileString = " ";
        if (file.exists()) {
            for (File f : file.listFiles()) {
                fileString = f.toString();
                int i = fileString.lastIndexOf("\\");
                int j = fileString.length();
                String fileName = fileString.substring(fileString.lastIndexOf("\\") + 1, fileString.length());
                list.add(fileName);
            }
        }else{
            errMsg=" 此筆資料無上傳附件!!";
        }
        model.addAttribute("imgs", list);
        model.addAttribute("id", id);
        model.addAttribute("errMsg", errMsg);
        return "showImg";
    }

    @Action("VerifyApi[showImg]")
    @GetMapping("/showImg/{id}/{picFile}")
    public void showImg(@PathVariable String id, @PathVariable String picFile, HttpServletResponse response) throws Exception {
        File file = new File(uploadingdir, id);
        String fileName = file.toString() + "\\" + picFile;
        response.setHeader("Content-Disposition", fileName);
        response.setContentType("image/jpeg");
        IOUtils.copy(new FileInputStream(fileName), response.getOutputStream());
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
