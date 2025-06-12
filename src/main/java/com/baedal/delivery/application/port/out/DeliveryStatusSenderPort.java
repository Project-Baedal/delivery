package com.baedal.delivery.application.port.out;

public interface DeliveryStatusSenderPort {

  void sendToStore(Long storeId, String payload);
}
