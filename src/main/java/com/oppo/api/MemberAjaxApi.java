package com.oppo.api;

import com.oppo.Entity.Departemt;
import com.oppo.Entity.Member;
import com.oppo.annotation.Action;
import com.oppo.dao.DepartemtDao;
import com.oppo.dao.MemberDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by JieChen on 2018/7/24.
 */
@CrossOrigin(origins = "*")
@RestController

public class MemberAjaxApi {
    @Autowired
    private MemberDao memberDao;

    //查詢分頁會員列表及修改pageSize
    @Action("MemberApi[getAccountAjax]")
    @RequestMapping(value = "member/getAccountAjax", method = RequestMethod.POST)
    public List<Member> getAccountAjax() {
        List<Member> memberList = new ArrayList<Member>();
        memberList = memberDao.findAll();
        System.out.println(memberList);
        return memberList;
    }
}




