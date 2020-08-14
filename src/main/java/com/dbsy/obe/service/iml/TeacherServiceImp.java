package com.dbsy.obe.service.iml;

import com.dbsy.obe.mapper.TeacherMapper;
import com.dbsy.obe.pojo.Teacher;
import com.dbsy.obe.service.TeacherService;
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

@Service("teacherServiceImp")
@CacheConfig(cacheNames = "teacher")
public class TeacherServiceImp implements TeacherService {
    @Autowired
    TeacherMapper teacherMapper;

    @Autowired
    RedisTemplate redisTemplate;


    @Override
    @Transactional
    public int insert(Teacher record) {
        return teacherMapper.insert(record);
    }

    @Override
    @Transactional
    public int insertSelective(Teacher record) {
        return teacherMapper.insertSelective(record);
    }

    @Override
    public int listCount(Map map) {
        return teacherMapper.listCount(map);
    }

    @Override
    public List<Teacher> list(Map map) {
        return teacherMapper.list(map);
    }

    @Override
    @Cacheable(key = "#id", unless = "#result == null")
    public Teacher get(int id) {
        return teacherMapper.get(id);
    }

    @Override
    @Transactional
    @CacheEvict("#id")
    public int delete(int id) {
        return teacherMapper.delete(id);
    }

    @Override
    @Transactional
    @CachePut(key = "#teacher.id", unless = "#teacher == null")
    public int update(Teacher teacher) {
        return teacherMapper.update(teacher);
    }

    @Override
    @Transactional
    public int batchRemove(int[] ids) {
        if (ids.length > 0) {
            for (int id : ids) {
                if (redisTemplate.hasKey("teacher::" + id)) {
                    redisTemplate.delete("teacher::" + id);
                }
            }
        }
        return teacherMapper.batchRemove(ids);
    }

    @Override
    public List<Teacher> getAll() {
        return teacherMapper.getAll();
    }
}
