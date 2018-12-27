package com.oppo.api;

import com.oppo.Entity.Departemt;
import com.oppo.Entity.Member;
import com.oppo.annotation.Action;
import com.oppo.business.MemberService;
import com.oppo.dao.DepartemtDao;
import com.oppo.dao.MemberDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    @Autowired
    private MemberService memberService;

    //查詢分頁會員列表及修改pageSize
    @Action("MemberApi[getAccountAjax]")
    @RequestMapping(value = "member/getAccountAjax", method = RequestMethod.POST)
    public Boolean getAccountAjax(@RequestBody String account) {
        Boolean flag = true;
        if (memberDao.countAllByAccount(account) == 0) {
            flag = false;
        }
        return flag;
    }

    @Action("MemberApi[deleteMember]")
    @RequestMapping(value = "/member/deleteMember", method = RequestMethod.DELETE)
    public void deleteMember(@RequestBody String idStr) {
        String[] idArr = idStr.split(",");
        if(idArr.length > 0) {
            for(int i = 0; i < idArr.length; i++) {
                System.out.println(idArr[i]);
                memberService.delete(Integer.parseInt(idArr[i]));
            }
        }
    }

}




