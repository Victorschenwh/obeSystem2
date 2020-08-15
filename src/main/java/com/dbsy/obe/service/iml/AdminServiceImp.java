package com.dbsy.obe.service.iml;

import com.dbsy.obe.mapper.AdminMapper;
import com.dbsy.obe.pojo.Admin;
import com.dbsy.obe.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("adminServiceImp")
public class AdminServiceImp implements AdminService {
    @Autowired
    AdminMapper adminMapper;

    @Override
    public int insert(Admin record) {
        return adminMapper.insert(record);
    }

    @Override
    public int insertSelective(Admin record) {
        return adminMapper.insertSelective(record);
    }

    @Override
    public Admin selectByUsernameAndPassword(String username, String password) {
        return adminMapper.selectByUsernameAndPassword(username, password);
    }

    @Override
    public Admin selectByEmail(String email) {
        return adminMapper.selectByEmail(email);
    }
}
