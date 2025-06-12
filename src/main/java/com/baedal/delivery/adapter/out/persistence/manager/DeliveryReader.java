package com.baedal.delivery.adapter.out.persistence.manager;

import com.baedal.delivery.adapter.out.persistence.entity.DeliveryEntity;
import com.baedal.delivery.adapter.out.persistence.repository.DeliveryJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DeliveryReader {

  private final DeliveryJpaRepository repository;

  public DeliveryEntity readDelivery(Long id) {
    return repository.findById(id)
        .orElseThrow(() -> new RuntimeException("No delivery found with id: " + id));
  }
}
