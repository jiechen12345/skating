package com.oppo.Entity;

import com.oppo.Entity.PreOrder;

import javax.persistence.Id;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.util.Date;

/**
 * Created by JieChen on 2018/11/22.
 */
@Entity
public class Enrollment {
    @Id
    @Column(length = 6)
    private String id;

    @ManyToOne(targetEntity = PreOrder.class)
    private PreOrder preOrder;

    private Boolean arrived = false;
    private Boolean printed = false;
    private Date enrollTime;

    public Enrollment(String id, PreOrder preOrder, Boolean arrived, Date enrollTime) {
        this.id = id;
        this.preOrder = preOrder;
        this.arrived = arrived;
        this.enrollTime = enrollTime;
    }
    public Enrollment(String id, PreOrder preOrder,Boolean arrived,Boolean printed) {
        this.id = id;
        this.preOrder = preOrder;
        this.arrived=arrived;
        this.printed=printed;
    }
    public Enrollment() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public PreOrder getPreOrder() {
        return preOrder;
    }

    public void setPreOrder(PreOrder preOrder) {
        this.preOrder = preOrder;
    }

    public Boolean getArrived() {
        return arrived;
    }

    public void setArrived(Boolean arrived) {
        this.arrived = arrived;
    }

    public Date getEnrollTime() {
        return enrollTime;
    }

    public Boolean getPrinted() {
        return printed;
    }

    public void setPrinted(Boolean printed) {
        this.printed = printed;
    }

    public void setEnrollTime(Date enrollTime) {
        this.enrollTime = enrollTime;
    }
}
