package com.dbsy.obe.controller;

import com.dbsy.obe.annotation.Login;
import com.dbsy.obe.myenum.Role;
import com.dbsy.obe.pojo.Admin;
import com.dbsy.obe.pojo.Teacher;
import com.dbsy.obe.service.AdminService;
import com.dbsy.obe.service.TeacherService;
import com.dbsy.obe.util.News;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
@RequestMapping("/login")
public class LoginController {

    @Autowired
    @Qualifier("teacherServiceImp")
    TeacherService teacherService;

    @Autowired
    @Qualifier("adminServiceImp")
    AdminService adminService;

    @RequestMapping("/teacher")
    @Login
    @ResponseBody
    public Map teacher(String username, String password, HttpSession httpSession) {
        Teacher teacher = teacherService.selectByUsernameAndPassword(username, password);

        if (teacher == null) {
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
            return News.fail("用户名或密码不正确");
        }

        if (!admin.getIsLock()) {
            httpSession.setAttribute(Role.Admin + "", admin);
            return News.success();
        } else {
            return News.fail("操作过于频繁,被锁定,请联系管理员!");
        }

    }
}
