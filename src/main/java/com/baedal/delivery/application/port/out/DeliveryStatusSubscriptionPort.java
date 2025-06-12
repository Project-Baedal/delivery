package com.baedal.delivery.application.port.out;

public interface DeliveryStatusSubscriptionPort {

  void subscribe(Long storeId);

  void unsubscribe(Long storeId);
}
