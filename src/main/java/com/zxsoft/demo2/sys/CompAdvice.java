package com.zxsoft.demo2.sys;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/*
    只应用@Controller的控制器组件，不影响@RestController组件。
    @ControllerAdvice中方法需要注解@ResponseBody对结果进行json化传出。

    替代PostHandler作为拦截器使用
 */
@ControllerAdvice(annotations = Controller.class)
public class CompAdvice {

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ResponseEntity<FanFExceptionDTO> handleException(Exception e){
        FanFExceptionDTO dto = new FanFExceptionDTO();
        dto.setMessage(e.getMessage());
        return new ResponseEntity<>(dto, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
