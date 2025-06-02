package com.baedal.delivery.application.service;

import com.baedal.delivery.application.command.UpdateDeliveryStatusCommand.Request;
import com.baedal.delivery.application.mapper.DeliveryApplicationMapper;
import com.baedal.delivery.application.port.in.DeliveryUseCase;
import com.baedal.delivery.application.port.out.DeliveryRepositoryPort;
import com.baedal.delivery.domain.model.CreateDelivery;
import com.baedal.delivery.domain.model.UpdateDeliveryStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DeliveryService implements DeliveryUseCase {

  private final DeliveryApplicationMapper deliveryMapper;

  private final DeliveryRepositoryPort deliveryRepository;

  @Override
  public void updateDeliveryStatus(Request req) {
    UpdateDeliveryStatus delivery = deliveryMapper.updateDeliveryStatus(req);
    deliveryRepository.updateStatus(delivery);
  }

  @Transactional
  public long createDelivery(Long storeId, Long orderId, Long customerId) {
    CreateDelivery delivery = deliveryMapper.createDelivery(storeId, orderId, customerId);
    return deliveryRepository.createDelivery(delivery);
  }
}
