package com.oppo.dao;


import com.oppo.Entity.PreOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Created by JieChen on 2018/11/16.
 */
public interface PreorderDao extends JpaRepository<PreOrder, String>, JpaSpecificationExecutor<PreOrder> {
    PreOrder findFirstByOrderByIdDesc();
}
