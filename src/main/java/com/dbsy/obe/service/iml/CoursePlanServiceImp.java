package com.dbsy.obe.service.iml;

import com.dbsy.obe.mapper.CoursePlanMapper;
import com.dbsy.obe.pojo.CoursePlan;
import com.dbsy.obe.service.CoursePlanService;
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

@Service("coursePlanServiceImp")
@CacheConfig(cacheNames = "course_plan")
public class CoursePlanServiceImp implements CoursePlanService {

    @Autowired
    private CoursePlanMapper coursePlanMapper;

    @Autowired
    RedisTemplate redisTemplate;

    @Override
    @Transactional
    public int insert(CoursePlan record) {
        return this.coursePlanMapper.insert(record);
    }

    @Override
    @Transactional
    public int insertSelective(CoursePlan record) {
        return this.coursePlanMapper.insertSelective(record);
    }

    @Override
    public int listCount(Map map) {
        return this.coursePlanMapper.listCount(map);
    }

    @Override
    public List<CoursePlan> list(Map map) {
        return this.coursePlanMapper.list(map);
    }

    @Override
    @Cacheable(key = "#id", unless = "#result == null")
    public CoursePlan get(int id) {
        return this.coursePlanMapper.get(id);
    }

    @Override
    @CacheEvict(key = "#id")
    @Transactional
    public int delete(int id) {
        return this.coursePlanMapper.delete(id);
    }

    @Override
    @Transactional
    @CachePut(key = "#coursePlan.id", unless = "#coursePlan == null")
    public int update(CoursePlan coursePlan) {
        return this.coursePlanMapper.update(coursePlan);
    }

    @Override
    public int batchRemove(int[] ids) {
        //清除缓存
        if (ids.length > 0) {
            for (int id : ids) {
                if (redisTemplate.hasKey("course_plan::" + id)) {
                    redisTemplate.delete("course_plan::" + id);
                }
            }
        }
        return this.coursePlanMapper.batchRemove(ids);
    }

    @Override
    public List<CoursePlan> getAll() {
        return this.coursePlanMapper.getAll();
    }
}
