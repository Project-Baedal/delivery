package com.baedal.delivery.adapter.out.cache.template;

import java.time.Duration;
import java.util.Collection;
import java.util.Collections;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RedisSetTemplate {

  private final RedisTemplate<String, Object> redisTemplate;

  public void add(String key, Object value, Duration ttl) {
    redisTemplate.opsForSet().add(key, value);
    redisTemplate.expire(key, ttl);
  }

  public void addAll(String key, Collection<?> values, Duration ttl) {
    if (values != null && !values.isEmpty()) {
      redisTemplate.opsForSet().add(key, values.toArray());
    }
    redisTemplate.expire(key, ttl);
  }

  public void remove(String key, Object value) {
    redisTemplate.opsForSet().remove(key, value);
  }

  public Set<Object> members(String key) {
    Set<Object> result = redisTemplate.opsForSet().members(key);
    return result != null ? result : Collections.emptySet();
  }

  public boolean isMember(String key, Object value) {
    Boolean result = redisTemplate.opsForSet().isMember(key, value);
    return Boolean.TRUE.equals(result);
  }

  public void delete(String key) {
    redisTemplate.delete(key);
  }
}
