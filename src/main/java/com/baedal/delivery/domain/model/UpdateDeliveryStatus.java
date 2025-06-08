package com.baedal.delivery.domain.model;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class UpdateDeliveryStatus {

  private Long id;
  private DeliveryStatus status;

}
