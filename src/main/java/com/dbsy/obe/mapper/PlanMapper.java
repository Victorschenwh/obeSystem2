package com.dbsy.obe.mapper;

import com.dbsy.obe.pojo.Major;
import com.dbsy.obe.pojo.Plan;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Map;

@Mapper
public interface PlanMapper {
    int insert(Plan record);

    int insertSelective(Plan record);

    int listCount(Map map);

    List<Plan> list(Map map);

    @Select("select * from plan where id = #{id}")
    Plan get(int id);

    @Delete("delete from plan where id = #{id}")
    int delete(int id);

    @Update("update major set name=#{name},year=#{year},introduce=#{introduce},major_id=#{majorId} where id=#{id}")
    int update(Plan plan);

    int batchRemove(int[] ids);

    @Select("select * from plan")
    List<Plan> getAll();

    @Select("select * from plan where major_id =#{majorId}")
    List<Plan> getPlansByMajorId(int majorId);


}