package com.dbsy.obe.mapper;

import com.dbsy.obe.pojo.Title;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Map;

@Mapper
public interface TitleMapper {
    int insert(Title record);

    int insertSelective(Title record);

    int listCount(Map map);

    List<Title> list(Map map);

    @Select("select * from title where id = #{id}")
    Title get(int id);

    @Delete("delete from title where id = #{id}")
    int delete(int id);

    @Update("update title set name=#{name},introduce=#{introduce}, where id=#{id}")
    int update(Title title);

    int batchRemove(int[] ids);

    @Select("select * from title")
    List<Title> getAll();

}