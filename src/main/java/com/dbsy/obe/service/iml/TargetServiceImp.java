package com.dbsy.obe.service.iml;

import com.dbsy.obe.mapper.TargetMapper;
import com.dbsy.obe.pojo.Target;
import com.dbsy.obe.service.TargetService;
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

@Service("targetServiceImp")
@CacheConfig(cacheNames = "target")
public class TargetServiceImp implements TargetService {
    @Autowired
    TargetMapper targetMapper;

    @Autowired
    RedisTemplate redisTemplate;

    @Override
    @Transactional
    public int insert(Target record) {
        return targetMapper.insert(record);
    }

    @Override
    @Transactional
    public int insertSelective(Target record) {
        return targetMapper.insertSelective(record);
    }

    @Override
    public int listCount(Map map) {
        return targetMapper.listCount(map);
    }

    @Override
    public List<Target> list(Map map) {
        return targetMapper.list(map);
    }

    @Override
    @Cacheable(key = "#id", unless = "#result == null")
    public Target get(int id) {
        return targetMapper.get(id);
    }

    @Override
    @Transactional
    @CacheEvict("#id")
    public int delete(int id) {
        return targetMapper.delete(id);
    }

    @Override
    @Transactional
    @CachePut(key = "#target.id", unless = "#target == null")
    public int update(Target target) {
        return targetMapper.update(target);
    }

    @Override
    @Transactional
    public int batchRemove(int[] ids) {
        if (ids.length > 0) {
            for (int id : ids) {
                if (redisTemplate.hasKey("target::" + id)) {
                    redisTemplate.delete("target::" + id);
                }
            }
        }
        return targetMapper.batchRemove(ids);
    }

    @Override
    public List<Target> getAll() {
        return targetMapper.getAll();
    }
}
