package com.baedal.delivery.application.port.out;

import com.baedal.delivery.domain.model.UpdateDeliveryStatus;

public interface DeliveryRepositoryPort {

  void updateStatus(UpdateDeliveryStatus updateDeliveryStatus);

}
