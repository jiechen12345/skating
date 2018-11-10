package com.oppo.Entity;

import javax.persistence.*;

/**
 * Created by JieChen on 2018/11/10.
 */
@Entity
public class Sessions {
    public Sessions() {
    }

    public Sessions(String dat, String sessionsName, String startTime, String endTime, Accommodate accommodate, Integer reserved, Integer extra) {
        this.dat = dat;
        this.sessionsName = sessionsName;
        this.startTime = startTime;
        this.endTime = endTime;
        this.accommodate = accommodate;
        this.reserved = reserved;
        this.extra = extra;
    }

    public Sessions(Integer extra) {
        this.extra = extra;
    }
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;
    //private Holiday ;
    @Column( nullable = false )
    private String dat;
    //場次名稱14:00-15:30
    @Column( nullable = false )
    private String sessionsName;
    @Column( nullable = false )
    private String startTime;
    @Column( nullable = false )
    private String endTime;
    //開放人數，如果場次要能個別設定放人數在進去Accommodate裡面加id改150
    @ManyToOne(targetEntity = Accommodate.class)
    private Accommodate accommodate;
    //已預約人數
    @Column( nullable = false )
    private Integer reserved = 0;
    //已預約人數
    @Column( nullable = false )
    private Integer extra = 0;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDat() {
        return dat;
    }

    public void setDat(String dat) {
        this.dat = dat;
    }

    public String getSessionsName() {
        return sessionsName;
    }

    public void setSessionsName(String sessionsName) {
        this.sessionsName = sessionsName;
    }

    public Accommodate getAccommodate() {
        return accommodate;
    }

    public void setAccommodate(Accommodate accommodate) {
        this.accommodate = accommodate;
    }

    public Integer getReserved() {
        return reserved;
    }

    public void setReserved(Integer reserved) {
        this.reserved = reserved;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public Integer getExtra() {
        return extra;
    }

    @Override
    public String toString() {
        return "Sessions{" +
                "id=" + id +
                ", dat='" + dat + '\'' +
                ", sessionsName='" + sessionsName + '\'' +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", accommodate=" + accommodate +
                ", reserved=" + reserved +
                ", extra=" + extra +
                '}';
    }

    public void setExtra(Integer extra) {
        this.extra = extra;
    }

}
