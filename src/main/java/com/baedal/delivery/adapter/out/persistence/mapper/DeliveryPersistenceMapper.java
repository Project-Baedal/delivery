package com.baedal.delivery.adapter.out.persistence.mapper;

import com.baedal.delivery.adapter.out.persistence.enums.DeliveryEntityStatus;
import com.baedal.delivery.domain.model.DeliveryStatus;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DeliveryPersistenceMapper {

  DeliveryEntityStatus toEntityStatus(DeliveryStatus status);
}
