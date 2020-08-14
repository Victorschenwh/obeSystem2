package com.dbsy.obe.service;

import com.dbsy.obe.pojo.Department;

import java.util.List;
import java.util.Map;

public interface DepartmentService {
    int insert(Department record);

    int insertSelective(Department record);

    Department get(int id);

    int listCount(Map map);

    List<Department> list(Map map);

    int delete(int id);

    int update(Department department);

    int batchRemove(int[] ids);

    List<Department> getAll();
}
