package com.dbsy.obe.service;


import com.dbsy.obe.pojo.Major;
import com.dbsy.obe.pojo.Plan;

import java.util.List;
import java.util.Map;

public interface PlanService {
    int insert(Plan record);

    int insertSelective(Plan record);

    int listCount(Map map);

    List<Plan> list(Map map);

    Plan get(int id);

    int delete(int id);

    int update(Plan plan);

    int batchRemove(int[] ids);

    List<Plan> getAll();

    List<Plan> getPlansByMajorId(int majorId);
}
