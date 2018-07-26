package com.zxsoft.demo2.domain;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "sys_Role")
public class Role {

    private String id; // 编号
    private String roleName; // 角色标识程序中判断使用,如"admin",这个是唯一的:
    private String description; // 角色描述,UI界面显示使用
    private Boolean isEnable = Boolean.TRUE; // 是否可用,如果不可用将不会添加给用户

    //角色 -- 权限关系：多对多关系;
    private List<Permission> permissions;

    // 用户 - 角色关系定义;
    private List<UserInfo> userInfos;// 一个角色对应多个用户

    @Id
    @GenericGenerator(name = "generator", strategy = "uuid")
    @GeneratedValue(generator = "generator")
    @Column(name = "id",nullable = false,length = 36)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Column(name = "roleName",unique =true)
    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    @Column(name = "description",columnDefinition="varchar(500) default ''")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name = "isEnable",columnDefinition="bit default 1")
    public Boolean getIsEnable() {
        return isEnable;
    }

    public void setIsEnable(Boolean isEnable) {
        this.isEnable = isEnable;
    }

    @ManyToMany(fetch= FetchType.EAGER)
    @JoinTable(name="sys_RolePermission",joinColumns={@JoinColumn(name="roleId",referencedColumnName = "id")},
            inverseJoinColumns={@JoinColumn(name="permissionId",referencedColumnName = "id")})
    public List<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<Permission> permissions) {
        this.permissions = permissions;
    }

    @ManyToMany
    @JoinTable(name="sys_UserRole",joinColumns={@JoinColumn(name="roleId",referencedColumnName = "id")},
            inverseJoinColumns={@JoinColumn(name="userInfoId",referencedColumnName = "id")})
    public List<UserInfo> getUserInfos() {
        return userInfos;
    }

    public void setUserInfos(List<UserInfo> userInfos) {
        this.userInfos = userInfos;
    }
}