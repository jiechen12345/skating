package com.oppo.dao;

import com.oppo.Entity.Holiday;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Created by JieChen on 2018/11/7.
 */
public interface HolidayDao extends JpaRepository<Holiday, String>, JpaSpecificationExecutor<Holiday> {
   // Holiday findByAccount(String account);
}
