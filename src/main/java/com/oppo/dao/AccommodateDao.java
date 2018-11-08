package com.oppo.dao;

import com.oppo.Entity.Accommodate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Created by JieChen on 2018/11/9.
 */
public interface AccommodateDao extends JpaRepository<Accommodate, String>, JpaSpecificationExecutor<Accommodate> {
    // Holiday findByAccount(String account);
}
