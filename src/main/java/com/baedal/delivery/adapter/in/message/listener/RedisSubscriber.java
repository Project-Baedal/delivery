package com.baedal.delivery.adapter.in.message.listener;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RedisSubscriber {

  private final RedisMessageListenerContainer listenerContainer;

  private final DeliveryStatusListener listener;

  private final Map<Long, ChannelTopic> subscribedTopics = new ConcurrentHashMap<>();

  @Value("${redis.channel-format.delivery-update}")
  private String channelFormat;

  public synchronized void subscribe(Long storeId) {
    if (!subscribedTopics.containsKey(storeId)) {
      // TODO: key 값에 따른 Subscriber 클래스 분리 예정
      String channelName = String.format(channelFormat, storeId);
      ChannelTopic topic = new ChannelTopic(channelName);
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
