package com.baedal.delivery.adapter.out.persistence.manager;

import com.baedal.delivery.adapter.out.persistence.enums.DeliveryEntityStatus;
import com.baedal.delivery.adapter.out.persistence.repository.DeliveryJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DeliveryUpdater {

  private final DeliveryJpaRepository deliveryJpaRepository;

  public void updateStatus(Long id, DeliveryEntityStatus status) {
    deliveryJpaRepository.updateStatus(id, status);
  }
}
