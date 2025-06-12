package com.baedal.delivery.adapter.in.message.listener;

import com.baedal.delivery.application.service.DeliveryStatusPushService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DeliveryStatusListener implements MessageListener {

  private final DeliveryStatusPushService service;

  @Override
  public void onMessage(Message message, byte[] pattern) {
    String channel = new String(message.getChannel());
    String payload = new String(message.getBody());

    Long storeId = extractStoreIdFromChannel(channel);
    service.pushDeliveryStatus(storeId, payload);
  }

  private Long extractStoreIdFromChannel(String channel) {
    // delivery:update:%d
    String[] parts = channel.split(":");
    return Long.parseLong(parts[2]);
  }
}
