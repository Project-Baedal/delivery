package com.baedal.delivery.application.port.in;

import com.baedal.delivery.application.command.UpdateDeliveryStatusCommand;

public interface DeliveryUseCase {

  void updateDeliveryStatus(UpdateDeliveryStatusCommand.Request req);

  long createDelivery(Long storeId, Long orderId, Long customerId);
}
