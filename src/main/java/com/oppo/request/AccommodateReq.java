package com.oppo.request;

/**
 * Created by JieChen on 2018/11/9.
 */
public class AccommodateReq {
    private Integer normaldayNum;
    private Integer holidayNum;
    private String specialDat;
    private Integer specialDayNum;
    private Integer sessionsId;
    public Integer getNormaldayNum() {
        return normaldayNum;
    }

    public void setNormaldayNum(Integer normaldayNum) {
        this.normaldayNum = normaldayNum;
    }

    public Integer getHolidayNum() {
        return holidayNum;
    }

    public void setHolidayNum(Integer holidayNum) {
        this.holidayNum = holidayNum;
    }

    public String getSpecialDat() {
        return specialDat;
    }

    public void setSpecialDat(String specialDat) {
        this.specialDat = specialDat;
    }

    public Integer getSpecialDayNum() {
        return specialDayNum;
    }

    public void setSpecialDayNum(Integer specialDayNum) {
        this.specialDayNum = specialDayNum;
    }

    public Integer getSessionsId() {
        return sessionsId;
    }

    public void setSessionsId(Integer sessionsId) {
        this.sessionsId = sessionsId;
    }

    @Override
    public String toString() {
        return "AccommodateReq{" +
                "normaldayNum=" + normaldayNum +
                ", holidayNum=" + holidayNum +
                ", specialDat='" + specialDat + '\'' +
                ", specialDayNum=" + specialDayNum +
                ", sessionsId=" + sessionsId +
                '}';
    }
}
