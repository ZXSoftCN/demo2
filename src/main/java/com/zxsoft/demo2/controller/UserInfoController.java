package com.zxsoft.demo2.controller;

import com.zxsoft.demo2.domain.UserInfo;
import com.zxsoft.demo2.service.Impl.UserInfoServiceImpl;
import com.zxsoft.demo2.service.UserInfoService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/userInfo")
public class UserInfoController {


    @Autowired
    private UserInfoService userInfoService;

    @GetMapping(value = "/userList",produces = "application/json;charset=utf-8")
    @RequiresPermissions("userInfo:list")
    public ResponseEntity<List<UserInfo>> getUserList(@RequestParam(value = "offset",defaultValue = "0") int offset,
                              @RequestParam(value = "limit",defaultValue = "10") int limit){
        Map<String,Integer> pageParam = new HashMap<String,Integer>();
        pageParam.put("offset",offset);
        pageParam.put("limit",limit);
        List<UserInfo> lstUserInfo = userInfoService.getUserInfoList(pageParam);

        return new ResponseEntity<List<UserInfo>>(lstUserInfo, HttpStatus.OK);
    }

    /**
     * 用户查询.
     * @return
     */
    @GetMapping(value = "/userView",produces = "application/json;charset=utf-8")
    @RequiresPermissions("userInfo:view")//权限管理;
    public String userInfo(){
        
        return "userInfo";
    }

    /**
     * 用户添加;
     * @return
     */
    @RequestMapping("/userAdd")
    @RequiresPermissions("userInfo:add")//权限管理;
    public String userInfoAdd(){
        return "userInfoAdd";
    }

    /**
     * 用户删除;
     * @return
     */
    @RequestMapping("/userDel")
    @RequiresPermissions("userInfo:del")//权限管理;
    public String userDel(){
        return "userInfoDel";
    }
}