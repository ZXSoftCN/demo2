package com.zxsoft.demo2.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "mort_case")
public class MortgageCase extends BaseEntity {
    private static final long serialVersionUID = 1429301041567617225L;

    private String caseNo;
    private String description;

    @Column(name = "caseNo")
    public String getCaseNo() {
        return caseNo;
    }

    public void setCaseNo(String caseNo) {
        this.caseNo = caseNo;
    }

    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
