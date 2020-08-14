package com.dbsy.obe.service;

import com.dbsy.obe.pojo.Admin;

public interface AdminService {

    int insert(Admin record);

    int insertSelective(Admin record);

    Admin selectByUsernameAndPassword(String username, String password);
}
