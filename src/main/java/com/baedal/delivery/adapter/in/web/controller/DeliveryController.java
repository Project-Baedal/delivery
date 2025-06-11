package com.baedal.delivery.adapter.in.web.controller;

import com.baedal.delivery.adapter.in.web.manager.SseEmitterManager;
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

  private final SseEmitterManager sseEmitterManager;

  @GetMapping("/v0/{storeId}/stream")
  public SseEmitter getDeliveryStream(@PathVariable Long storeId) {
    SseEmitter emitter = new SseEmitter(); // Timeout milliseconds
    sseEmitterManager.addEmitter(storeId, emitter);
    return emitter;
  }
}
