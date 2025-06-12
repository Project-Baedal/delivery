package com.baedal.delivery.adapter.out.persistence.repository;

import com.baedal.delivery.adapter.out.persistence.entity.DeliveryEntity;
import com.baedal.delivery.adapter.out.persistence.enums.DeliveryEntityStatus;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface DeliveryJpaRepository extends JpaRepository<DeliveryEntity, Long> {

  @Modifying
  @Query("UPDATE DeliveryEntity d SET d.status = :status WHERE d.id = :id")
  void updateStatus(@Param("id") Long id, @Param("status") DeliveryEntityStatus status);

  List<DeliveryEntity> findByStoredIdAndStatusNot(Long storedId, DeliveryEntityStatus status);
}
