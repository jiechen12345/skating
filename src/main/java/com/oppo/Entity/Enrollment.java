package com.oppo.Entity;

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

    private  Boolean arrived=false;

    private Date enrollTime;

    public Enrollment(String id, PreOrder preOrder) {
        this.id = id;
        this.preOrder = preOrder;
    }

    public Enrollment(String id, PreOrder preOrder, Boolean arrived, Date enrollTime) {
        this.id = id;
        this.preOrder = preOrder;
        this.arrived = arrived;
        this.enrollTime = enrollTime;
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

    public void setEnrollTime(Date enrollTime) {
        this.enrollTime = enrollTime;
    }
}
