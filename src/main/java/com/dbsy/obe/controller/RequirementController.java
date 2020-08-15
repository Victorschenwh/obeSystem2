package com.dbsy.obe.controller;

import com.dbsy.obe.annotation.Authority;
import com.dbsy.obe.myenum.Role;
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
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/requirement")
@Authority({Role.Admin})
public class RequirementController {


    @Autowired
    @Qualifier("requirementServiceImp")
    RequirementService requirementService;

    @Authority({Role.Teacher})
    @RequestMapping("")
    public String requirement() {
        return "baseInfo/requirement";
    }


    @Authority({Role.Teacher})
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
        return News.fail("编辑失败");
    }

    @Authority({Role.Teacher})
    @ResponseBody
    @RequestMapping("/get/{id}")
    public Map get(@PathVariable("id") int id) {
        return News.success("成功", requirementService.get(id));
    }

    @Authority({Role.Teacher})
    @ResponseBody
    @RequestMapping("/getAll")
    public Map getAll() {
        return News.success("成功", requirementService.getAll());
    }

    @Authority({Role.Teacher})
    @ResponseBody
    @RequestMapping("/getRequirementsByPlanId/{planId}")
    public Map getRequirementsByPlanId(@PathVariable("planId") int planId) {
        List list = requirementService.getRequirementsByPlanId(planId);
        if (list != null) {
            return News.success("成功", list);

        }
        return News.fail("查找失败");
    }

}
