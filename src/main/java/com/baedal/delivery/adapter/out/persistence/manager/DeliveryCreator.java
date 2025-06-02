package com.baedal.delivery.adapter.out.persistence.manager;

import com.baedal.delivery.adapter.out.persistence.entity.DeliveryEntity;
import com.baedal.delivery.adapter.out.persistence.repository.DeliveryJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DeliveryCreator {

  private final DeliveryJpaRepository repository;

  public Long create(DeliveryEntity entity) {
    return repository.save(entity)
        .getId();
  }
}
