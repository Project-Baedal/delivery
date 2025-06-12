package com.baedal.delivery.adapter.out.cache.template;

import java.time.Duration;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RedisKeyValueTemplate {

  private final RedisTemplate<String, Object> redisTemplate;

  public void set(String key, Object value, Duration ttl) {
    redisTemplate.opsForValue().set(key, value, ttl);
  }

  public <T> Optional<T> get(String key, Class<T> clazz) {
    Object value = redisTemplate.opsForValue().get(key);
    if (clazz.isInstance(value)) {
      return Optional.of(clazz.cast(value));
    }
    return Optional.empty();
  }

  public void delete(String key) {
    redisTemplate.delete(key);
  }
}
