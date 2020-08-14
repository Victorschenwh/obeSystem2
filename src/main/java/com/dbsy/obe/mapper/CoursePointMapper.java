package com.dbsy.obe.mapper;

import com.dbsy.obe.pojo.CoursePoint;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Map;

@Mapper
public interface CoursePointMapper {
    int insert(CoursePoint record);

    int insertSelective(CoursePoint record);

    int listCount(Map map);

    List<CoursePoint> list(Map map);

    @Select("select * from course_point where id = #{id}")
    CoursePoint get(int id);

    @Delete("delete from course_point where id = #{id}")
    int delete(int id);

    @Update("update course_point set course_id=#{courseId},point_id=#{pointId},weight=#{weight} where id=#{id}")
    int update(CoursePoint coursePoint);

    int batchRemove(int[] ids);

    @Select("select * from course_point")
    List<CoursePoint> getAll();
}