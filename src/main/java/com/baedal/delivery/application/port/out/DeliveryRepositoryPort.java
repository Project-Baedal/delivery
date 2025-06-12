package com.baedal.delivery.application.port.out;

import com.baedal.delivery.domain.model.CreateDelivery;
import com.baedal.delivery.domain.model.Delivery;
import com.baedal.delivery.domain.model.UpdateDeliveryStatus;

public interface DeliveryRepositoryPort {

  void updateStatus(UpdateDeliveryStatus updateDeliveryStatus);

  Delivery createDelivery(CreateDelivery createDelivery);

  Delivery findById(long id);
}
