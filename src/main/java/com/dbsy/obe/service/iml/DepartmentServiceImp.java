package com.dbsy.obe.service.iml;

import com.dbsy.obe.mapper.DepartmentMapper;
import com.dbsy.obe.pojo.Department;
import com.dbsy.obe.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service("departmentServiceImp")
@CacheConfig(cacheNames = "department")
public class DepartmentServiceImp implements DepartmentService {
    @Autowired
    DepartmentMapper departmentMapper;

    private static String table = "department";

    @Autowired
    RedisTemplate redisTemplate;

    @Override
    @Transactional
    public int insert(Department record) {
        return departmentMapper.insert(record);
    }

    @Override
    @Transactional
    public int insertSelective(Department record) {
        return departmentMapper.insertSelective(record);
    }

    @Override
    public int listCount(Map map) {
        return departmentMapper.listCount(map);
    }

    @Override
    public List<Department> list(Map map) {
        return departmentMapper.list(map);
    }

    @Override
    @Cacheable(key = "#id", unless = "#result == null")
    public Department get(int id) {
        return departmentMapper.get(id);
    }

    @Override
    @Transactional
    @CacheEvict(key = "#id")
    public int delete(int id) {
        return departmentMapper.delete(id);
    }

    @Override
    @Transactional
    @CachePut(key = "#department.id", unless = "#department == null")
    public int update(Department department) {
        return departmentMapper.update(department);
    }

    @Override
    @Transactional
    public int batchRemove(int[] ids) {
        //清除缓存
        if (ids.length > 0) {
            for (int id : ids) {
                if (redisTemplate.hasKey("department::" + id)) {
                    redisTemplate.delete("department::" + id);
                }
            }
        }
        return departmentMapper.batchRemove(ids);
    }

    @Override
    public List<Department> getAll() {
        return departmentMapper.getAll();
    }
}
