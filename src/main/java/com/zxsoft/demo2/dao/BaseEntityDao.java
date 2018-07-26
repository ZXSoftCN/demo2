package com.zxsoft.demo2.dao;

import com.zxsoft.demo2.domain.BaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BaseEntityDao extends JpaRepository<BaseEntity,String> {
    @Override
    Optional<BaseEntity> findById(String s);

}
