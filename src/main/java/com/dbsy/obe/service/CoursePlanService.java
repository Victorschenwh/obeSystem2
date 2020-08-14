package com.dbsy.obe.service;

import com.dbsy.obe.pojo.CoursePlan;

import java.util.List;
import java.util.Map;

public interface CoursePlanService {

    int insert(CoursePlan record);

    int insertSelective(CoursePlan record);

    int listCount(Map map);

    List<CoursePlan> list(Map map);

    CoursePlan get(int id);

    int delete(int id);

    int update(CoursePlan coursePlan);

    int batchRemove(int[] ids);

    List<CoursePlan> getAll();
}
