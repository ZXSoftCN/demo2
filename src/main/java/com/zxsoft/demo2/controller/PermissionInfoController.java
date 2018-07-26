package com.zxsoft.demo2.controller;

import org.apache.shiro.authz.annotation.RequiresGuest;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/Permission")
@RequiresPermissions("permission")
public class PermissionInfoController {

    @RequestMapping({"/","/index"})
    @ResponseBody
    public String index(){
        return"Permission";
    }

    @GetMapping("/anyAction")
    @ResponseBody
    @RequiresPermissions("permission:oneItem:*")
    public String doAnyAction(){
        return "doAnyAction";
    }

    @GetMapping("/allSubItem")
    @ResponseBody
    @RequiresUser
    public String allSubItem(){
        return "allSubItem";
    }

    @GetMapping("/oneItem/query")
    @ResponseBody
    @RequiresPermissions("permission:oneItem:query")
    public String oneItemQuery()
    {
        return "oneItem:query";
    }

}
