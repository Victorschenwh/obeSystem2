package com.dbsy.obe.service.iml;

import com.dbsy.obe.mapper.ClazzMapper;
import com.dbsy.obe.pojo.Clazz;
import com.dbsy.obe.service.ClazzService;
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

@Service("clazzServiceImp")
@CacheConfig(cacheNames = "clazz")
public class ClazzServiceImp implements ClazzService {

    @Autowired
    private ClazzMapper clazzMapper;

    @Autowired
    RedisTemplate redisTemplate;

    @Override
    @Transactional
    public int insert(Clazz record) {
        return this.clazzMapper.insert(record);
    }

    @Override
    @Transactional
    public int insertSelective(Clazz record) {
        return this.clazzMapper.insertSelective(record);
    }

    @Override
    public int listCount(Map map) {
        return this.clazzMapper.listCount(map);
    }

    @Override
    public List<Clazz> list(Map map) {
        return this.clazzMapper.list(map);
    }

    @Override
    @Cacheable(key = "#id", unless = "#result ==null ")
    public Clazz get(int id) {
        return this.clazzMapper.get(id);
    }

    @Override
    @CacheEvict(key = "#id")
    @Transactional
    public int delete(int id) {
        return this.clazzMapper.delete(id);
    }

    @Override
    @CachePut(key = "#clazz.id", unless = "#clazz == null")
    @Transactional
    public int update(Clazz clazz) {
        return this.clazzMapper.update(clazz);
    }

    @Override
    @Transactional
    public int batchRemove(int[] ids) {
        //清除缓存
        if (ids.length > 0) {
            for (int id : ids) {
                if (redisTemplate.hasKey("clazz::" + id)) {
                    redisTemplate.delete("clazz::" + id);
                }
            }
        }
        return this.clazzMapper.batchRemove(ids);
    }

    @Override
    public List<Clazz> getAll() {
        return this.clazzMapper.getAll();
    }
}
