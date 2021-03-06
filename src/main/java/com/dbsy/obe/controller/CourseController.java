package com.dbsy.obe.controller;


import com.dbsy.obe.annotation.Authority;
import com.dbsy.obe.myenum.Role;
import com.dbsy.obe.pojo.Course;
import com.dbsy.obe.service.CourseService;
import com.dbsy.obe.util.News;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/course")
@Authority({Role.Admin})
public class CourseController {

    @Autowired
    @Qualifier("courseServiceImp")
    CourseService courseService;

    @Authority({Role.Teacher})
    @RequestMapping("")
    public String course() {
        return "baseInfo/course";
    }


    @Authority({Role.Teacher})
    @RequestMapping("/list")
    @ResponseBody
    public Map list(Map map) {
        Map m = new HashMap();
        m.put("total", courseService.listCount(map));
        m.put("rows", courseService.list(map));
        return m;
    }




    @ResponseBody
    @RequestMapping("/remove/{id}")
    public Map remove(@PathVariable("id") int id) {
        if (courseService.delete(id) > 0) {
            return News.success();
        }
        return News.fail("删除失败");
    }

    @ResponseBody
    @RequestMapping("/batchRemove")
    public Map batchRemove(int[] ids) {

        if (courseService.batchRemove(ids) == ids.length) {
            return News.success();
        }
        return News.fail("删除失败");
    }

    @ResponseBody
    @RequestMapping("/insert")
    public Map insert(Course course) {
        if (courseService.insert(course) > 0) {
            return News.success();
        }
        return News.fail("添加失败");
    }

    @ResponseBody
    @RequestMapping("/update")
    public Map update(Course course) {
        if (courseService.update(course) > 0) {
            return News.success();
        }
        return News.fail("添加失败");
    }

    @Authority({Role.Teacher})
    @ResponseBody
    @RequestMapping("/get/{id}")
    public Map get(@PathVariable("id") int id) {
        return News.success("成功", courseService.get(id));
    }

    @Authority({Role.Teacher})
    @ResponseBody
    @RequestMapping("/getAll")
    public Map getAll() {
        return News.success("成功", courseService.getAll());
    }

    @Authority({Role.Teacher})
    @ResponseBody
    @RequestMapping("/getCourseByDepartmentId/{departmentId}")
    public Map getCourseBydepartmentId(@PathVariable("departmentId") int departmentId){
        List list = courseService.getCourseByDepartmentId(departmentId);
        if (list != null) {
            return News.success("成功",list);

        }
        return News.fail("查找失败");
    }

}
