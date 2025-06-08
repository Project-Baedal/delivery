package com.baedal.delivery.adapter.in.message.listener;

import com.baedal.delivery.adapter.in.message.mapper.DeliveryListenerMapper;
import com.baedal.delivery.application.command.UpdateDeliveryStatusCommand;
import com.baedal.delivery.application.port.in.DeliveryUseCase;
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
}
