package com.baedal.delivery.adapter.in.web.controller;

import com.baedal.delivery.adapter.out.web.manager.StoreSseEmitterManager;
import com.baedal.delivery.application.service.DeliveryService;
import com.baedal.delivery.domain.model.UpdateDeliveryStatus;
import com.baedal.delivery.util.ObjectMapperUtil;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/delivery")
public class DeliveryController {

  private final StoreSseEmitterManager storeSseEmitterManager;

  private final DeliveryService service;

  @GetMapping("/v0/{storeId}/stream")
  public SseEmitter getDeliveryStream(@PathVariable Long storeId) {
    SseEmitter emitter = new SseEmitter(); // FIXME: Timeout milliseconds
    storeSseEmitterManager.addEmitter(storeId, emitter);
    return emitter;
  }

  @GetMapping("/v0/{storeId}/notify-ready")
  public void clientReady(@PathVariable Long storeId) {
    List<UpdateDeliveryStatus> statuses = service.getStoresDeliveryStatus(storeId);
    for (UpdateDeliveryStatus status : statuses) {
      storeSseEmitterManager.sendToClients(storeId, ObjectMapperUtil.toJson(status));
    }
  }

}
