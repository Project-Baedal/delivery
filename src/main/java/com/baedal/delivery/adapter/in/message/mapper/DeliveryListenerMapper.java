package com.baedal.delivery.adapter.in.message.mapper;

import com.baedal.delivery.application.command.UpdateDeliveryStatusCommand;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DeliveryListenerMapper {

  UpdateDeliveryStatusCommand.Request updateDeliveryStatus(String deliveryId, String status);
}
