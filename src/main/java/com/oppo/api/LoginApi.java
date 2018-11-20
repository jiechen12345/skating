package com.oppo.api;

import com.oppo.Entity.Member;
import com.oppo.dao.MemberDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;

/**
 * Created by jiechen on 2018/7/23.
 */
@Controller
@RequestMapping("/api")
public class LoginApi {
    Logger LOGGER = LoggerFactory.getLogger(LoginApi.class);
    @Autowired
    private MemberDao memberDao;
    @PostMapping(value = "/login")
    public String login(@RequestParam("account") String account, @RequestParam("password") String password
            , Map<String, Object> map, HttpSession session,HttpServletRequest request) throws IOException {
            //String ip=getIpAddress(request);
            Member member = memberDao.findByAccount(account);
            if(member!=null){
                String p=member.getPassword();
                if(password.equals(p)) {
                    session.setAttribute("loginUser", member);
                    return "redirect:/main.html";
                }else{
                    map.put("msg", "密碼錯誤");
                    return "index";
                }
            }else {
                map.put("msg", "無此帳號");
                return "index";
            }
    }


}
