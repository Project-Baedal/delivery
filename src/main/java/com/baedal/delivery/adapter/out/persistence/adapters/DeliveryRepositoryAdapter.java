package com.baedal.delivery.adapter.out.persistence.adapters;

import com.baedal.delivery.adapter.out.persistence.enums.DeliveryEntityStatus;
import com.baedal.delivery.adapter.out.persistence.manager.DeliveryUpdater;
import com.baedal.delivery.adapter.out.persistence.mapper.DeliveryPersistenceMapper;
import com.baedal.delivery.application.port.out.DeliveryRepositoryPort;
import com.baedal.delivery.domain.model.UpdateDeliveryStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DeliveryRepositoryAdapter implements DeliveryRepositoryPort {

  private final DeliveryPersistenceMapper deliveryMapper;
  private final DeliveryUpdater deliveryUpdater;

  @Override
  public void updateStatus(UpdateDeliveryStatus updateDeliveryStatus) {
    DeliveryEntityStatus status = deliveryMapper.toEntityStatus(updateDeliveryStatus.getStatus());
    deliveryUpdater.updateStatus(updateDeliveryStatus.getId(), status);
  }
}
