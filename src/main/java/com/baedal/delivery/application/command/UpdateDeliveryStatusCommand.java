package com.baedal.delivery.application.command;

import lombok.Builder;
import lombok.Getter;

public class UpdateDeliveryStatusCommand {

  @Builder
  @Getter
  public static class Request {

    private Long deliveryId;
    private String status;
  }

}
