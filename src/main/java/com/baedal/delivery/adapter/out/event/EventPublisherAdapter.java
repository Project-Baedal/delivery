package com.baedal.delivery.adapter.out.event;

import com.baedal.delivery.adapter.out.event.publisher.SpringEventPublisher;
import com.baedal.delivery.application.port.out.DeliveryEventPublisherPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class EventPublisherAdapter implements DeliveryEventPublisherPort {

  private final SpringEventPublisher springEventPublisher;

  @Override
  public void publishEvent(Object event) {
    log.debug("publish event:[{}]", event.getClass().getName());
    springEventPublisher.publishEvent(event);
  }
}
