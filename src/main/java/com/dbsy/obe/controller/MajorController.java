package com.dbsy.obe.controller;

import com.dbsy.obe.annotation.Authority;
import com.dbsy.obe.myenum.Role;
import com.dbsy.obe.pojo.Major;
import com.dbsy.obe.service.MajorService;
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
@RequestMapping("/major")
//@Authority({Role.Admin})
public class MajorController {
    @Autowired
    @Qualifier("majorServiceImp")
    MajorService majorService;

    @RequestMapping("")
    public String major() {
        return "baseInfo/major";
    }


//    @Authority({Role.Teacher})
    @RequestMapping("/list")
    @ResponseBody
    public Map list(Map map) {
        Map m = new HashMap();
        m.put("total", majorService.listCount(map));
        m.put("rows", majorService.list(map));
        return m;
    }

    @ResponseBody
    @RequestMapping("/remove/{id}")
    public Map remove(@PathVariable("id") int id) {
        if (majorService.delete(id) > 0) {
            return News.success();
        }
        return News.fail("删除失败");
    }

    @ResponseBody
    @RequestMapping("/batchRemove")
    public Map batchRemove(int[] ids) {

        if (majorService.batchRemove(ids) == ids.length) {
            return News.success();
        }
        return News.fail("删除失败");
    }

    @ResponseBody
    @RequestMapping("/insert")
    public Map insert(Major major) {
        if (majorService.insert(major) > 0) {
            return News.success();
        }
        return News.fail("添加失败");
    }

    @ResponseBody
    @RequestMapping("/update")
    public Map update(Major major) {
        if (majorService.update(major) > 0) {
            return News.success();
        }
        return News.fail("添加失败");
    }

//    @Authority({Role.Teacher})
    @ResponseBody
    @RequestMapping("/get/{id}")
    public Map get(@PathVariable("id") int id) {
        return News.success("成功", majorService.get(id));
    }

//    @Authority({Role.Teacher})
    @ResponseBody
    @RequestMapping("/getAll")
    public Map getAll() {
        return News.success("成功", majorService.getAll());
    }


//    @Authority({Role.Teacher})
    @ResponseBody
    @RequestMapping("/getMajorsByDpartmentId/{departmentId}")
    public Map getMajorsByDpartmentId(@PathVariable("departmentId") int departmentId){
        if (majorService.getMajorsByDpartmentId(departmentId) != null) {
            return News.success("成功",majorService.getMajorsByDpartmentId(departmentId));

        }
        return News.fail("查找失败");
    }


}
