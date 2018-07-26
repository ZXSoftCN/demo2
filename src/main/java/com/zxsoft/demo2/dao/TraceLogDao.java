package com.zxsoft.demo2.dao;

import com.zxsoft.demo2.domain.TraceLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

public interface TraceLogDao extends JpaRepository<TraceLog,String> {

//    @Transactional(propagation = Propagation.REQUIRES_NEW,rollbackFor = Exception.class)
//    public TraceLog saveLog()

}
