package com.baedal.delivery.adapter.out.message;

import com.baedal.delivery.adapter.out.message.publisher.RedisPublisher;
import com.baedal.delivery.application.port.out.DeliveryMessagePublisherPort;
import com.baedal.delivery.domain.model.UpdateDeliveryStatus;
import com.baedal.delivery.util.ObjectMapperUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DeliveryMessagePublisherAdapter implements DeliveryMessagePublisherPort {

  private final RedisPublisher redisPublisher;

  @Value("${redis.channel-format.delivery-update}")
  private String channelFormat;

  public void deliveryStatusUpdate(Long storeId, UpdateDeliveryStatus model) {
    String channelName = String.format(channelFormat, storeId);
    String message = ObjectMapperUtil.toJson(model);

    redisPublisher.publish(channelName, message);
  }
}
