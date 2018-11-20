package com.oppo.dao;

import com.oppo.Entity.Sessions;
import com.oppo.Entity.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Created by JieChen on 2018/11/20.
 */
public interface StatusDao extends JpaRepository<Status, Integer>, JpaSpecificationExecutor<Status> {
}
