package com.hsbc.downstream.repo;
import org.springframework.data.jpa.repository.JpaRepository;

import com.hsbc.downstream.model.TransactionRecord;








public interface TransactionRecordRepository extends JpaRepository<TransactionRecord, Integer> {
}