package com.dbsy.obe.service.iml;

import com.dbsy.obe.mapper.MajorMapper;
import com.dbsy.obe.pojo.Major;
import com.dbsy.obe.service.MajorService;
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

@Service("majorServiceImp")
@CacheConfig(cacheNames = "major")
public class MajorServiceImp implements MajorService {

    @Autowired
    MajorMapper majorMapper;

    @Autowired
    RedisTemplate redisTemplate;

    @Override
    @Transactional
    public int insert(Major record) {
        return majorMapper.insert(record);
    }

    @Override
    @Transactional
    public int insertSelective(Major record) {
        return majorMapper.insertSelective(record);
    }

    @Override
    public int listCount(Map map) {
        return majorMapper.listCount(map);
    }

    @Override
    @Transactional
    public List<Major> list(Map map) {
        return majorMapper.list(map);
    }

    @Override
    @Cacheable(key = "#id", unless = "#result == null")
    public Major get(int id) {
        return majorMapper.get(id);
    }

    @Override
    @Transactional
    @CacheEvict("#id")
    public int delete(int id) {
        return majorMapper.delete(id);
    }

    @Override
    @Transactional
    @CachePut(key = "#major.id", unless = "#major == null")
    public int update(Major major) {
        return majorMapper.update(major);
    }

    @Override
    @Transactional
    public int batchRemove(int[] ids) {
        //清除缓存
        if (ids.length > 0) {
            for (int id : ids) {
                if (redisTemplate.hasKey("major::" + id)) {
                    redisTemplate.delete("major::" + id);
                }
            }
        }
        return majorMapper.batchRemove(ids);
    }

    @Override
    public List<Major> getAll() {
        return majorMapper.getAll();
    }

    @Override
//    @Cacheable(key = "#departmentId", unless = "#result == null")
    public List<Major> getMajorsByDpartmentId(int departmentId) {
        return majorMapper.getMajorsByDpartmentId(departmentId);
    }
}
