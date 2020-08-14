package com.dbsy.obe.mapper;

import com.dbsy.obe.pojo.Teacher;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Map;

@Mapper
public interface TeacherMapper {
    int insert(Teacher record);

    int insertSelective(Teacher record);

    int listCount(Map map);

    List<Teacher> list(Map map);

    @Select("select * from teacher where id = #{id}")
    Teacher get(int id);

    @Delete("delete from teacher where id = #{id}")
    int delete(int id);

    @Update("update teacher set username=#{username},password=#{password},name=#{name},gender=#{gender},birthday=#{birthday},title_id=#{titleId},department_id=#{departmentId},post=#{post} where id=#{id}")
    int update(Teacher teacher);

    int batchRemove(int[] ids);

    @Select("select * from teacher")
    List<Teacher> getAll();
}