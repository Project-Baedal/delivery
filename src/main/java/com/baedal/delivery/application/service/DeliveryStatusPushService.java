package com.baedal.delivery.application.service;

import com.baedal.delivery.application.port.out.DeliveryStatusSenderPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeliveryStatusPushService {

  private final DeliveryStatusSenderPort statusSenderPort;

  public void pushDeliveryStatus(Long storeId, String payload) {
    statusSenderPort.sendToStore(storeId, payload);
  }
}
