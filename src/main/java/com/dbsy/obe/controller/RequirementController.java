package com.dbsy.obe.controller;

import com.dbsy.obe.pojo.Requirement;
import com.dbsy.obe.service.RequirementService;
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
@RequestMapping("/requirement")
public class RequirementController {
    @Autowired
    @Qualifier("requirementServiceImp")
    RequirementService requirementService;


    @RequestMapping("")
    public String requirement() {
        return "baseInfo/requirement";
    }


    @RequestMapping("/list")
    @ResponseBody
    public Map list(Map map) {
        Map m = new HashMap();
        m.put("total", requirementService.listCount(map));
        m.put("rows", requirementService.list(map));
        return m;
    }

    @ResponseBody
    @RequestMapping("/remove/{id}")
    public Map remove(@PathVariable("id") int id) {
        if (requirementService.delete(id) > 0) {
            return News.success();
        }
        return News.fail("删除失败");
    }

    @ResponseBody
    @RequestMapping("/batchRemove")
    public Map batchRemove(int[] ids) {

        if (requirementService.batchRemove(ids) == ids.length) {
            return News.success();
        }
        return News.fail("删除失败");
    }

    @ResponseBody
    @RequestMapping("/insert")
    public Map insert(Requirement requirement) {
        if (requirementService.insert(requirement) > 0) {
            return News.success();
        }
        return News.fail("添加失败");
    }

    @ResponseBody
    @RequestMapping("/update")
    public Map update(Requirement requirement) {
        if (requirementService.update(requirement) > 0) {
            return News.success();
        }
        return News.fail("添加失败");
    }

    @ResponseBody
    @RequestMapping("/get/{id}")
    public Map get(@PathVariable("id") int id) {
        return News.success("成功", requirementService.get(id));
    }

    @ResponseBody
    @RequestMapping("/getAll")
    public Map getAll() {
        return News.success("成功", requirementService.getAll());
    }

}
