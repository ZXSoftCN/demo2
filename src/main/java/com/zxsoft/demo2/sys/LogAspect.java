package com.zxsoft.demo2.sys;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zxsoft.demo2.dao.TraceLogDao;
import com.zxsoft.demo2.domain.TraceLog;
import com.zxsoft.demo2.domain.UserInfo;
import com.zxsoft.demo2.util.AddressUtils;
import com.zxsoft.demo2.util.HttpContextUtils;
import com.zxsoft.demo2.util.IPUtils;
import org.apache.shiro.SecurityUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.stereotype.Component;
import org.springframework.validation.support.BindingAwareModelMap;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

@Aspect
@Component
public class LogAspect {

    @Autowired
    private TraceLogDao traceLogDao;

    @Autowired
    ObjectMapper mapper;

    @Pointcut("@annotation(com.zxsoft.demo2.sys.Log)")
    public void pointcut() {
    }

    @Around("pointcut()")
    public Object around(ProceedingJoinPoint point) {
        Object result = null;
        long beginTime = System.currentTimeMillis();
        try {
            result = point.proceed();
        } catch (Throwable e) {
            e.printStackTrace();
        }
        long time = System.currentTimeMillis() - beginTime;
        saveLog(point, time);
        return result;
    }

    private void saveLog(ProceedingJoinPoint joinPoint, long time) {
        UserInfo user = (UserInfo) SecurityUtils.getSubject().getPrincipal();
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        TraceLog log = new TraceLog();
        Log logAnnotation = method.getAnnotation(Log.class);
        if (logAnnotation != null) {
            log.setOperation(logAnnotation.value());
        }
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = signature.getName();
        log.setMethod(className + "." + methodName + "()");
        Object[] args = joinPoint.getArgs();
        LocalVariableTableParameterNameDiscoverer u = new LocalVariableTableParameterNameDiscoverer();
        String[] paramNames = u.getParameterNames(method);
        if (args != null && paramNames != null) {
            StringBuilder params = new StringBuilder();
            for (int i = 0; i < args.length; i++) {
                if (args[i] instanceof HttpServletRequest){
                    params.append("  ").append(paramNames[i]).append(": ").append(args[i].getClass().getName());
                }else if (args[i] instanceof BindingAwareModelMap) {
                    continue;
                }else{
                    params.append("  ").append(paramNames[i]).append(": ").append(JSON.toJSONString(args[i]));
                }
            }
            log.setParams(params.toString());
        }
        HttpServletRequest request = HttpContextUtils.getHttpServletRequest();
        log.setIp(IPUtils.getIpAddr(request));
        log.setUsername(user.getUserName());
        log.setTimeConsume(time);
        log.setCreateTime(DateTime.now().toDate());
        log.setLocation(AddressUtils.getRealAddressByIP(log.getIp(), mapper));
        this.traceLogDao.saveAndFlush(log);
    }
}
