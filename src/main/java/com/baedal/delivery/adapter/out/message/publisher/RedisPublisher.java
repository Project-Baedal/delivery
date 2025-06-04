package com.baedal.delivery.adapter.out.message.publisher;

import com.baedal.delivery.util.ObjectMapperUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RedisPublisher {

  private final RedisTemplate<String, Object> template;

  public void publish(ChannelTopic topic, Object message) {
    String json = ObjectMapperUtil.toJson(message);
    template.convertAndSend(topic.getTopic(), json);
  }
}
