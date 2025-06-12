package com.baedal.delivery.application.event;

import com.baedal.delivery.domain.model.DeliveryStatus;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class DeliveryCreatedEvent {

  private Long deliveryId;

  private Long orderId;

  private Long storeId;

  private Long customerId;

  private DeliveryStatus status;
}
