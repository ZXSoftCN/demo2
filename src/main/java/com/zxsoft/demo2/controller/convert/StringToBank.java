package com.zxsoft.demo2.controller.convert;

import com.zxsoft.demo2.dao.BankDao;
import com.zxsoft.demo2.domain.Bank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StringToBank implements Converter<String,Bank> {

    @Autowired
    private BankDao bankDao;

    @Override
    public Bank convert(String s) {
        return bankDao.findByBankCodeIgnoreCase(s);
    }
}
