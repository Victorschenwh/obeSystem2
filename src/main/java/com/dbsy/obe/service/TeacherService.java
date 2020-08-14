package com.dbsy.obe.service;

import com.dbsy.obe.pojo.Teacher;

import java.util.List;
import java.util.Map;

public interface TeacherService {
    int insert(Teacher record);

    int insertSelective(Teacher record);

    int listCount(Map map);

    List<Teacher> list(Map map);

    Teacher get(int id);

    int delete(int id);

    int update(Teacher teacher);

    int batchRemove(int[] ids);

    List<Teacher> getAll();
}
