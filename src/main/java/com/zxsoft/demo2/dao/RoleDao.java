package com.zxsoft.demo2.dao;

import com.zxsoft.demo2.domain.Role;
import com.zxsoft.demo2.domain.UserInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RoleDao extends JpaRepository<Role,String> {

    List<Role> findAllByRoleNameLike(String rolename);
    Page<Role> findAllByRoleNameLikeOrderById(String rolename,Pageable pageable);

    List<Role> findAllByIsEnableTrueOrderById();
    Page<Role> findAllByIsEnableTrueOrderById(Pageable pageable);

}
