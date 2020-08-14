package com.dbsy.obe.service;

import com.dbsy.obe.pojo.Clazz;
import com.dbsy.obe.pojo.Major;
import com.dbsy.obe.pojo.Teacher;

import java.util.List;
import java.util.Map;

public interface ClazzService {
    int insert(Clazz record);

    int insertSelective(Clazz record);

    int listCount(Map map);

    List<Clazz> list(Map map);

    Clazz get(int id);

    int delete(int id);

    int update(Clazz clazz);

    int batchRemove(int[] ids);

    List<Clazz> getAll();
}
