package com.oppo.request;

/**
 * Created by JieChen on 2018/11/15.
 */
public class PreorderReq {
    private String id;
    private Integer sessionsId;
    private String preorderDate;
    private String groupName;
    private String applicantName;
    private String applicantPhone;
    private String applicantEmail;
    private Integer groupNum;

    public PreorderReq() {
    }

    public PreorderReq(String id, Integer sessionsId, String preorderDate, String groupName, String applicantName, String applicantPhone, String applicantEmail, Integer groupNum) {
        this.id = id;
        this.sessionsId = sessionsId;
        this.preorderDate = preorderDate;
        this.groupName = groupName;
        this.applicantName = applicantName;
        this.applicantPhone = applicantPhone;
        this.applicantEmail = applicantEmail;
        this.groupNum = groupNum;
    }

    public PreorderReq(Integer sessionsId, String preorderDate, String groupName, String applicantName, String applicantPhone, String applicantEmail, Integer groupNum) {
        this.sessionsId = sessionsId;
        this.preorderDate = preorderDate;
        this.groupName = groupName;
        this.applicantName = applicantName;
        this.applicantPhone = applicantPhone;
        this.applicantEmail = applicantEmail;
        this.groupNum = groupNum;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    @Override
    public String toString() {
        return "PreorderReq{" +
                "id='" + id + '\'' +
                ", sessionsId=" + sessionsId +
                ", preorderDate='" + preorderDate + '\'' +
                ", groupName='" + groupName + '\'' +
                ", applicantName='" + applicantName + '\'' +
                ", applicantPhone='" + applicantPhone + '\'' +
                ", applicantEmail='" + applicantEmail + '\'' +
                ", groupNum=" + groupNum +
                '}';
    }
}
