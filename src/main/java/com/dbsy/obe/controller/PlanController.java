package com.dbsy.obe.controller;
import com.dbsy.obe.annotation.Authority;
import com.dbsy.obe.myenum.Role;
import com.dbsy.obe.pojo.Plan;
import com.dbsy.obe.service.PlanService;
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
@RequestMapping("/plan")
//@Authority({Role.Admin})
public class PlanController {
    @Autowired
    @Qualifier("planServiceImp")
    PlanService planService;

    @RequestMapping("")
    public String major() {
        return "baseInfo/plan";
    }


//    @Authority({Role.Teacher})
    @RequestMapping("/list")
    @ResponseBody
    public Map list(Map map) {
        Map m = new HashMap();
        m.put("total", planService.listCount(map));
        m.put("rows", planService.list(map));
        return m;
    }

    @ResponseBody
    @RequestMapping("/remove/{id}")
    public Map remove(@PathVariable("id") int id) {
        if (planService.delete(id) > 0) {
            return News.success();
        }
        return News.fail("删除失败");
    }

    @ResponseBody
    @RequestMapping("/batchRemove")
    public Map batchRemove(int[] ids) {

        if (planService.batchRemove(ids) == ids.length) {
            return News.success();
        }
        return News.fail("删除失败");
    }

    @ResponseBody
    @RequestMapping("/insert")
    public Map insert(Plan plan) {
        if (planService.insert(plan) > 0) {
            return News.success();
        }
        return News.fail("添加失败");
    }

    @ResponseBody
    @RequestMapping("/update")
    public Map update(Plan plan) {
        if (planService.update(plan) > 0) {
            return News.success();
        }
        return News.fail("添加失败");
    }

//    @Authority({Role.Teacher})
    @ResponseBody
    @RequestMapping("/get/{id}")
    public Map get(@PathVariable("id") int id) {
        return News.success("成功", planService.get(id));
    }

    @Authority({Role.Teacher})
    @ResponseBody
    @RequestMapping("/getAll")
    public Map getAll() {
        return News.success("成功", planService.getAll());
    }

//    @Authority({Role.Teacher})
    @ResponseBody
    @RequestMapping("/getPlansByMajorId/{majorId}")
    public Map getPlansByMajorId(@PathVariable("majorId") int majorId){
        if (planService.getPlansByMajorId(majorId) != null) {
            return News.success("成功",planService.getPlansByMajorId(majorId));

        }
        return News.fail("查找失败");
    }

}
