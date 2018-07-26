package com.zxsoft.demo2.sys;

import com.zxsoft.demo2.common.AESUtil;
import org.apache.commons.io.IOUtils;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdvice;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.Base64;
import java.util.List;


/*
    支持对Controller上注解@DecryptRequestBody的方法中ResponseBody值进行AES解密.
    可增加其它注解的拦截处理
 */
@RestControllerAdvice
public class ApiRequestAdvice implements RequestBodyAdvice {
    @Override
    public boolean supports(MethodParameter methodParameter, Type type, Class<? extends HttpMessageConverter<?>> aClass) {
        if(methodParameter.getMethod().isAnnotationPresent(DecryptRequestBody.class)) {
            return true;
        }
        return false;
    }

    @Override
    public HttpInputMessage beforeBodyRead(HttpInputMessage httpInputMessage, MethodParameter methodParameter, Type type, Class<? extends HttpMessageConverter<?>> aClass) throws IOException {

        try {
            return new DHttpInputMessage(httpInputMessage);
        }catch (Exception e){ }
        return httpInputMessage;
    }

    @Override
    public Object afterBodyRead(Object o, HttpInputMessage httpInputMessage, MethodParameter methodParameter, Type type, Class<? extends HttpMessageConverter<?>> aClass) {
        return o;
    }

    @Override
    public Object handleEmptyBody(Object o, HttpInputMessage httpInputMessage, MethodParameter methodParameter, Type type, Class<? extends HttpMessageConverter<?>> aClass) {
        return o;
    }

    private static class DHttpInputMessage implements HttpInputMessage{
        private HttpHeaders headers;

        private InputStream body;

        public DHttpInputMessage(HttpInputMessage msg) throws IOException {
            this.headers = msg.getHeaders();


            byte[] unDecryptMsg = IOUtils.toByteArray(msg.getBody());
//            List lst = IOUtils.readLines(msg.getBody(),"UTF-8");//IOUtils读取msg.getBody()后body会为null
            String strOrg = new String(unDecryptMsg,"UTF-8");
            unDecryptMsg =  Base64.getDecoder().decode(strOrg);//Base64解码
            byte[] decrypMsg = AESUtil.toDecrypt(unDecryptMsg);//再解密
            InputStream iStream = new ByteArrayInputStream(decrypMsg);
            this.body =iStream;
//            this.body = IOUtils.toInputStream(new String(decrypMsg,"UTF-8"));
        }

        @Override
        public InputStream getBody() throws IOException {
            return body;
        }

        @Override
        public HttpHeaders getHeaders() {
            return headers;
        }
    }
}
