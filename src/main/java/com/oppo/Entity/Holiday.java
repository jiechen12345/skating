package com.oppo.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by JieChen on 2018/11/7.
 */
@Entity
public class Holiday {
    @Id
    @Column(length = 10)
    private String holidat;

    private String title;

    public Holiday(String holidat) {
        this.holidat = holidat;
    }

    public Holiday() {
    }

    public Holiday(String holidat, String title) {
        this.holidat = holidat;
        this.title = title;
    }

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

    @Override
    public String toString() {
        return "Holiday{" +
                "holidat='" + holidat + '\'' +
                ", title='" + title + '\'' +
                '}';
    }
}
