package com.zxsoft.demo2.dao;

import com.zxsoft.demo2.domain.TestBank;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestBankDao extends JpaRepository<TestBank,Integer> {
}
