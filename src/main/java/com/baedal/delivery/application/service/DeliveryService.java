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
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DeliveryService implements DeliveryUseCase {

  private final DeliveryRepositoryPort deliveryRepository;

  private final DeliveryCachePort cachePort;

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

  // FIXME: cache 조회 로직을 분리?
  public List<UpdateDeliveryStatus> getStoresDeliveryStatus(Long storeId) {
    Set<Long> deliveryIds = cachePort.findAllByStoreId(storeId);

    if (deliveryIds.isEmpty()) { // 1. cache miss
      return deliveryRepository.findAllValidDeliveriesByStoreId(storeId);
    }

    // 2. cache hit[store set]
    Map<Long, UpdateDeliveryStatus> cached = new HashMap<>();
    Set<Long> cacheMissed = new HashSet<>();

    for (Long deliveryId : deliveryIds) {
      cachePort.findByDeliveryId(deliveryId)
          .map(status -> deliveryMapper.updateDeliveryStatus(deliveryId, status))
          .ifPresentOrElse(
              status -> cached.put(deliveryId, status),
              () -> cacheMissed.add(deliveryId)
          );
    }

    if (!cacheMissed.isEmpty()) { // 3. cache miss[delivery status]
      List<Delivery> cacheMissedDeliveries =
          deliveryRepository.findAllByIds(cacheMissed);

      for (Delivery delivery : cacheMissedDeliveries) {
        UpdateDeliveryStatus status = deliveryMapper.updateDeliveryStatus(delivery.getId(),
            delivery.getStatus());
        cached.put(delivery.getId(), status);

        // 캐시 업데이트
        cachePort.saveStatus(delivery.getId(), storeId, status.getStatus());
      }
    }

    return deliveryIds.stream()
        .map(cached::get)
        .filter(Objects::nonNull)
        .toList();
  }
}
