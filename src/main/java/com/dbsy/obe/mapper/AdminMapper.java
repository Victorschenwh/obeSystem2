package com.dbsy.obe.mapper;

import com.dbsy.obe.pojo.Admin;

public interface AdminMapper {
    int insert(Admin record);

    int insertSelective(Admin record);
}