package com.baedal.delivery.domain.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Delivery {

  private Long id;

  private Long orderId;

  private Long storeId;

  private Long customerId;

  private Long riderId;

  private DeliveryStatus status;
}
