package com.baedal.delivery.application.port.out;

import com.baedal.delivery.domain.model.UpdateDeliveryStatus;

public interface DeliveryMessagePublisherPort {

  void deliveryStatusUpdate(Long storeId, UpdateDeliveryStatus model);
}
