package com.baedal.delivery.adapter.out.persistence.entity;

import com.baedal.delivery.adapter.out.persistence.enums.DeliveryEntityStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "delivery")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DeliveryEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private Long orderId;

  @Column(nullable = false)
  private Long storedId;

  @Column(nullable = false)
  private Long customerId;

  @Column(nullable = true)
  private Long riderId;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private DeliveryEntityStatus status;

  @Builder
  public DeliveryEntity(Long orderId, Long storedId, Long customerId, Long riderId,
      DeliveryEntityStatus status) {
    this.orderId = orderId;
    this.storedId = storedId;
    this.customerId = customerId;
    this.riderId = riderId;
    this.status = status;
  }
}
