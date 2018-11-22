package com.oppo.dao;

import com.oppo.Entity.Enrollment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Created by JieChen on 2018/11/23.
 */
public interface EnrollmentDao extends JpaRepository<Enrollment, String>, JpaSpecificationExecutor<Enrollment> {
    //PreOrder findFirstByAndPreorderDateOrderByIdDesc(String preorderDate);
    Enrollment findFirstByIdStartingWithOrderByIdDesc(String idStart);
}
