package com.zxsoft.demo2.sys;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authz.AuthorizationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;


/*
    作为Rest服务器响应异常的拦截器
    PostHandler
 */
@RestControllerAdvice
public class RestApiAdvice {

    @ExceptionHandler(value = AuthenticationException.class)
    public ResponseEntity<FanFExceptionDTO>  handleAuthenticationException(){
        FanFExceptionDTO dto = new FanFExceptionDTO(403, "认证失败！");
        return new ResponseEntity<>(dto, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(value = AuthorizationException.class)
    public ResponseEntity<FanFExceptionDTO> handleException(AuthorizationException e){
        FanFExceptionDTO dto = new FanFExceptionDTO();
        dto.setMessage(String.format("未被授权：%s", e.getMessage()));
        return new ResponseEntity<>(dto, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<FanFExceptionDTO> handleException(Exception e){
        FanFExceptionDTO dto = new FanFExceptionDTO();
        dto.setMessage(e.getMessage());
        return new ResponseEntity<>(dto, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
