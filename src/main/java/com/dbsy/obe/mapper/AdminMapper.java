package com.dbsy.obe.mapper;

import com.dbsy.obe.pojo.Admin;
import com.dbsy.obe.pojo.Teacher;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface AdminMapper {
    int insert(Admin record);

    int insertSelective(Admin record);

    @Select("select * from admin where username = #{username} and password = #{password}")
    Admin selectByUsernameAndPassword(String username, String password);

}