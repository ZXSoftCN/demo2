package com.zxsoft.demo2.domain;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "sys_Permission")
public class Permission implements Serializable {

    private String id;//主键.
    private String name;//名称.
    private String resourceType;//资源类型，[menu|button]
    private String url;//资源路径.
    private String permission; //权限字符串,menu例子：role:*，button例子：role:create,role:update,role:delete,role:view

    private String parentId; //父编号

    private String parentIds; //父编号列表

    private Boolean isEnable = Boolean.FALSE;

    private List<Role> roles;

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

    @Column(name = "name",unique =true)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "resourceType",columnDefinition="varchar(255) default ''")
    public String getResourceType() {
        return resourceType;
    }

    public void setResourceType(String resourceType) {
        this.resourceType = resourceType;
    }

    @Column(name = "url",columnDefinition="varchar(255) default ''")
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Column(name = "permission",columnDefinition="varchar(255) default ''")
    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }
    @Column(name = "parentId",columnDefinition="varchar(255) default ''")
    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }
    @Column(name = "parentIds",columnDefinition="varchar(500) default ''")
    public String getParentIds() {
        return parentIds;
    }

    public void setParentIds(String parentIds) {
        this.parentIds = parentIds;
    }
    @Column(name = "isEnable",columnDefinition="bit default 1")
    public Boolean getIsEnable() {
        return isEnable;
    }

    public void setIsEnable(Boolean isEnable) {
        this.isEnable = isEnable;
    }
    @ManyToMany
    @JoinTable(name="sys_RolePermission",joinColumns={@JoinColumn(name="permissionId",referencedColumnName = "id")},
            inverseJoinColumns={@JoinColumn(name="roleId",referencedColumnName = "id")})
    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
}