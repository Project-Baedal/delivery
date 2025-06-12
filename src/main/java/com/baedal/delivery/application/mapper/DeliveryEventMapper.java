package com.baedal.delivery.application.mapper;

import com.baedal.delivery.application.event.DeliveryCreatedEvent;
import com.baedal.delivery.application.event.DeliveryStatusUpdatedEvent;
import com.baedal.delivery.domain.model.Delivery;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface DeliveryEventMapper {

  @Mapping(target = "deliveryId", source = "id")
  DeliveryStatusUpdatedEvent toDeliveryStatusUpdatedEvent(Delivery model);

  @Mapping(target = "deliveryId", source = "id")
  DeliveryCreatedEvent toDeliveryCreatedEvent(Delivery model);
}
