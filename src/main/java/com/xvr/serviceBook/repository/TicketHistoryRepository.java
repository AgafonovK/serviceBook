package com.xvr.serviceBook.repository;

import com.xvr.serviceBook.entity.TicketsHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketHistoryRepository extends JpaRepository<TicketsHistory, Long> {
}
