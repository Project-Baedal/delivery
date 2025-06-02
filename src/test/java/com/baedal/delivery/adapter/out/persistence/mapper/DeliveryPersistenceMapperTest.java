package com.baedal.delivery.adapter.out.persistence.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import com.baedal.delivery.adapter.out.persistence.entity.DeliveryEntity;
import com.baedal.delivery.adapter.out.persistence.enums.DeliveryEntityStatus;
import com.baedal.delivery.domain.model.CreateDelivery;
import com.baedal.delivery.domain.model.DeliveryStatus;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

class DeliveryPersistenceMapperTest {

  private DeliveryPersistenceMapper mapper = Mappers.getMapper(DeliveryPersistenceMapper.class);

  @Test
  void toEnum() {
    DeliveryEntityStatus entityStatus = mapper.toEntityStatus(DeliveryStatus.PENDING);
    assertThat(entityStatus).isEqualTo(DeliveryEntityStatus.PENDING);
  }

  @Test
  void toDeliveryEntity() {
    CreateDelivery model = CreateDelivery.builder()
        .storeId(2L)
        .orderId(1L)
        .customerId(3L)
        .build();

    DeliveryEntity entity = mapper.toEntity(model);

    assertThat(entity).isNotNull();
    assertThat(entity.getStatus()).isEqualTo(DeliveryEntityStatus.PENDING);
  }
}
