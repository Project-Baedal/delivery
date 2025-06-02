package com.baedal.delivery.adapter.in.message.listener;

import com.baedal.delivery.adapter.in.message.event.DeliveryCreateEvent;
import com.baedal.delivery.adapter.in.message.mapper.DeliveryListenerMapper;
import com.baedal.delivery.application.command.UpdateDeliveryStatusCommand;
import com.baedal.delivery.application.port.in.DeliveryUseCase;
import com.baedal.delivery.util.ObjectMapperUtil;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DeliveryListener {

  private final DeliveryListenerMapper deliveryMapper;

  private final DeliveryUseCase deliveryUseCase;

  @KafkaListener(topics = "delivery.updateDeliveryStatus", groupId = "updateDelivery")
  public void updateDeliveryStatus(ConsumerRecord<String, String> record) {
    String deliveryId = record.key();
    String status = record.value();
    UpdateDeliveryStatusCommand.Request command = deliveryMapper.updateDeliveryStatus(
        deliveryId, status
    );
    deliveryUseCase.updateDeliveryStatus(command);
  }

  @KafkaListener(topics = "delivery.create", groupId = "createDelivery")
  public void createDelivery(ConsumerRecord<String, String> record) {
    String key = record.key();
    DeliveryCreateEvent event = ObjectMapperUtil.jsonToDto(
        record.value(), DeliveryCreateEvent.class);
    deliveryUseCase.createDelivery(
        event.getStoreId(),
        event.getOrderId(),
        event.getCustomerId()
    );
  }
}
