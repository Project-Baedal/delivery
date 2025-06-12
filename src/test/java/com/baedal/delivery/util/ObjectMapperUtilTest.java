package com.baedal.delivery.util;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.baedal.delivery.domain.model.DeliveryStatus;
import com.baedal.delivery.domain.model.UpdateDeliveryStatus;
import org.junit.jupiter.api.Test;

class ObjectMapperUtilTest {

  @Test
  void string_toJson() {
    String hello = ObjectMapperUtil.toJson("hello");
    System.out.println("hello = " + hello);
  }

  @Test
  void string_jsonToDto() {
    assertThatThrownBy(() -> {
          String hello = ObjectMapperUtil.jsonToDto("hello", String.class);
          System.out.println("hello = " + hello);
        }
    ).isInstanceOf(RuntimeException.class);
  }

  @Test
  void enum_toJson() {
    String json = ObjectMapperUtil.toJson(DeliveryStatus.DELIVERED);
    System.out.println("json = " + json);
  }

  @Test
  void model_toJson() {
    UpdateDeliveryStatus model = UpdateDeliveryStatus.builder()
        .id(1L)
        .status(DeliveryStatus.PENDING)
        .build();
    String json = ObjectMapperUtil.toJson(model);
    System.out.println("json = " + json);
  }
}
