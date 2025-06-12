package com.baedal.delivery.application.service;

import com.baedal.delivery.application.command.UpdateDeliveryStatusCommand.Request;
import com.baedal.delivery.application.event.DeliveryCreatedEvent;
import com.baedal.delivery.application.event.DeliveryStatusUpdatedEvent;
import com.baedal.delivery.application.mapper.DeliveryApplicationMapper;
import com.baedal.delivery.application.mapper.DeliveryEventMapper;
import com.baedal.delivery.application.port.in.DeliveryUseCase;
import com.baedal.delivery.application.port.out.DeliveryCachePort;
import com.baedal.delivery.application.port.out.DeliveryEventPublisherPort;
import com.baedal.delivery.application.port.out.DeliveryRepositoryPort;
import com.baedal.delivery.domain.model.CreateDelivery;
import com.baedal.delivery.domain.model.Delivery;
import com.baedal.delivery.domain.model.UpdateDeliveryStatus;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DeliveryService implements DeliveryUseCase {

  private final DeliveryRepositoryPort deliveryRepository;

  private final DeliveryCachePort deliveryCachePort;

  private final DeliveryEventPublisherPort eventPublisher;

  private final DeliveryEventMapper eventMapper;

  private final DeliveryApplicationMapper deliveryMapper;

  @Transactional
  public void updateDeliveryStatus(Request req) {
    Delivery delivery = deliveryRepository.findById(req.getDeliveryId());
    UpdateDeliveryStatus updateDelivery = deliveryMapper.updateDeliveryStatus(req);
    deliveryRepository.updateStatus(updateDelivery);

    DeliveryStatusUpdatedEvent event = eventMapper.toDeliveryStatusUpdatedEvent(delivery);
    eventPublisher.publishEvent(event);
  }

  @Transactional
  public long createDelivery(Long storeId, Long orderId, Long customerId) {
    CreateDelivery createDelivery = deliveryMapper.createDelivery(storeId, orderId, customerId);
    Delivery delivery = deliveryRepository.createDelivery(createDelivery);

    DeliveryCreatedEvent event = eventMapper.toDeliveryCreatedEvent(delivery);
    eventPublisher.publishEvent(event);

    return delivery.getId();
  }
}
