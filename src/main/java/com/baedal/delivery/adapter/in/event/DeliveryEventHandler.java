package com.baedal.delivery.adapter.in.event;

import com.baedal.delivery.application.event.DeliveryCreatedEvent;
import com.baedal.delivery.application.event.DeliveryStatusUpdatedEvent;
import com.baedal.delivery.application.port.out.DeliveryCachePort;
import com.baedal.delivery.application.port.out.DeliveryMessagePublisherPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
@RequiredArgsConstructor
public class DeliveryEventHandler {

  private final DeliveryCachePort cachePort;

  private final DeliveryMessagePublisherPort messagePublisher;

  @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
  public void handle(DeliveryStatusUpdatedEvent event) {
    cachePort.saveStatus(event.getDeliveryId(), event.getStoreId(), event.getStatus());
    messagePublisher.deliveryStatusUpdate(event.getStoreId(), event.getStatus());
  }

  @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
  public void handle(DeliveryCreatedEvent event) {
    cachePort.saveStatus(event.getDeliveryId(), event.getStoreId(), event.getStatus());
    messagePublisher.deliveryStatusUpdate(event.getStoreId(), event.getStatus());
  }
}
