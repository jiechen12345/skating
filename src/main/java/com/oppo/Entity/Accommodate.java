package com.oppo.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by JieChen on 2018/11/9.
 * 開放人數
 */
@Entity
public class Accommodate {
    public Accommodate() {
    }

    public Accommodate(String flag, Integer num) {
        this.flag = flag;
        this.num = num;
    }

    //2018-11-11-2
    @Id
    @Column(length = 12)
    private String flag;

    private Integer num;

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    @Override
    public String toString() {
        return "Accommodate{" +
                "flag='" + flag + '\'' +
                ", num=" + num +
                '}';
    }
}
