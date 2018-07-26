package com.zxsoft.demo2.dao;

import com.zxsoft.demo2.domain.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserInfoDao extends JpaRepository<UserInfo,String> {

    @Override
    Optional<UserInfo> findById(String id);

    Optional<UserInfo> findByUserName(String userName);

}
