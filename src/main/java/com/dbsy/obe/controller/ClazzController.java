package com.dbsy.obe.controller;

import com.dbsy.obe.annotation.Authority;
import com.dbsy.obe.myenum.Role;
import com.dbsy.obe.pojo.Clazz;
import com.dbsy.obe.service.ClazzService;
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
@RequestMapping("/clazz")
public class ClazzController {

    @Autowired
    @Qualifier("clazzServiceImp")
    ClazzService clazzService;

    @RequestMapping("")
    public String clazz() {
        return "baseInfo/clazz";
    }


    @RequestMapping("/list")
    @ResponseBody
    public Map list(Map map) {
        Map m = new HashMap();
        m.put("total", clazzService.listCount(map));
        m.put("rows", clazzService.list(map));
        return m;
    }



    @ResponseBody
    @RequestMapping("/remove/{id}")
    public Map remove(@PathVariable("id") int id) {
        if (clazzService.delete(id) > 0) {
            return News.success();
        }
        return News.fail("删除失败");
    }

    @ResponseBody
    @RequestMapping("/batchRemove")
    public Map batchRemove(int[] ids) {

        if (clazzService.batchRemove(ids) == ids.length) {
            return News.success();
        }
        return News.fail("删除失败");
    }

    @ResponseBody
    @RequestMapping("/insert")
    public Map insert(Clazz clazz) {
        if (clazzService.insert(clazz) > 0) {
            return News.success();
        }
        return News.fail("添加失败");
    }

    @ResponseBody
    @RequestMapping("/update")
    public Map update(Clazz clazz) {
        if (clazzService.update(clazz) > 0) {
            return News.success();
        }
        return News.fail("添加失败");
    }

    @ResponseBody
    @RequestMapping("/get/{id}")
    public Map get(@PathVariable("id") int id) {
        return News.success("成功", clazzService.get(id));
    }

    @ResponseBody
    @RequestMapping("/getAll")
    public Map getAll() {
        return News.success("成功", clazzService.getAll());
    }
}
