package com.zxsoft.demo2.domain;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "sys_region")
@NamedEntityGraph(name = "Resources.lazy", attributeNodes = {@NamedAttributeNode("resources")})
public class Region extends BaseEntity {
    private static final long serialVersionUID = 5552222903713902180L;


    private String name;
    private String code;
    private String description;
    //地区logo
    private String logoUrl;
    private Set<RegionResource> resources;
    private Set<Bank> banks;

//    @JSONField(serialize=false)
    @OneToMany( fetch = FetchType.LAZY,mappedBy = "region")
//    @JoinColumn(name = "regionId")
    public Set<Bank> getBanks() {
        return banks;
    }

    public void setBanks(Set<Bank> banks) {
        this.banks = banks;
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "code",nullable = false,unique = true)
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name = "logoUrl")
    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

//    @JSONField(serialize=true)
    @OneToMany(cascade = {CascadeType.REMOVE},mappedBy = "region",fetch = FetchType.LAZY)
    public Set<RegionResource> getResources() {
        return resources;
    }

    public void setResources(Set<RegionResource> resources) {
        this.resources = resources;
    }
}
