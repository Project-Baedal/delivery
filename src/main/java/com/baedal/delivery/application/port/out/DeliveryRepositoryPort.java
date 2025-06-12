package com.baedal.delivery.application.port.out;

import com.baedal.delivery.domain.model.CreateDelivery;
import com.baedal.delivery.domain.model.Delivery;
import com.baedal.delivery.domain.model.UpdateDeliveryStatus;
import java.util.Collection;
import java.util.List;

public interface DeliveryRepositoryPort {

  void updateStatus(UpdateDeliveryStatus updateDeliveryStatus);

  Delivery createDelivery(CreateDelivery createDelivery);

  Delivery findById(long id);

  List<Delivery> findAllByIds(Collection<Long> ids);

  List<UpdateDeliveryStatus> findAllValidDeliveriesByStoreId(Long storeId);
}
