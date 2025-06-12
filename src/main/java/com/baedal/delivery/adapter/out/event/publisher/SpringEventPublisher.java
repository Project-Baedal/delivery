package com.baedal.delivery.adapter.out.event.publisher;

import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SpringEventPublisher {

  private final ApplicationEventPublisher eventPublisher;

  public void publishEvent(Object event) {
    eventPublisher.publishEvent(event);
  }
}
