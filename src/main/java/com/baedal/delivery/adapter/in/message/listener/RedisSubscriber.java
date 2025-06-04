package com.baedal.delivery.adapter.in.message.listener;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RedisSubscriber {

  private final RedisMessageListenerContainer listenerContainer;

  private final DeliveryStatusListener listener;

  private final Map<Long, ChannelTopic> subscribedTopics = new ConcurrentHashMap<>();

  public synchronized void subscribe(Long storeId) {
    if (!subscribedTopics.containsKey(storeId)) {
      // TODO: key 값에 따른 Subscriber 클래스 분리 예정
      ChannelTopic topic = new ChannelTopic("delivery:update:" + storeId);
      listenerContainer.addMessageListener(listener, topic);
      subscribedTopics.put(storeId, topic);
    }
  }

  public synchronized void unsubscribe(Long storeId) {
    ChannelTopic topic = subscribedTopics.remove(storeId);
    if (topic != null) {
      listenerContainer.removeMessageListener(listener, topic);
    }
  }
}
