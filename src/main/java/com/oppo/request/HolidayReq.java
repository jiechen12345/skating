package com.oppo.request;
/**
 * Created by JieChen on 2018/11/7.
 */

public class HolidayReq {
    public HolidayReq() {
    }
    public HolidayReq(String startDat, String endDat) {
        this.startDat = startDat;
        this.endDat = endDat;
    }

    private String startDat;
    private String endDat;

    public String getStartDat() {
        return startDat;
    }

    public void setStartDat(String startDat) {
        this.startDat = startDat;
    }

    public String getEndDat() {
        return endDat;
    }

    public void setEndDat(String endDat) {
        this.endDat = endDat;
    }

    @Override
    public String toString() {
        return "HolidayReq{" +
                "startDat='" + startDat + '\'' +
                ", endDat='" + endDat + '\'' +
                '}';
    }
}
