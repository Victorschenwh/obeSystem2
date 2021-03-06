package com.dbsy.obe.controller;

import com.dbsy.obe.annotation.Authority;
import com.dbsy.obe.myenum.Role;
import com.dbsy.obe.pojo.CoursePlan;
import com.dbsy.obe.service.CoursePlanService;
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
@RequestMapping("/coursePlan")
@Authority({Role.Admin})
public class CoursePlanController {

    @Autowired
    @Qualifier("coursePlanServiceImp")
    CoursePlanService coursePlanService;



    @Authority({Role.Teacher})
    @RequestMapping("")
    public String coursePlan() {
        return "baseInfo/coursePlan";
    }


    @Authority({Role.Teacher})
    @RequestMapping("/list")
    @ResponseBody
    public Map list(Map map) {
        Map m = new HashMap();
        m.put("total", coursePlanService.listCount(map));
        m.put("rows", coursePlanService.list(map));
        return m;
    }



    @ResponseBody
    @RequestMapping("/remove/{id}")
    public Map remove(@PathVariable("id") int id) {
        if (coursePlanService.delete(id) > 0) {
            return News.success();
        }
        return News.fail("删除失败");
    }

    @ResponseBody
    @RequestMapping("/batchRemove")
    public Map batchRemove(int[] ids) {

        if (coursePlanService.batchRemove(ids) == ids.length) {
            return News.success();
        }
        return News.fail("删除失败");
    }

    @ResponseBody
    @RequestMapping("/insert")
    public Map insert(CoursePlan coursePlan) {
        if (coursePlanService.insert(coursePlan) > 0) {
            return News.success();
        }
        return News.fail("添加失败");
    }

    @ResponseBody
    @RequestMapping("/update")
    public Map update(CoursePlan coursePlan) {
        if (coursePlanService.update(coursePlan) > 0) {
            return News.success();
        }
        return News.fail("添加失败");
    }

    @Authority({Role.Teacher})
    @ResponseBody
    @RequestMapping("/get/{id}")
    public Map get(@PathVariable("id") int id) {
        return News.success("成功", coursePlanService.get(id));
    }

    @Authority({Role.Teacher})
    @ResponseBody
    @RequestMapping("/getAll")
    public Map getAll() {
        return News.success("成功", coursePlanService.getAll());
    }


    @Authority({Role.Teacher})
    @ResponseBody
    @RequestMapping("/getCoursePlanByCourseId/{courseId}")
    public Map getCoursePlanByCourseId(@PathVariable("courseId") int courseId){
        List list = coursePlanService.getCoursePlanByCourseId(courseId);
        if (list != null) {
            return News.success("成功",list);

        }
        return News.fail("查找失败");
    }


}
