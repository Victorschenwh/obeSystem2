package com.dbsy.obe.mapper;

import com.dbsy.obe.pojo.Department;
import com.dbsy.obe.pojo.Major;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Map;

@Mapper
public interface MajorMapper {
    int insert(Major record);

    int insertSelective(Major record);

    int listCount(Map map);

    List<Major> list(Map map);

    @Select("select * from major where id = #{id}")
    Major get(int id);

    @Delete("delete from major where id = #{id}")
    int delete(int id);

    @Update("update major set name=#{name},introduce=#{introduce},department_id=#{departmentId} where id=#{id}")
    int update(Major major);

    int batchRemove(int[] ids);

    @Select("select * from major")
    List<Major> getAll();

    @Select("select * from major where department_id=#{departmentId}")
    List<Major> getMajorsByDpartmentId(int departmentId);
}