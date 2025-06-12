package com.baedal.delivery.adapter.out.web;

import com.baedal.delivery.adapter.out.web.manager.StoreSseEmitterManager;
import com.baedal.delivery.application.port.out.DeliveryStatusSenderPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DeliveryStatusSenderAdapter implements DeliveryStatusSenderPort {

  private final StoreSseEmitterManager emitterManager;

  @Override
  public void sendToStore(Long storeId, String payload) {
    emitterManager.sendToClients(storeId, payload);
  }
}
