package com.dbsy.obe.controller;

import com.dbsy.obe.pojo.Department;
import com.dbsy.obe.service.DepartmentService;
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
@RequestMapping("/department")
public class DepartmentController {

    @Autowired
    @Qualifier("departmentServiceImp")
    DepartmentService departmentService;


    @RequestMapping("")
    public String department() {
        return "baseInfo/department";
    }


    @RequestMapping("/list")
    @ResponseBody
    public Map list(Map map) {
        Map m = new HashMap();
        m.put("total", departmentService.listCount(map));
        m.put("rows", departmentService.list(map));
        return m;
    }

    @ResponseBody
    @RequestMapping("/remove/{id}")
    public Map remove(@PathVariable("id") int id) {
        if (departmentService.delete(id) > 0) {
            return News.success();
        }
        return News.fail("删除失败");
    }

    @ResponseBody
    @RequestMapping("/batchRemove")
    public Map batchRemove(int[] ids) {

        if (departmentService.batchRemove(ids) == ids.length) {
            return News.success();
        }
        return News.fail("删除失败");
    }

    @ResponseBody
    @RequestMapping("/insert")
    public Map insert(Department department) {
        if (departmentService.insert(department) > 0) {
            return News.success();
        }
        return News.fail("添加失败");
    }

    @ResponseBody
    @RequestMapping("/update")
    public Map update(Department department) {
        if (departmentService.update(department) > 0) {
            return News.success();
        }
        return News.fail("添加失败");
    }

    @ResponseBody
    @RequestMapping("/get/{id}")
    public Map get(@PathVariable("id") int id) {
        return News.success("成功", departmentService.get(id));
    }

    @RequestMapping("/getAll")
    @ResponseBody
    public Map getAll() {
        return News.success("学院数据请求成功", departmentService.getAll());
    }


}
