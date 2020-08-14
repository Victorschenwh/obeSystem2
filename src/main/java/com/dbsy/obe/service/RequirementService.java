package com.dbsy.obe.service;

import com.dbsy.obe.pojo.Requirement;

import java.util.List;
import java.util.Map;

public interface RequirementService {
    int insert(Requirement record);

    int insertSelective(Requirement record);

    int listCount(Map map);

    List<Requirement> list(Map map);

    Requirement get(int id);

    int delete(int id);

    int update(Requirement requirement);

    int batchRemove(int[] ids);

    List<Requirement> getAll();
}
