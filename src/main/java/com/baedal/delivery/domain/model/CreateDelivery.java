package com.baedal.delivery.domain.model;

import lombok.Builder;
import lombok.Getter;

@Getter
public class CreateDelivery {

  private final Long orderId;

  private final Long storeId;

  private final Long customerId;

  private final DeliveryStatus status = DeliveryStatus.PENDING;

  @Builder
  public CreateDelivery(Long orderId, Long storeId, Long customerId) {
    this.orderId = orderId;
    this.storeId = storeId;
    this.customerId = customerId;
  }
}
