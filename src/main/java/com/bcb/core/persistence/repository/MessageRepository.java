package com.bcb.core.persistence.repository;

import com.bcb.core.persistence.model.MessageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepository extends JpaRepository<MessageEntity, Long> {

    List<MessageEntity> findByCustomer_IdOrderByRequestTimestampDesc(Long id);

}
