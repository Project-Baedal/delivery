package com.baedal.delivery.adapter.in.web.manager;

import com.baedal.delivery.adapter.in.message.listener.RedisSubscriber;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@Component
@RequiredArgsConstructor
@Slf4j
public class SseEmitterManager {

  private final RedisSubscriber subscriber;

  private final Map<Long, SseEmitter> emitterMap = new ConcurrentHashMap<>();

  public synchronized void addEmitter(Long storeId, SseEmitter emitter) {
    if (!emitterMap.containsKey(storeId)) {
      emitterMap.put(storeId, emitter);

      subscriber.subscribe(storeId);

      emitter.onCompletion(() -> removeEmitter(storeId, "completion"));
      emitter.onTimeout(() -> removeEmitter(storeId, "timeout"));
    }
  }

  public synchronized void removeEmitter(Long storeId, String reason) {
    SseEmitter emitter = emitterMap.get(storeId);
    if (emitter != null) {
      emitterMap.remove(storeId);
      subscriber.unsubscribe(storeId);
    }
    log.debug("Removing emitter {}", reason);
  }

  public void sendToClients(Long storeId, String message) {
    SseEmitter emitter = emitterMap.get(storeId);
    if (emitter == null) {
      return;
    }

    try {
      emitter.send(SseEmitter.event().name("delivery-update").data(message));
    } catch (IOException e) {
      removeEmitter(storeId, e.toString());
    }
  }
}
