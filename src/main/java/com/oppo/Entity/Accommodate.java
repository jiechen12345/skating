package com.oppo.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by JieChen on 2018/11/9.
 */
@Entity
public class Accommodate {
    @Id
    @Column(length = 10)
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
