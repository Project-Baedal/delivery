package com.baedal.delivery.adapter.in.message.event;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class DeliveryCreateEvent {

  private Long orderId;

  private Long storeId;

  private Long customerId;
}
