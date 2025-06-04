package com.baedal.delivery.util;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

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
}
