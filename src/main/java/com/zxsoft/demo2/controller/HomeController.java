package com.zxsoft.demo2.controller;

import com.sun.crypto.provider.AESKeyGenerator;
import com.zxsoft.demo2.api.UserInfoDto;
import com.zxsoft.demo2.common.AESUtil;
import com.zxsoft.demo2.domain.UserInfo;
import com.zxsoft.demo2.sys.DecryptRequestBody;
import com.zxsoft.demo2.sys.EncryptResponseBody;
import com.zxsoft.demo2.sys.Log;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.springframework.http.HttpInputMessage;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.Base64;
import java.util.Map;

@Controller
public class HomeController {
    @RequestMapping({"/","/index"})
    public String index(){
        return"/index";
    }

    @ResponseBody
    @GetMapping("/testGet/{paramString}")
    public String testGet(@PathVariable String paramString) throws Exception{
        //测试AES加密
        ByteSource bsEncrypt = ByteSource.Util.bytes(paramString);
        byte[] encodes = AESUtil.toEncrypt(paramString.getBytes("UTF-8"));//加密成字节数组
        String enString = ByteSource.Util.bytes(encodes).toBase64();//将加密的字节数组Base64编码成字符串保存
        ByteSource deDecrypt = ByteSource.Util.bytes(encodes);

        byte[] undecodes = Base64.getDecoder().decode(enString);//将保存的字符串进行Base64解码成字节数组
        byte[] decodes = AESUtil.toDecrypt(undecodes);//再进行解密
//        String deString = ByteSource.Util.bytes(decodes).toBase64();//解密后字节数据转出Base64字符串，供后续Base64解码
        String str2 = new String(decodes, "UTF-8");//通过Base64解码成原文
        return str2;
    }


    @GetMapping("/testGet2/{paramString}")
    public String testGet2(@PathVariable String paramString) throws Exception{
        //测试AES加密
        ByteSource bsEncrypt = ByteSource.Util.bytes(paramString);
        byte[] encodes = AESUtil.toEncrypt(paramString.getBytes("UTF-8"));//加密成字节数组
        byte[] decodes = AESUtil.toDecrypt(encodes);
        String s = new String(decodes,"UTF-8");//直接将解密的字节数组转化成明文

        String enString = ByteSource.Util.bytes(encodes).toBase64();//将加密的字节数组Base64编码成字符串保存

        //因为加密的字节数组保存前进行了Base64编码，所以解密前先做Base64解码
        byte[] decodes2 = AESUtil.toDecrypt(Base64.getDecoder().decode(enString));//将保存的字符串进行Base64解码成字节数组,再进行解密
        String str2 = new String(decodes2, "UTF-8");//将解密后的字节数组解码成原文
        return str2;
    }

    @RequestMapping("/encrypt/{value}")
    @EncryptResponseBody
    public String responseEncrypt(@PathVariable(required = false,name = "value") String enValue,
                                  @RequestBody String bodyString) throws Exception{

        ByteSource bsEncrypt = ByteSource.Util.bytes(enValue);;
        byte[] encodes = AESUtil.toEncrypt(enValue.getBytes("UTF-8"));//加密成字节数组
        String enString = ByteSource.Util.bytes(encodes).toBase64();//将加密的字节数组Base64编码成字符串保存
        return enString;
    }

    @RequestMapping("/decrypt/decrypt")
    @ResponseBody
    public String requestDecrypt(@RequestParam(required = false,name = "enData") String enData,
                                 @RequestBody(required = false) String enJsonData) throws UnsupportedEncodingException {

        byte[] encodes = AESUtil.toEncrypt("leolee");//加密成字节数组
        String enString = Base64.getEncoder().encodeToString(encodes);//将加密的字节数组Base64编码成字符串保存

        if (enString.equalsIgnoreCase(enData)){
            System.out.print("Equal");
        }
        byte[] deBase64Codes = Base64.getDecoder().decode(enData);
        byte[] decodes = AESUtil.toDecrypt(deBase64Codes);//解密前先进行Base64解码
        String str2 = new String(decodes, "UTF-8");//通过Base64解码成原文
        return str2;
    }

    @RequestMapping("/decrypt/decrypt2")
    @ResponseBody
    @DecryptRequestBody
    public String requestDecrypt2(
            @RequestParam(required = false,name = "enData") String enData,
                                 @RequestBody(required = false) String enJsonData) {
//        String jsData = enJsonData;
        return enJsonData;
    }

    @RequestMapping("/home/encryptData")
    @EncryptResponseBody
    public String queryByEncryptData(@RequestParam(required = false,name = "enData") String enData){
        if(enData.trim().isEmpty()){
            return null;
        }
        return enData;
    }

    @Log(value = "登录跟踪")
    @RequestMapping("/login")
    public String login(HttpServletRequest request, @RequestBody UserInfoDto userData, Map<String, Object> map) throws Exception{
        System.out.println("HomeController.login()");
        // 登录失败从request中获取shiro处理的异常信息。
        // shiroLoginFailure:就是shiro异常类的全类名.


        UsernamePasswordToken token = new UsernamePasswordToken(userData.getUserName(), userData.getPassword());
        Subject currentUser = SecurityUtils.getSubject();
        try {
            if (currentUser.isAuthenticated()) {
                UserInfo currUserInfo = (UserInfo)currentUser.getPrincipals().getPrimaryPrincipal();
                //不管当前用户是不是更换用户名，都登出再重新登入。
                if(!currUserInfo.getUserName().equalsIgnoreCase(token.getUsername())){
                    currentUser.logout();
                }
            }
            currentUser.login(token);
        } catch (UnknownAccountException | IncorrectCredentialsException | LockedAccountException e) {
            return e.getMessage();
        } catch (AuthenticationException e) {
            return e.getMessage();
        }

        String exception = (String) request.getAttribute("shiroLoginFailure");
        System.out.println("exception=" + exception);
        String msg = "";
        if (exception != null) {
            if (UnknownAccountException.class.getName().equals(exception)) {
                System.out.println("UnknownAccountException -- > 账号不存在：");
                msg = "UnknownAccountException -- > 账号不存在：";
            } else if (IncorrectCredentialsException.class.getName().equals(exception)) {
                System.out.println("IncorrectCredentialsException -- > 密码不正确：");
                msg = "IncorrectCredentialsException -- > 密码不正确：";
            } else if ("kaptchaValidateFailed".equals(exception)) {
                System.out.println("kaptchaValidateFailed -- > 验证码错误");
                msg = "kaptchaValidateFailed -- > 验证码错误";
            } else {
                msg = "else >> "+exception;
                System.out.println("else -- >" + exception);
            }
        }
        map.put("msg", msg);
        // 此方法不处理登录成功,由shiro进行处理
        return "/login";
    }

    @RequestMapping("/403")
    public String unauthorizedRole(){
        System.out.println("------没有权限-------");
        return "403";
    }

}