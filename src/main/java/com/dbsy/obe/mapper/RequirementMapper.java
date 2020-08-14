package com.dbsy.obe.mapper;

import com.dbsy.obe.pojo.Requirement;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Map;

@Mapper
public interface RequirementMapper {
    int insert(Requirement record);

    int insertSelective(Requirement record);

    int listCount(Map map);

    List<Requirement> list(Map map);

    @Select("select * from requirement where id = #{id}")
    Requirement get(int id);

    @Delete("delete from requirement where id = #{id}")
    int delete(int id);

    @Update("update requirement set synopsis=#{synopsis},introduction=#{introduction},plan_id=#{planId}, where id=#{id}")
    int update(Requirement requirement);

    int batchRemove(int[] ids);

    @Select("select * from requirement")
    List<Requirement> getAll();
}