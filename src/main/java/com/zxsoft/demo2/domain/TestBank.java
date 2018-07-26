package com.zxsoft.demo2.domain;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "test_bank")
public class TestBank {
    private int id;
    private String code;

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "code", nullable = true, length = 255)
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    private TestBank parent;
    private Set<TestBank> children = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "PARENTID")
    public TestBank getParent() {
        return parent;
    }

    public void setParent(TestBank parent) {
        this.parent = parent;
    }

    @OneToMany(mappedBy = "parent", fetch = FetchType.EAGER,cascade = CascadeType.REMOVE)
    public Set<TestBank> getChildren() {
        return children;
    }

    public void setChildren(Set<TestBank> children) {
        this.children = children;
    }

}
