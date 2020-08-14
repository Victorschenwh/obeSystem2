package com.dbsy.obe.service.iml;


import com.dbsy.obe.mapper.CourseMapper;
import com.dbsy.obe.pojo.Course;
import com.dbsy.obe.service.CourseService;
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

@Service("courseServiceImp")
@CacheConfig(cacheNames = "course")
public class CourseServiceImp implements CourseService {
    @Autowired
    private CourseMapper courseMapper;

    @Autowired
    RedisTemplate redisTemplate;

    @Override
    @Transactional
    public int insert(Course record) {
        return this.courseMapper.insert(record);
    }

    @Override
    @Transactional
    public int insertSelective(Course record) {
        return this.insertSelective(record);
    }

    @Override
    public int listCount(Map map) {
        return this.courseMapper.listCount(map);
    }

    @Override
    public List<Course> list(Map map) {
        return this.courseMapper.list(map);
    }

    @Override
    @Cacheable(key = "#id", unless = "#result == null")
    public Course get(int id) {
        return this.courseMapper.get(id);
    }

    @Override
    @Transactional
    @CacheEvict(key = "#id")
    public int delete(int id) {
        return this.courseMapper.delete(id);
    }

    @Override
    @Transactional
    @CachePut(key = "#course.id", unless = "#course == null")
    public int update(Course course) {
        return this.courseMapper.update(course);
    }

    @Override
    public int batchRemove(int[] ids) {
        //清除缓存
        if (ids.length > 0) {
            for (int id : ids) {
                if (redisTemplate.hasKey("course::" + id)) {
                    redisTemplate.delete("course::" + id);
                }
            }
        }
        return this.courseMapper.batchRemove(ids);
    }

    @Override
    public List<Course> getAll() {
        return this.courseMapper.getAll();
    }
}
