package com.dbsy.obe.controller;
import com.dbsy.obe.annotation.Authority;
import com.dbsy.obe.myenum.Role;
import com.dbsy.obe.pojo.Point;
import com.dbsy.obe.service.PointService;
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
@RequestMapping("/point")
//@Authority({Role.Admin})
public class PointController {
    @Autowired
    @Qualifier("pointServiceImp")
    PointService pointService;

    @RequestMapping("")
    public String major() {
        return "baseInfo/point";
    }


//    @Authority({Role.Teacher})
    @RequestMapping("/list")
    @ResponseBody
    public Map list(Map map) {
        Map m = new HashMap();
        m.put("total", pointService.listCount(map));
        m.put("rows", pointService.list(map));
        return m;
    }

    @ResponseBody
    @RequestMapping("/remove/{id}")
    public Map remove(@PathVariable("id") int id) {
        if (pointService.delete(id) > 0) {
            return News.success();
        }
        return News.fail("删除失败");
    }

    @ResponseBody
    @RequestMapping("/batchRemove")
    public Map batchRemove(int[] ids) {

        if (pointService.batchRemove(ids) == ids.length) {
            return News.success();
        }
        return News.fail("删除失败");
    }

    @ResponseBody
    @RequestMapping("/insert")
    public Map insert(Point point) {
        if (pointService.insert(point) > 0) {
            return News.success();
        }
        return News.fail("添加失败");
    }

    @ResponseBody
    @RequestMapping("/update")
    public Map update(Point point) {
        if (pointService.update(point) > 0) {
            return News.success();
        }
        return News.fail("添加失败");
    }

//    @Authority({Role.Teacher})
    @ResponseBody
    @RequestMapping("/get/{id}")
    public Map get(@PathVariable("id") int id) {
        return News.success("成功", pointService.get(id));
    }


//    @Authority({Role.Teacher})
    @ResponseBody
    @RequestMapping("/getAll")
    public Map getAll() {
        return News.success("成功", pointService.getAll());
    }

//    @Authority({Role.Teacher})
    @ResponseBody
    @RequestMapping("/getPointsByRequirementId/{requirementId}")
    public Map getPointsByRequirementId(@PathVariable("requirementId") int requiremntId){
        if (pointService.getPointsByRequirementId(requiremntId) != null) {
            return News.success("成功",pointService.getPointsByRequirementId(requiremntId));

        }
        return News.fail("查找失败");
    }
}
