package com.oppo.Entity;

import javax.persistence.*;

/**
 * Created by JieChen on 2018/11/18.
 * 1:送出表單 2:OTP申請 3:OTP通過 4:審核不通過 5:審核通過 6:到場
 */
@Entity
public class Status {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;
    @Column(length = 10)
    private String statusName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }
}
