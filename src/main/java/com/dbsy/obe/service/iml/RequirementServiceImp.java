package com.dbsy.obe.service.iml;

import com.dbsy.obe.mapper.RequirementMapper;
import com.dbsy.obe.pojo.Requirement;
import com.dbsy.obe.service.RequirementService;
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

@Service("requirementServiceImp")
@CacheConfig(cacheNames = "requirement")
public class RequirementServiceImp implements RequirementService {
    @Autowired
    RequirementMapper requirementMapper;

    @Autowired
    RedisTemplate redisTemplate;

    @Override
    @Transactional
    public int insert(Requirement record) {
        return requirementMapper.insert(record);
    }

    @Override
    @Transactional
    public int insertSelective(Requirement record) {
        return requirementMapper.insertSelective(record);
    }

    @Override
    public int listCount(Map map) {
        return requirementMapper.listCount(map);
    }

    @Override
    public List<Requirement> list(Map map) {
        return requirementMapper.list(map);
    }

    @Override
    @Cacheable(key = "#id", unless = "#result == null")
    public Requirement get(int id) {
        return requirementMapper.get(id);
    }

    @Override
    @Transactional
    @CacheEvict("#id")
    public int delete(int id) {
        return requirementMapper.delete(id);
    }

    @Override
    @Transactional
    @CachePut(key = "#requirement.id", unless = "#requirement == null")
    public int update(Requirement requirement) {
        return requirementMapper.update(requirement);
    }

    @Override
    @Transactional
    public int batchRemove(int[] ids) {
        if (ids.length > 0) {
            for (int id : ids) {
                if (redisTemplate.hasKey("requirement::" + id)) {
                    redisTemplate.delete("requirement::" + id);
                }
            }
        }
        return requirementMapper.batchRemove(ids);
    }

    @Override
    public List<Requirement> getAll() {
        return requirementMapper.getAll();
    }
}

