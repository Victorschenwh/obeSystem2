package com.dbsy.obe.service.iml;

import com.dbsy.obe.mapper.CoursePointMapper;
import com.dbsy.obe.pojo.CoursePoint;
import com.dbsy.obe.service.CoursePointService;
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

@Service("CoursePointServiceImp")
@CacheConfig(cacheNames = "course_point")
public class CoursePointServiceImp implements CoursePointService {

    @Autowired
    CoursePointMapper coursePointMapper;

    @Autowired
    RedisTemplate redisTemplate;

    @Override
    @Transactional
    public int insert(CoursePoint record) {
        return coursePointMapper.insert(record);
    }

    @Override
    @Transactional
    public int insertSelective(CoursePoint record) {
        return coursePointMapper.insertSelective(record);
    }

    @Override
    public int listCount(Map map) {
        return coursePointMapper.listCount(map);
    }

    @Override
    public List<CoursePoint> list(Map map) {
        return coursePointMapper.list(map);
    }

    @Override
    @Cacheable(key = "#id", unless = "#result == null")
    public CoursePoint get(int id) {
        return coursePointMapper.get(id);
    }

    @Override
    @Transactional
    @CacheEvict("#id")
    public int delete(int id) {
        return coursePointMapper.delete(id);
    }

    @Override
    @Transactional
    @CachePut(key = "#coursePoint.id", unless = "#coursePoint == null")
    public int update(CoursePoint coursePoint) {
        return coursePointMapper.update(coursePoint);
    }

    @Override
    @Transactional
    public int batchRemove(int[] ids) {
        //清除缓存
        if (ids.length > 0) {
            for (int id : ids) {
                if (redisTemplate.hasKey("course_point::" + id)) {
                    redisTemplate.delete("course_point::" + id);
                }
            }
        }
        return coursePointMapper.batchRemove(ids);
    }

    @Override
    public List<CoursePoint> getAll() {
        return coursePointMapper.getAll();
    }
}
