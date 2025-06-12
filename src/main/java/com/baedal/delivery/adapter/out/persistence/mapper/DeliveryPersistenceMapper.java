package com.baedal.delivery.adapter.out.persistence.mapper;

import com.baedal.delivery.adapter.out.persistence.entity.DeliveryEntity;
import com.baedal.delivery.adapter.out.persistence.enums.DeliveryEntityStatus;
import com.baedal.delivery.domain.model.CreateDelivery;
import com.baedal.delivery.domain.model.Delivery;
import com.baedal.delivery.domain.model.DeliveryStatus;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DeliveryPersistenceMapper {

  DeliveryEntityStatus toEntityStatus(DeliveryStatus status);

  DeliveryEntity toEntity(CreateDelivery model);

  Delivery toDomain(DeliveryEntity entity);
}
