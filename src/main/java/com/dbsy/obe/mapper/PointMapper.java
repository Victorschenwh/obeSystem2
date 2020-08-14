package com.dbsy.obe.mapper;

import com.dbsy.obe.pojo.Plan;
import com.dbsy.obe.pojo.Point;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Map;

@Mapper
public interface PointMapper {
    int insert(Point record);

    int insertSelective(Point record);

    int listCount(Map map);

    List<Point> list(Map map);

    @Select("select * from point where id = #{id}")
    Point get(int id);

    @Delete("delete from point where id = #{id}")
    int delete(int id);

    @Update("update point set introduction=#{introduction},synopsis=#{synopsis},requirement_id=#{requirementId} where id=#{id}")
    int update(Point point);

    int batchRemove(int[] ids);

    @Select("select * from point")
    List<Point> getAll();

    @Select("select * from point where requirement_id=#{requirementId}")
    List<Point> getPointsByRequirementId(int requirementId);
}