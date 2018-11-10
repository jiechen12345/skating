package com.oppo.Entity;

import javax.persistence.*;

/**
 * Created by JieChen on 2018/11/10.
 */
@Entity
public class Sessions {
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
    private String sessionsTime;
    //開放人數，如果場次要能個別設定放人數在進去Accommodate裡面加id改150
    @ManyToOne(targetEntity = Accommodate.class)
    private Accommodate accommodate;
    //已預約人數
    @Column( nullable = false )
    private Integer reserved = 0;

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
        dat = dat;
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

    public String getSessionsTime() {
        return sessionsTime;
    }

    public void setSessionsTime(String sessionsTime) {
        this.sessionsTime = sessionsTime;
    }

    @Override
    public String toString() {
        return "Sessions{" +
                "id=" + id +
                ", dat='" + dat + '\'' +
                ", sessionsName='" + sessionsName + '\'' +
                ", sessionsTime='" + sessionsTime + '\'' +
                ", accommodate=" + accommodate +
                ", reserved=" + reserved +
                '}';
    }
}
