package com.oppo.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * Created by JieChen on 2018/11/7.
 */
@Entity
public class Holiday {
    public Holiday(String holidat) {
        this.holidat = holidat;
    }

    public Holiday() {
    }
    public Holiday(String holidat, String title) {
        this.holidat = holidat;
        this.title = title;
    }

    public Holiday(String holidat, String title, Accommodate accommodate) {
        this.holidat = holidat;
        this.title = title;
        this.accommodate = accommodate;
    }

    @Id
    @Column(length = 10)
    private String holidat;

    private String title;
    @ManyToOne(targetEntity = Accommodate.class)
    private  Accommodate accommodate;


    public String getHolidat() {
        return holidat;
    }

    public void setHolidat(String holidat) {
        this.holidat = holidat;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Accommodate getAccommodate() {
        return accommodate;
    }

    public void setAccommodate(Accommodate accommodate) {
        this.accommodate = accommodate;
    }

    @Override
    public String toString() {
        return "Holiday{" +
                "holidat='" + holidat + '\'' +
                ", title='" + title + '\'' +
                ", accommodate=" + accommodate +
                '}';
    }
}
