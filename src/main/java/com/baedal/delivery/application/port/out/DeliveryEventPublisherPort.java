package com.baedal.delivery.application.port.out;

public interface DeliveryEventPublisherPort {

  void publishEvent(Object event);
}
