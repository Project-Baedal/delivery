package com.baedal.delivery.adapter.out.message.publisher;

import com.baedal.delivery.util.ObjectMapperUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RedisPublisher {

  private final RedisTemplate<String, Object> template;

  public void publish(String channelName, Object message) {
    String json = ObjectMapperUtil.toJson(message);
    template.convertAndSend(channelName, json);
  }
}
