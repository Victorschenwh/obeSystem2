package com.dbsy.obe.service;

import com.dbsy.obe.pojo.Target;

import java.util.List;
import java.util.Map;

public interface TargetService {
    int insert(Target record);

    int insertSelective(Target record);

    int listCount(Map map);

    List<Target> list(Map map);

    Target get(int id);

    int delete(int id);

    int update(Target target);

    int batchRemove(int[] ids);

    List<Target> getAll();
}
