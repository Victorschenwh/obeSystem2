package com.dbsy.obe.mapper;

import com.dbsy.obe.pojo.Department;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Map;

@Mapper
public interface DepartmentMapper {
    int insert(Department record);

    int insertSelective(Department record);

    int listCount(Map map);

    List<Department> list(Map map);

    @Select("select * from department where id = #{id}")
    Department get(int id);

    @Delete("delete from department where id = #{id}")
    int delete(int id);

    @Update("update department set name=#{name},introduce=#{introduce} where id=#{id}")
    int update(Department department);

    int batchRemove(int[] ids);

    @Select("select * from department")
    List<Department> getAll();
}