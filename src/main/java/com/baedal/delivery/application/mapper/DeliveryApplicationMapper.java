package com.baedal.delivery.application.mapper;

import com.baedal.delivery.application.command.UpdateDeliveryStatusCommand;
import com.baedal.delivery.domain.model.CreateDelivery;
import com.baedal.delivery.domain.model.UpdateDeliveryStatus;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface DeliveryApplicationMapper {

  @Mapping(target = "id", source = "deliveryId")
  UpdateDeliveryStatus updateDeliveryStatus(UpdateDeliveryStatusCommand.Request req);

  CreateDelivery createDelivery(Long storeId, Long orderId, Long customerId);
}
