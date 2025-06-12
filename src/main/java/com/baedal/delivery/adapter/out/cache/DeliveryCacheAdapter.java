package com.baedal.delivery.adapter.out.cache;

import com.baedal.delivery.adapter.out.cache.template.RedisKeyValueTemplate;
import com.baedal.delivery.adapter.out.cache.template.RedisSetTemplate;
import com.baedal.delivery.application.port.out.DeliveryCachePort;
import com.baedal.delivery.domain.model.DeliveryStatus;
import java.time.Duration;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DeliveryCacheAdapter implements DeliveryCachePort {

  private final RedisKeyValueTemplate keyValueTemplate;

  private final RedisSetTemplate setTemplate;

  @Value("${redis.key-format.delivery-status.key}")
  private String deliveryKeyFormat;

  @Value("${redis.key-format.delivery-status.ttl}")
  private Duration deliveryTtl;

  @Value("${redis.key-format.delivery-store.key}")
  private String deliveryStoreKeyFormat;

  @Value("${redis.key-format.delivery-store.ttl}")
  private Duration deliveryStoreTtl;

  public void saveStatus(Long deliveryId, Long storeId, DeliveryStatus deliveryStatus) {
    String deliveryStatusKey = String.format(deliveryKeyFormat, deliveryId);
    String deliveryStoreKey = String.format(deliveryStoreKeyFormat, storeId);

    // if Delivered, remove cache
    if (deliveryStatus.isDelivered()) {
      keyValueTemplate.delete(deliveryStatusKey);
      setTemplate.remove(deliveryStatusKey, deliveryId);
      return;
    }

    // deliveryId에 해당하는 배달 상태 캐시
    keyValueTemplate.set(deliveryStatusKey, deliveryStatus, deliveryTtl);

    // storeId에 해당하는 Set of deliveryIds 캐시
    setTemplate.add(deliveryStoreKey, deliveryId, deliveryStoreTtl);
  }

  public Optional<DeliveryStatus> findByDeliveryId(Long deliveryId) {
    String deliveryStatusKey = String.format(deliveryKeyFormat, deliveryId);
    return keyValueTemplate.get(deliveryStatusKey, DeliveryStatus.class);
  }

  public Set<Long> findAllByStoreId(Long storeId) {
    String deliveryStoreKey = String.format(deliveryStoreKeyFormat, storeId);
    Set<Object> members = setTemplate.members(deliveryStoreKey);

    return members.stream()
        .map(o -> Long.valueOf(o.toString()))
        .collect(Collectors.toSet());
  }
}
