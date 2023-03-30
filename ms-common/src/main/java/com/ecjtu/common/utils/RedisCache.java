package com.ecjtu.common.utils;

import ch.qos.logback.core.util.TimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundSetOperations;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @Description: redis 工具类
 * @Author: ZengXueBin
 * @Date: 2023/3/23 14:02
 */
@SuppressWarnings({"unchecked", "rawtypes"})
@Component
public class RedisCache {

    @Autowired
    public RedisTemplate redisTemplate;

    private RedisCache() {
    }

    /**
     * 缓存基本的对象 Integer/String/实体类等
     *
     * @param key 缓存的键值
     * @param value 缓存的值
     * @param <T> 缓存的类型
     */
    public <T> void setCacheObject(final String key, final T value) {
        redisTemplate.opsForValue().set(key, value);
    }

    /**
     * 缓存基本的对象 Integer/String/实体类等
     *
     * @param key 缓存的键值
     * @param value 缓存的值
     * @param timeout 时间
     * @param timeUtil 时间颗粒度
     * @param <T> 缓存的类型
     */
    public <T> void setCacheObject(final String key, final T value, final Integer timeout, final TimeUtil timeUtil) {
        redisTemplate.opsForValue().set(key, value);
    }

    /**
     * 设置有效时间
     *
     * @param key redis键
     * @param timeout 超时时间
     * @param unit 时间单位
     * @return true=设置成功 false=设置失败
     */
    public Boolean expire(final String key, final long timeout, final TimeUnit unit) {
        return Boolean.TRUE.equals(redisTemplate.expire(key, timeout, unit));
    }

    /**
     * 设置有效时间
     *
     * @param key redis键
     * @param timeout 超时时间
     * @return true=设置成功 false=设置失败
     */
    public Boolean expire(final String key, final long timeout) {
        return expire(key, timeout, TimeUnit.SECONDS);
    }

    /**
     * 获取有效时间
     *
     * @param key redis键
     * @return 有效时间
     */
    public Long getExpire(final String key) {
        return redisTemplate.getExpire(key);
    }

    /**
     * 判断key是否存在
     *
     * @param key redis键
     * @return true=存在 false=不存在
     */
    public Boolean hasKey(String key) {
        return redisTemplate.hasKey(key);
    }

    /**
     * 获取缓存的基本对象
     *
     * @param key redis键
     * @return redis键对应的数据
     * @param <T> 数据类型
     */
    public <T> T getCacheObject(final String key) {
        ValueOperations<String, T> operation = redisTemplate.opsForValue();
        return operation.get(key);
    }

    /**
     * 删除单个对象
     *
     * @param key redis键
     * @return true=删除成功 false=删除失败
     */
    public Boolean deleteObject(final String key) {
        return redisTemplate.delete(key);
    }

    /**
     * 删除集合对象
     *
     * @param collection redis集合键
     * @return true=删除成功 false=删除失败
     */
    public Boolean deleteObject(final Collection collection) {
        return Objects.requireNonNull(redisTemplate.delete(collection)) > 0;
    }

    /**
     * 缓存List数据
     *
     * @param key redis键
     * @param dataList 待缓存的List数据
     * @return 缓存的个数
     * @param <T> 对象类型
     */
    public <T> Long setCacheList(final String key, final List<T> dataList) {
        Long count = redisTemplate.opsForList().rightPushAll(key,dataList);
        return count == null ? 0 : count;
    }

    /**
     * 获取缓存的List对象
     *
     * @param key redis键
     * @return 对应的数据
     * @param <T> 数据类型
     */
    public <T> List<T> getCacheList(final String key) {
        return redisTemplate.opsForList().range(key, 0, -1);
    }

    /**
     * 缓存Set
     *
     * @param key redis键
     * @param dataSet 待缓存的数据
     * @return 缓存数据的对象
     * @param <T> 数据类型
     */
    public <T> BoundSetOperations<String, T> setCacheSet(final String key, final Set<T> dataSet) {
        BoundSetOperations<String, T> setOperation = redisTemplate.boundSetOps(key);
        for (T data : dataSet) {
            setOperation.add(data);
        }
        return setOperation;
    }

    /**
     * 获取缓存的Set
     *
     * @param key redis键
     * @return set对象
     * @param <T> 对象类型
     */
    public <T> Set<T> getCacheSet(final String key) {
        return redisTemplate.opsForSet().members(key);
    }

    /**
     * 缓存Map
     *
     * @param key redis键
     * @param dataMap 待缓存的对象
     * @param <T> 对象类型
     */
    public <T> void setCacheMap(final String key, final Map<String, T> dataMap) {
        if (dataMap != null) {
            redisTemplate.opsForHash().putAll(key, dataMap);
        }
    }

    /**
     * 获取缓存的Map
     *
     * @param key redis键
     * @return map对象
     * @param <T> 对象类型
     */
    public <T> Map<String, T> getCacheMap(final String key) {
        return redisTemplate.opsForHash().entries(key);
    }

    /**
     * 往Hash中存入数据
     *
     * @param key redis键
     * @param hKey Hash键
     * @param value 值
     * @param <T> 值类型
     */
    public <T> void setCacheMapValue(final String key, final String hKey, final T value) {
        redisTemplate.opsForHash().put(key, hKey, value);
    }

    /**
     * 获取Hash中的数据
     *
     * @param key redis键
     * @param hKey hash值
     * @return hash中的对象
     * @param <T> 对象类型
     */
    public <T> T getCacheMapValue(final String key, final String hKey) {
        HashOperations<String, String, T> opsForHash = redisTemplate.opsForHash();
        return opsForHash.get(key, hKey);
    }

    /**
     * 获取多个Hash中的数据
     *
     * @param key redis键
     * @param hKeys hash键集合
     * @return hash对象集合
     * @param <T> 对象类型
     */
    public <T> List<T> getMultiCacheMapValue(final String key, final Collection<Object> hKeys) {
        return redisTemplate.opsForHash().multiGet(key, hKeys);
    }

    /**
     * 删除Hash中的某条数据
     *
     * @param key redis键
     * @param hKey hash键
     * @return true=删除成功 false=删除失败
     */
    public Boolean deleteCacheMapValue(final String key, final String hKey) {
        return redisTemplate.opsForHash().delete(key, hKey) > 0;
    }

    /**
     * 获取缓存的基本对象列表
     *
     * @param pattern 字符串前缀
     * @return 对象列表
     */
    public Collection<String> keys(final String pattern) {
        return redisTemplate.keys(pattern);
    }
}
