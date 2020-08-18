package com.dbsy.obe.aspect;

import com.dbsy.obe.util.News;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Component
@Aspect
public class LoginAspect {
    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    RedisTemplate redisTemplate;

    Model model;

    @Around("@annotation(com.dbsy.obe.annotation.Login)||@within(com.dbsy.obe.annotation.Login)")
    public Object around(ProceedingJoinPoint pJoinPoint) throws Throwable {

        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        String username = request.getParameter("username");
        String email = request.getParameter("email");
        HttpSession session = request.getSession();
        session.setAttribute("sessionUsername",username);
//        System.out.println("username： " + username);


        String ip = request.getRemoteAddr();
        if (redisTemplate.hasKey(ip) && Integer.parseInt(redisTemplate.opsForValue().get(ip) + "") >= 10) {
            return News.fail("操作过于频繁,ip被锁定,一小时后解除");
        }
        if (username != null && redisTemplate.hasKey(username) && Integer.parseInt(redisTemplate.opsForValue().get(username) + "") >= 10) {
            return News.fail("操作过于频繁,用户名被锁定,一小时后解除");
        }
        if (email != null && redisTemplate.hasKey(email) && Integer.parseInt(redisTemplate.opsForValue().get(email) + "") >= 10) {
            return News.fail("操作过于频繁,用户名被锁定,一小时后解除");
        }
        return pJoinPoint.proceed();

    }
}
