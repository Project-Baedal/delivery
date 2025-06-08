package com.baedal.delivery.application.mapper;

import com.baedal.delivery.application.command.UpdateDeliveryStatusCommand;
import com.baedal.delivery.domain.model.UpdateDeliveryStatus;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DeliveryApplicationMapper {

  UpdateDeliveryStatus updateDeliveryStatus(UpdateDeliveryStatusCommand.Request req);
}
