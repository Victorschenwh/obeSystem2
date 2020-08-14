package com.dbsy.obe.service;

import com.dbsy.obe.pojo.Plan;
import com.dbsy.obe.pojo.Point;

import java.util.List;
import java.util.Map;

public interface PointService {
    int insert(Point record);

    int insertSelective(Point record);

    int listCount(Map map);

    List<Point> list(Map map);

    Point get(int id);

    int delete(int id);

    int update(Point point);

    int batchRemove(int[] ids);

    List<Point> getAll();

    List<Point> getPointsByRequirementId(int requirementId);
}
