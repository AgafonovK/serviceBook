package com.xvr.serviceBook.service;

import com.xvr.serviceBook.entity.Ticket;
import com.xvr.serviceBook.repository.TicketRepository;
import com.xvr.serviceBook.service.servicedto.TicketServiceDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface TicketService {


    Page<Ticket> findAllTicketsPaginated(Pageable pageable);
    List<Ticket> findAllTicketsList();
    void save(TicketServiceDto ticketServiceDto);
    Optional<Ticket> getTicketById(Long id);
    Page<Ticket> findTicketsByDepartmentId(Pageable pageable, Long departmentId);
}
