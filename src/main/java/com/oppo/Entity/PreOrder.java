package com.oppo.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.Date;

/**
 * Created by JieChen on 2018/11/15.
 */
@Entity
public class PreOrder {
    @Id
    @Column(length = 12)
    private String id;
    @ManyToOne(targetEntity = Sessions.class)
    private Sessions sessions;
    @Column(length = 12)
    private String preorderDate;
    private String groupName;
    @Column(length = 12)
    private String applicantName;
    private String applicantPhone;
    @Column(length = 150)
    private String applicantEmail;
    @Column(length = 50)
    private Integer groupNum;

    @ManyToOne(targetEntity = Status.class)
    //1:送出表單 2:OTP申請 3:OTP通過 4:審核不通過 5:審核通過 6:到場
    private Status status;
    private Date createTime;
    private Date otpTime;
    private Date otpPassTime;
    private Date verifyTime;
    public PreOrder() {
    }
    public PreOrder(String id) {
        this.id = id;
    }
    public PreOrder(String id, Sessions sessions, String preorderDate, String groupName, String applicantName, String applicantPhone, String applicantEmail, Integer groupNum) {
        this.id = id;
        this.sessions = sessions;
        this.preorderDate = preorderDate;
        this.groupName = groupName;
        this.applicantName = applicantName;
        this.applicantPhone = applicantPhone;
        this.applicantEmail = applicantEmail;
        this.groupNum = groupNum;
    }

    public PreOrder(String id, Sessions sessions, String preorderDate, String groupName, String applicantName, String applicantPhone, String applicantEmail, Integer groupNum, Status status, Date createTime) {
        this.id = id;
        this.sessions = sessions;
        this.preorderDate = preorderDate;
        this.groupName = groupName;
        this.applicantName = applicantName;
        this.applicantPhone = applicantPhone;
        this.applicantEmail = applicantEmail;
        this.groupNum = groupNum;
        this.status = status;
        this.createTime = createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getOtpTime() {
        return otpTime;
    }

    public void setOtpTime(Date otpTime) {
        this.otpTime = otpTime;
    }

    public Date getOtpPassTime() {
        return otpPassTime;
    }

    public void setOtpPassTime(Date otpPassTime) {
        this.otpPassTime = otpPassTime;
    }

    public Date getVerifyTime() {
        return verifyTime;
    }

    public void setVerifyTime(Date verifyTime) {
        this.verifyTime = verifyTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Sessions getSessions() {
        return sessions;
    }

    public void setSessions(Sessions sessions) {
        this.sessions = sessions;
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

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
