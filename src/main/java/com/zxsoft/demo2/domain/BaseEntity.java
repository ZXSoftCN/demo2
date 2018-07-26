package com.zxsoft.demo2.domain;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.zxsoft.demo2.dao.BaseEntityDao;
import org.hibernate.annotations.GenericGenerator;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class BaseEntity implements Serializable {
    //编译时记录版本ID，用于分辨修改前后对象序列化是否相同。
    //若有修改VersionUID，则提示：序列化版本不一致异常(InvalidCastException)
    private static final long serialVersionUID = 4159370717186153399L;

    private String id; // 主键
    private Date createTime;
    private Date lastUpdateTime;
    private String creator;
    private String lastUpdater;

    @PrePersist
    public void prePersist() {
        createTime = lastUpdateTime = DateTime.now().toDate();
    }

    @PreUpdate
    public void preUpdate() {
        lastUpdateTime = DateTime.now().toDate();
    }

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


    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss" )
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_time")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss" )
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "lastUpdate_Time")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    public Date getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(Date lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    @Column(name="creator")
    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    @Column(name="lastUpdater")
    public String getLastUpdater() {
        return lastUpdater;
    }

    public void setLastUpdater(String lastUpdater) {
        this.lastUpdater = lastUpdater;
    }

}
