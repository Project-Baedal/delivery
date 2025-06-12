package com.baedal.delivery.adapter.out.persistence.adapters;

import com.baedal.delivery.adapter.out.persistence.entity.DeliveryEntity;
import com.baedal.delivery.adapter.out.persistence.enums.DeliveryEntityStatus;
import com.baedal.delivery.adapter.out.persistence.manager.DeliveryCreator;
import com.baedal.delivery.adapter.out.persistence.manager.DeliveryReader;
import com.baedal.delivery.adapter.out.persistence.manager.DeliveryUpdater;
import com.baedal.delivery.adapter.out.persistence.mapper.DeliveryPersistenceMapper;
import com.baedal.delivery.application.port.out.DeliveryRepositoryPort;
import com.baedal.delivery.domain.model.CreateDelivery;
import com.baedal.delivery.domain.model.Delivery;
import com.baedal.delivery.domain.model.UpdateDeliveryStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DeliveryRepositoryAdapter implements DeliveryRepositoryPort {

  private final DeliveryPersistenceMapper mapper;

  private final DeliveryUpdater updater;

  private final DeliveryCreator creator;

  private final DeliveryReader reader;

  @Override
  public void updateStatus(UpdateDeliveryStatus updateDeliveryStatus) {
    DeliveryEntityStatus status = mapper.toEntityStatus(updateDeliveryStatus.getStatus());
    updater.updateStatus(updateDeliveryStatus.getId(), status);
  }

  @Override
  public Delivery createDelivery(CreateDelivery createDelivery) {
    DeliveryEntity entity = mapper.toEntity(createDelivery);
    entity = creator.create(entity);
    return mapper.toDomain(entity);
  }

  @Override
  public Delivery findById(long id) {
    DeliveryEntity entity = reader.readDelivery(id);
    return mapper.toDomain(entity);
  }
}
