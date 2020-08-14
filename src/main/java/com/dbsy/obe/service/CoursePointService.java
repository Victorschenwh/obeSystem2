package com.dbsy.obe.service;

import com.dbsy.obe.pojo.CoursePoint;

import java.util.List;
import java.util.Map;

public interface CoursePointService {
    int insert(CoursePoint record);

    int insertSelective(CoursePoint record);

    CoursePoint get(int id);

    int listCount(Map map);

    List<CoursePoint> list(Map map);

    int delete(int id);

    int update(CoursePoint coursePoint);

    int batchRemove(int[] ids);

    List<CoursePoint> getAll();
}
