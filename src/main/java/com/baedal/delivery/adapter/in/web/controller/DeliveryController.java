package com.baedal.delivery.adapter.in.web.controller;

import com.baedal.delivery.adapter.out.web.manager.StoreSseEmitterManager;
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

  @GetMapping("/v0/{storeId}/stream")
  public SseEmitter getDeliveryStream(@PathVariable Long storeId) {
    SseEmitter emitter = new SseEmitter(); // Timeout milliseconds
    storeSseEmitterManager.addEmitter(storeId, emitter);
    return emitter;
  }
}
