package com.xvr.serviceBook.repository;

import com.xvr.serviceBook.entity.PriorityTicket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PriorityTicketRepository extends JpaRepository<PriorityTicket,Long> {

}
