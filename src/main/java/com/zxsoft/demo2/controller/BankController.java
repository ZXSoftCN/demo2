package com.zxsoft.demo2.controller;

import com.zxsoft.demo2.dao.BankDao;
import com.zxsoft.demo2.domain.Bank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bank")
public class BankController {

    @Autowired
    private BankDao bankDao;

    @GetMapping("/{code}")
    @ResponseBody
    public Bank getBankByCode(@PathVariable(name = "code") Bank code){

        return code;
    }

    @PostMapping("/add")
    @ResponseBody
    public Bank addBank(@RequestBody Bank jsonObj){

        return null;
    }
}
