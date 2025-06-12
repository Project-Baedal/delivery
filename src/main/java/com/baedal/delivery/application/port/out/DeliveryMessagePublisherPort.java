package com.baedal.delivery.application.port.out;

import com.baedal.delivery.domain.model.DeliveryStatus;

public interface DeliveryMessagePublisherPort {

  void deliveryStatusUpdate(Long storeId, DeliveryStatus status);
}
