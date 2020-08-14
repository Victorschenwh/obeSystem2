package com.dbsy.obe.service.iml;

import com.dbsy.obe.mapper.PlanMapper;
import com.dbsy.obe.pojo.Major;
import com.dbsy.obe.pojo.Plan;
import com.dbsy.obe.service.PlanService;
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

@Service("planServiceImp")
@CacheConfig(cacheNames = "plan")
public class PlanServiceImp implements PlanService {
    @Autowired
    PlanMapper planMapper;

    @Autowired
    RedisTemplate redisTemplate;

    @Override
    @Transactional
    public int insert(Plan record) {
        return planMapper.insert(record);
    }

    @Override
    @Transactional
    public int insertSelective(Plan record) {
        return planMapper.insertSelective(record);
    }

    @Override
    public int listCount(Map map) {
        return planMapper.listCount(map);
    }

    @Override
    public List<Plan> list(Map map) {
        return planMapper.list(map);
    }

    @Override
    @Cacheable(key = "#id", unless = "#result == null")
    public Plan get(int id) {
        return planMapper.get(id);
    }

    @Override
    @CacheEvict("#id")
    @Transactional
    public int delete(int id) {
        return planMapper.delete(id);
    }

    @Override
    @Transactional
    @CachePut(key = "#plan.id", unless = "#plan == null")
    public int update(Plan plan) {
        return planMapper.update(plan);
    }

    @Override
    public int batchRemove(int[] ids) {
        //清除缓存
        if (ids.length > 0) {
            for (int id : ids) {
                if (redisTemplate.hasKey("plan::" + id)) {
                    redisTemplate.delete("plan::" + id);
                }
            }
        }
        return planMapper.batchRemove(ids);
    }

    @Override
    public List<Plan> getAll() {
        return planMapper.getAll();
    }

    @Override
    public List<Plan> getPlansByMajorId(int majorId) {
        return planMapper.getPlansByMajorId(majorId);
    }
}
