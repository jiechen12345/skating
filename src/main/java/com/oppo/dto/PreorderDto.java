package com.oppo.dto;

/**
 * Created by JieChen on 2018/11/18.
 */
public class PreorderDto {

    public String dat;
    public String sessionsName;
    public String startTime;
    public String endTime;
    public String starAndEndTime;
    //已預約
    public Integer reserved = 0;
    //剩餘人數
    public Integer remaining =0;


    public String id;
    public Integer sessionsId;
    public String preorderDate;
    public String groupName;
    public String applicantName;
    public String applicantPhone;
    public String applicantEmail;
    public Integer groupNum;
    //0:送出 1:OTP過 2:審核通過 3:到場?? 4:審核不通過
    private String status;
    public PreorderDto() {
    }

    public PreorderDto(String sessionsName, String starAndEndTime, String preorderDate, String groupName, String applicantName, String applicantPhone, String applicantEmail, Integer groupNum, String status) {
        this.sessionsName = sessionsName;
        this.starAndEndTime = starAndEndTime;
        this.preorderDate = preorderDate;
        this.groupName = groupName;
        this.applicantName = applicantName;
        this.applicantPhone = applicantPhone;
        this.applicantEmail = applicantEmail;
        this.groupNum = groupNum;
        this.status = status;
    }

    public PreorderDto(String id, Integer sessionsId, String preorderDate, String groupName, String applicantName, String applicantPhone, String applicantEmail, Integer groupNum, String status) {
        this.id = id;
        this.sessionsId = sessionsId;
        this.preorderDate = preorderDate;
        this.groupName = groupName;
        this.applicantName = applicantName;
        this.applicantPhone = applicantPhone;
        this.applicantEmail = applicantEmail;
        this.groupNum = groupNum;
        this.status = status;
    }

    public PreorderDto(String dat, String sessionsName, String startTime, String endTime, Integer reserved, Integer remaining, String status) {
        this.dat = dat;
        this.sessionsName = sessionsName;
        this.startTime = startTime;
        this.endTime = endTime;
        this.reserved = reserved;
        this.remaining = remaining;
        this.status = status;
    }



    public PreorderDto( String dat, String sessionsName, String startTime, String endTime, Integer reserved, Integer remaining) {
        this.dat = dat;
        this.sessionsName = sessionsName;
        this.startTime = startTime;
        this.endTime = endTime;
        this.reserved = reserved;
        this.remaining = remaining;
    }

    public String getStarAndEndTime() {
        return starAndEndTime;
    }

    public void setStarAndEndTime(String starAndEndTime) {
        this.starAndEndTime = starAndEndTime;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public Integer getSessionsId() {
        return sessionsId;
    }

    public void setSessionsId(Integer sessionsId) {
        this.sessionsId = sessionsId;
    }

    public String getPreorderDate() {
        return preorderDate;
    }

    public void setPreorderDate(String preorderDate) {
        this.preorderDate = preorderDate;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getApplicantName() {
        return applicantName;
    }

    public void setApplicantName(String applicantName) {
        this.applicantName = applicantName;
    }

    public String getApplicantPhone() {
        return applicantPhone;
    }

    public void setApplicantPhone(String applicantPhone) {
        this.applicantPhone = applicantPhone;
    }

    public String getApplicantEmail() {
        return applicantEmail;
    }

    public void setApplicantEmail(String applicantEmail) {
        this.applicantEmail = applicantEmail;
    }

    public Integer getGroupNum() {
        return groupNum;
    }

    public void setGroupNum(Integer groupNum) {
        this.groupNum = groupNum;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public Integer getReserved() {
        return reserved;
    }

    public void setReserved(Integer reserved) {
        this.reserved = reserved;
    }

    public Integer getRemaining() {
        return remaining;
    }

    public void setRemaining(Integer remaining) {
        this.remaining = remaining;
    }

    @Override
    public String toString() {
        return "PreorderDto{" +
                "dat='" + dat + '\'' +
                ", sessionsName='" + sessionsName + '\'' +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", starAndEndTime='" + starAndEndTime + '\'' +
                ", reserved=" + reserved +
                ", remaining=" + remaining +
                ", id='" + id + '\'' +
                ", sessionsId=" + sessionsId +
                ", preorderDate='" + preorderDate + '\'' +
                ", groupName='" + groupName + '\'' +
                ", applicantName='" + applicantName + '\'' +
                ", applicantPhone='" + applicantPhone + '\'' +
                ", applicantEmail='" + applicantEmail + '\'' +
                ", groupNum=" + groupNum +
                ", status='" + status + '\'' +
                '}';
    }
}
