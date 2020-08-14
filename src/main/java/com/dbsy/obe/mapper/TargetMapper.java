package com.dbsy.obe.mapper;

import com.dbsy.obe.pojo.Target;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Map;

@Mapper
public interface TargetMapper {
    int insert(Target record);

    int insertSelective(Target record);

    int listCount(Map map);

    List<Target> list(Map map);

    @Select("select * from target where id = #{id}")
    Target get(int id);

    @Delete("delete from target where id = #{id}")
    int delete(int id);

    @Update("update requirement set synopsis=#{synopsis},introduction=#{introduction},plan_id=#{planId}, where id=#{id}")
    int update(Target target);

    int batchRemove(int[] ids);

    @Select("select * from target")
    List<Target> getAll();
}