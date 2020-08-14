package com.dbsy.obe.controller;

import com.dbsy.obe.pojo.CoursePoint;
import com.dbsy.obe.service.CoursePointService;
import com.dbsy.obe.util.News;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/coursePoint")
public class CoursePointController {

    @Autowired
    @Qualifier("CoursePointServiceImp")
    CoursePointService coursePointService;


    @RequestMapping("")
    public String coursePoint() {
        return "baseInfo/coursePoint";
    }


    @RequestMapping("/list")
    @ResponseBody
    public Map list(Map map) {
        Map m = new HashMap();
        m.put("total", coursePointService.listCount(map));
        m.put("rows", coursePointService.list(map));
        return m;
    }

    @ResponseBody
    @RequestMapping("/remove/{id}")
    public Map remove(@PathVariable("id") int id) {
        if (coursePointService.delete(id) > 0) {
            return News.success();
        }
        return News.fail("删除失败");
    }

    @ResponseBody
    @RequestMapping("/batchRemove")
    public Map batchRemove(int[] ids) {

        if (coursePointService.batchRemove(ids) == ids.length) {
            return News.success();
        }
        return News.fail("删除失败");
    }

    @ResponseBody
    @RequestMapping("/insert")
    public Map insert(CoursePoint coursePoint) {
        if (coursePointService.insert(coursePoint) > 0) {
            return News.success();
        }
        return News.fail("添加失败");
    }

    @ResponseBody
    @RequestMapping("/update")
    public Map update(CoursePoint coursePoint) {
        if (coursePointService.update(coursePoint) > 0) {
            return News.success();
        }
        return News.fail("添加失败");
    }

    @ResponseBody
    @RequestMapping("/get/{id}")
    public Map get(@PathVariable("id") int id) {
        return News.success("成功", coursePointService.get(id));
    }

    @RequestMapping("/getAll")
    @ResponseBody
    public Map getAll() {
        return News.success("成功", coursePointService.getAll());
    }


}
