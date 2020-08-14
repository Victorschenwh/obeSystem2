package com.dbsy.obe.mapper;

import com.dbsy.obe.pojo.Clazz;
import com.dbsy.obe.pojo.Teacher;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Map;

@Mapper
public interface ClazzMapper {
    int insert(Clazz record);

    int insertSelective(Clazz record);

    int listCount(Map map);

    List<Clazz> list(Map map);

    @Select("select * from clazz where id = #{id}")
    Clazz get(int id);

    @Delete("delete from clazz where id = #{id}")
    int delete(int id);

    @Update("update clazz set name=#{name},introduce=#{introduce},major_id=#{majorId} where id=#{id}")
    int update(Clazz clazz);

    int batchRemove(int[] ids);

    @Select("select * from clazz")
    List<Clazz> getAll();
}