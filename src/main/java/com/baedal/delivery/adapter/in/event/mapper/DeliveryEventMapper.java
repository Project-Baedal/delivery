package com.baedal.delivery.adapter.in.event.mapper;

import com.baedal.delivery.domain.model.DeliveryStatus;
import com.baedal.delivery.domain.model.UpdateDeliveryStatus;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DeliveryEventMapper {

  UpdateDeliveryStatus toDomain(Long deliveryId, DeliveryStatus status);
}
