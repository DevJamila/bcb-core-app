package com.bcb.core.persistence.repository;

import com.bcb.core.persistence.model.CustomerEntity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository extends JpaRepository<CustomerEntity, Long> {

    @EntityGraph(attributePaths = {"customerPlan"})
    List<CustomerEntity> findAll();

    @EntityGraph(attributePaths = {"customerPlan"})
    Optional<CustomerEntity> findById(Long id);

    @EntityGraph(attributePaths = {"customerPlan"})
    Optional<CustomerEntity> findByIdOrPhone(Long id, String phone);
}
