package com.spring.batch.repository;

import com.spring.batch.entity.BitcoinData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BitcoinRepository extends JpaRepository<BitcoinData, Long> {

}
