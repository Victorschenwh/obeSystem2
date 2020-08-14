package com.dbsy.obe.service.iml;

import com.dbsy.obe.mapper.PointMapper;
import com.dbsy.obe.pojo.Point;
import com.dbsy.obe.service.PointService;
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

@Service("pointServiceImp")
@CacheConfig(cacheNames = "point")
public class PointServiceImp implements PointService {
    @Autowired
    PointMapper pointMapper;

    @Autowired
    RedisTemplate redisTemplate;

    @Override
    public int insert(Point record) {
        return pointMapper.insert(record);
    }

    @Override
    public int insertSelective(Point record) {
        return pointMapper.insertSelective(record);
    }

    @Override
    public int listCount(Map map) {
        return pointMapper.listCount(map);
    }

    @Override
    public List<Point> list(Map map) {
        return pointMapper.list(map);
    }

    @Override
    @Cacheable(key = "#id", unless = "#result == null")
    public Point get(int id) {
        return pointMapper.get(id);
    }

    @Override
    @CacheEvict("#id")
    @Transactional
    public int delete(int id) {
        return pointMapper.delete(id);
    }

    @Override
    @Transactional
    @CachePut(key = "#point.id", unless = "#point == null")
    public int update(Point point) {
        return pointMapper.update(point);
    }

    @Override
    public int batchRemove(int[] ids) { //清除缓存
        if (ids.length > 0) {
            for (int id : ids) {
                if (redisTemplate.hasKey("point::" + id)) {
                    redisTemplate.delete("point::" + id);
                }
            }
        }
        return pointMapper.batchRemove(ids);
    }

    @Override
    public List<Point> getAll() {
        return pointMapper.getAll();
    }

    @Override
    public List<Point> getPointsByRequirementId(int requirementId) {
        return pointMapper.getPointsByRequirementId(requirementId);
    }
}
