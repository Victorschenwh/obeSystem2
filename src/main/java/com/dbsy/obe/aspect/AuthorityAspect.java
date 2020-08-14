package com.dbsy.obe.aspect;

import com.dbsy.obe.annotation.Authority;
import com.dbsy.obe.myenum.Role;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.*;

@Component
@Aspect
public class AuthorityAspect {
    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Around("@annotation(com.dbsy.obe.annotation.Authority)||@within(com.dbsy.obe.annotation.Authority)")
    public Object around(ProceedingJoinPoint pJoinPoint) throws Throwable {

        log.info("权限校验");

        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        HttpServletResponse response = attributes.getResponse();

        List<Role> roleList = new ArrayList<>();
        Map map = new HashMap();

        Class<?> aClass = pJoinPoint.getTarget().getClass();//获得所在切点的该类的class对象，也就是UserController这个类的对象

        //判断注解是否在类上
        if (aClass.getAnnotation(Authority.class) != null) {
            Role[] roles = aClass.getAnnotation(Authority.class).value();
            roleList.addAll(Arrays.asList(roles));
/*            for (Role role : roles) {
                //只要拥有一个角色在线,便能拥有访问权限
                if (request.getSession().getAttribute(role + "") != null) {
                    //放行
                    return pJoinPoint.proceed();
                }
            }
            //一个角色的登录状态都不存在,拒绝访问
            if (pJoinPoint.proceed() instanceof String) {
                response.sendRedirect("/");
                return null;
            }
            map.put("code", -1);
            map.put("msg", "权限不足,请先登录");
            return map;*/
        }

        String name = pJoinPoint.getSignature().getName();
        Class<?>[] parameterTypes = ((MethodSignature) pJoinPoint.getSignature()).getParameterTypes();
        Method method = aClass.getMethod(name, parameterTypes);//通过反射获得该方法
        Authority authority = method.getAnnotation(Authority.class);//获得该注解
        //判断注解是否在方法上
        if (authority != null) {
            roleList.addAll(Arrays.asList(authority.value()));
           /* for (Role role : authority.value()) {
                if (request.getSession().getAttribute(role + "") != null) {
                    return pJoinPoint.proceed();
                }
            }
            if (pJoinPoint.proceed() instanceof String) {
                response.sendRedirect("/");
                return null;
            }
            //一个角色的登录状态都不存在,拒绝访问
            map.put("code", -1);
            map.put("msg", "权限不足");
            return map;*/
        }

        if (roleList.size() > 0) {
            for (Role role : roleList) {
                if (request.getSession().getAttribute(role + "") != null) {
                    return pJoinPoint.proceed();
                }
            }
            if (pJoinPoint.proceed() instanceof String) {
                response.sendRedirect("/");
                return null;
            }
            //一个角色的登录状态都不存在,拒绝访问
            map.put("code", -1);
            map.put("msg", "权限不足");
            return map;
        }
        //没有设置权限校验,直接通行
        return pJoinPoint.proceed();
    }
}
