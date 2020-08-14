package com.dbsy.obe.service;


import com.dbsy.obe.pojo.Course;

import java.util.List;
import java.util.Map;

public interface CourseService {

    int insert(Course record);

    int insertSelective(Course record);

    int listCount(Map map);

    List<Course> list(Map map);

    Course get(int id);

    int delete(int id);

    int update(Course course);

    int batchRemove(int[] ids);

    List<Course> getAll();
}
