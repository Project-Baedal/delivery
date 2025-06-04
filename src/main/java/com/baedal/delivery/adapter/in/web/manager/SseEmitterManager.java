package com.baedal.delivery.adapter.in.web.manager;

import com.baedal.delivery.adapter.in.message.listener.RedisSubscriber;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@Component
@RequiredArgsConstructor
public class SseEmitterManager {

  private final RedisSubscriber subscriber;

  // TODO: owner가 하나의 store에 여러 클라이언트로 SSE 연결할 경우 storeId->List로
  private final Map<Long, List<SseEmitter>> emitterMap = new ConcurrentHashMap<>();

  public synchronized void addEmitter(Long storeId, SseEmitter emitter) {
    emitterMap.computeIfAbsent(storeId, id -> new CopyOnWriteArrayList<>()).add(emitter);

    if (emitterMap.get(storeId).size() == 1) {
      subscriber.subscribe(storeId);
    }

    emitter.onCompletion(() -> removeEmitter(storeId, emitter));
    emitter.onTimeout(() -> removeEmitter(storeId, emitter));
  }

  public synchronized void removeEmitter(Long storeId, SseEmitter emitter) {
    List<SseEmitter> emitters = emitterMap.get(storeId);
    if (emitters != null) {
      emitters.remove(emitter);
      if (emitters.isEmpty()) {
        emitterMap.remove(storeId);
        subscriber.unsubscribe(storeId);
      }
    }
  }

  public void sendToClients(Long storeId, String message) {
    List<SseEmitter> emitters = emitterMap.get(storeId);
    if (emitters == null) return;

    for (SseEmitter emitter : emitters) {
      try {
        emitter.send(SseEmitter.event().name("delivery-update").data(message));
      } catch (IOException e) {
        removeEmitter(storeId, emitter);
      }
    }
  }

}
