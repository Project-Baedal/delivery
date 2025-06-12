package com.baedal.delivery.adapter.in.event;

import com.baedal.delivery.application.event.DeliveryCreatedEvent;
import com.baedal.delivery.application.event.DeliveryStatusUpdatedEvent;
import com.baedal.delivery.application.port.out.DeliveryCachePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
@RequiredArgsConstructor
public class DeliveryEventHandler {

  private final DeliveryCachePort cachePort;

  @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
  public void handle(DeliveryStatusUpdatedEvent event) {
    cachePort.saveStatus(event.getDeliveryId(), event.getStoreId(), event.getStatus());
  }

  @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
  public void handle(DeliveryCreatedEvent event) {
    cachePort.saveStatus(event.getDeliveryId(), event.getStoreId(), event.getStatus());
  }
}
