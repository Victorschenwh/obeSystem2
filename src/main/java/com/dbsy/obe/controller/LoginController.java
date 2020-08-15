package com.dbsy.obe.controller;

import com.dbsy.obe.annotation.Login;
import com.dbsy.obe.myenum.Role;
import com.dbsy.obe.pojo.Admin;
import com.dbsy.obe.pojo.Teacher;
import com.dbsy.obe.service.AdminService;
import com.dbsy.obe.service.TeacherService;
import com.dbsy.obe.util.Check;
import com.dbsy.obe.util.News;
import com.dbsy.obe.util.SendEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@Controller
@RequestMapping("/login")
public class LoginController {

    @Autowired
    @Qualifier("teacherServiceImp")
    TeacherService teacherService;

    @Autowired
    @Qualifier("adminServiceImp")
    AdminService adminService;

    @Autowired
    RedisTemplate redisTemplate;

    /**
     * 登录操作时安全次数限制
     *
     * @param key
     */
    public void limit(String key) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        String ip = request.getRemoteAddr();
        if (redisTemplate.hasKey(ip)) {
            redisTemplate.opsForValue().set(ip, Integer.parseInt(redisTemplate.opsForValue().get(ip) + "") + 1);
        } else {
            redisTemplate.opsForValue().set(ip, 0, 1, TimeUnit.HOURS);
        }

        if (redisTemplate.hasKey(key)) {
            redisTemplate.opsForValue().set(key, Integer.parseInt(redisTemplate.opsForValue().get(key) + "") + 1);
        } else {
            redisTemplate.opsForValue().set(key, 0, 1, TimeUnit.HOURS);
        }

    }

    @RequestMapping("/teacher")
    @Login
    @ResponseBody
    public Map teacher(String username, String password, HttpSession httpSession) {


        Teacher teacher = teacherService.selectByUsernameAndPassword(username, password);

        if (teacher == null) {
            this.limit(username);
            return News.fail("用户名或密码不正确");
        }

        if (!teacher.getIsLock()) {
            httpSession.setAttribute(Role.Teacher + "", teacher);
            return News.success();
        } else {
            return News.fail("操作过于频繁,被锁定,请联系管理员!");
        }

    }


    @RequestMapping("/admin")
    @Login
    @ResponseBody
    public Map admin(String username, String password, HttpSession httpSession) {

        Admin admin = adminService.selectByUsernameAndPassword(username, password);

        if (admin == null) {
            this.limit(username);
            return News.fail("用户名或密码不正确");
        }

        if (!admin.getIsLock()) {
            httpSession.setAttribute(Role.Admin + "", admin);
            return News.success();
        } else {
            return News.fail("操作过于频繁,被锁定,请联系管理员!");
        }

    }


    @RequestMapping("/emailLogin")
    @Login
    @ResponseBody
    public Map emailLogin(String email, String captcha, int role, HttpServletRequest request, HttpSession httpSession) {
        String ip = request.getRemoteAddr();

        if (captcha != null && captcha.equals(redisTemplate.opsForValue().get(ip + email) + "")) {

            if (role == 0) {
                Teacher t = teacherService.selectByEmail(email);
                if (t != null) {
                    httpSession.setAttribute(Role.Teacher + "", t);
                    return News.success();
                }
            } else if (role == 1) {
                Admin a = adminService.selectByEmail(email);
                if (a != null) {
                    httpSession.setAttribute(Role.Admin + "", a);
                    return News.success();
                }

            }
        }
        this.limit(email);
        return News.fail("验证码错误");

    }

    @RequestMapping("/send")
    @Login
    @ResponseBody
    public Map send(String email, HttpServletRequest request) {

        String ip = request.getRemoteAddr();

        if (!Check.isEmail(email)) {
            return News.fail("邮箱格式有问题");
        }
        String captcha = new Random().nextInt(1000000) + "";

        redisTemplate.opsForValue().set(ip + email, captcha, 60, TimeUnit.SECONDS);

        SendEmail.send(email, "OBE 系统", captcha);

        return News.success();

    }


}
