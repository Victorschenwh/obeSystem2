package com.dbsy.obe.controller;

import com.dbsy.obe.annotation.Authority;
import com.dbsy.obe.myenum.Role;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/index")
public class IndexController {

    @RequestMapping("")
    @Authority({Role.Teacher, Role.Admin})
    public String index() {
        return "index";
    }

}
