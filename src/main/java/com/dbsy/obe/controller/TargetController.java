package com.dbsy.obe.controller;

import com.dbsy.obe.annotation.Authority;
import com.dbsy.obe.myenum.Role;
import com.dbsy.obe.pojo.Target;
import com.dbsy.obe.service.TargetService;
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
@RequestMapping("/target")
@Authority({Role.Admin})
public class TargetController {
    @Autowired
    @Qualifier("targetServiceImp")
    TargetService targetService;

    @Authority({Role.Teacher})
    @RequestMapping("")
    public String target() {
        return "baseInfo/target";
    }


    @Authority({Role.Teacher})
    @RequestMapping("/list")
    @ResponseBody
    public Map list(Map map) {
        Map m = new HashMap();
        m.put("total", targetService.listCount(map));
        m.put("rows", targetService.list(map));
        return m;
    }

    @Authority({Role.Teacher})
    @ResponseBody
    @RequestMapping("/remove/{id}")
    public Map remove(@PathVariable("id") int id) {
        if (targetService.delete(id) > 0) {
            return News.success();
        }
        return News.fail("删除失败");
    }

    @ResponseBody
    @RequestMapping("/batchRemove")
    public Map batchRemove(int[] ids) {

        if (targetService.batchRemove(ids) == ids.length) {
            return News.success();
        }
        return News.fail("删除失败");
    }

    @ResponseBody
    @RequestMapping("/insert")
    public Map insert(Target target) {
        if (targetService.insert(target) > 0) {
            return News.success();
        }
        return News.fail("添加失败");
    }

    @ResponseBody
    @RequestMapping("/update")
    public Map update(Target target) {
        if (targetService.update(target) > 0) {
            return News.success();
        }
        return News.fail("编辑失败");
    }

    @Authority({Role.Teacher})
    @ResponseBody
    @RequestMapping("/get/{id}")
    public Map get(@PathVariable("id") int id) {
        return News.success("成功", targetService.get(id));
    }

    @Authority({Role.Teacher})
    @ResponseBody
    @RequestMapping("/getAll")
    public Map getAll() {
        return News.success("成功", targetService.getAll());
    }

    @Authority({Role.Teacher})
    @ResponseBody
    @RequestMapping("/getTargetsByPlanId/{planId}")
    public Map getTargetsByPlanId(@PathVariable("planId") int planId) {
        List list = targetService.getTargetsByPlanId(planId);
        if (list != null) {
            return News.success("成功", list);

        }
        return News.fail("查找失败");
    }

}
