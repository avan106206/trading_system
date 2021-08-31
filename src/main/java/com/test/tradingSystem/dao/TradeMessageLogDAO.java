package com.test.tradingSystem.dao;

import com.test.tradingSystem.model.TradeMessageLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TradeMessageLogDAO extends JpaRepository<TradeMessageLog, Long>
{
}
