package com.baedal.delivery.domain.model;

public enum DeliveryStatus {
  PENDING, DELIVERING, DELIVERED;

  public boolean isDelivered() {
    return this == DELIVERED;
  }
}
