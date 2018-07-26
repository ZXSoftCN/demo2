package com.zxsoft.demo2.api;

import com.alibaba.druid.support.json.JSONParser;
import com.alibaba.druid.support.json.JSONUtils;
import com.zxsoft.demo2.domain.UserInfo;
import com.zxsoft.demo2.service.Impl.UserInfoServiceImpl;
import com.zxsoft.demo2.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/userInfoApi")
public class UserInfoApi {


    @Autowired
    private UserInfoService userInfoService;

    @PostMapping("/add")
    public UserInfo addUserInfo(@RequestBody UserInfoDto userdata){

        String strUserName = userdata.getUserName();
        return userInfoService.addUserInfo(userdata.getUserName(), userdata.getName(), userdata.getPassword());
    }
}
