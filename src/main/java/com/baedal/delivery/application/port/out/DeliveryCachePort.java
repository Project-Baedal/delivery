package com.baedal.delivery.application.port.out;

import com.baedal.delivery.domain.model.DeliveryStatus;
import java.util.Optional;
import java.util.Set;

public interface DeliveryCachePort {

  void saveStatus(Long deliveryId, Long storeId, DeliveryStatus deliveryStatus);

  Optional<DeliveryStatus> findByDeliveryId(Long deliveryId);

  Set<Long> findAllByStoreId(Long storeId);
}
