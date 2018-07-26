package com.zxsoft.demo2.domain;

import org.springframework.format.annotation.NumberFormat;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "SC_ORG_INFO")
public class ScOrgInfo {
    private int id;
    private ScOrgInfo scOrgInfo;
    private Set<ScOrgInfo> scOrgInfos = new HashSet<ScOrgInfo>();

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setScOrgInfo(ScOrgInfo scOrgInfo) {
        this.scOrgInfo = scOrgInfo;
    }

    @ManyToOne(cascade = {CascadeType.MERGE}, optional = false)
    @JoinColumn(name = "PORG_ID")
    public ScOrgInfo getScOrgInfo() {
        return this.scOrgInfo;
    }

    public void setScOrgInfos(Set<ScOrgInfo> scOrgInfos) {
        this.scOrgInfos = scOrgInfos;
    }

    @OneToMany(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY, mappedBy = "scOrgInfo")
    public Set<ScOrgInfo> getScOrgInfos() {
        return this.scOrgInfos;
    }

}