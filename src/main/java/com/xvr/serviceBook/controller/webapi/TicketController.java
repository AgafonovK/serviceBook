package com.xvr.serviceBook.controller.webapi;

import com.xvr.serviceBook.entity.Report;
import com.xvr.serviceBook.entity.Ticket;
import com.xvr.serviceBook.form.TicketForm;
import com.xvr.serviceBook.repository.*;
import com.xvr.serviceBook.service.DepartmentService;
import com.xvr.serviceBook.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(value = "web/tickets")
public class TicketController {

    private final TicketService ticketService;

    @Autowired
    DepartmentService departmentService;
    @Autowired
    EquipmentRepository equipmentRepository;
    @Autowired
    WorkerRepository workerRepository;
    @Autowired
    StatusTicketRepository statusTicketRepository;

    @Autowired
    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }


    @RequestMapping (method = RequestMethod.GET)
    public String ticketsView(@RequestParam(value = "page") Optional<Integer> page,
                              @RequestParam(value = "size") Optional<Integer> size,
                              Model model){
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(5);
        //TODO don't work page
        Page<Ticket> ticketsPaginated = ticketService.findAllTicketsPaginated(PageRequest.of(1,5));
        List<Ticket> list = ticketService.findAllTicketsList();
        System.out.println("TICKETS " + list.size());
        System.out.println("TICKETS PAGE " + ticketsPaginated.getContent().size());
        model.addAttribute("tickets",ticketsPaginated);
        return "ticket/ticketPage";
    }

    @RequestMapping (value = "create-ticket", method = RequestMethod.GET)
    public String addTicket(Model model){
        TicketForm ticketForm = new TicketForm();
        model.addAttribute("listWorker", workerRepository.findAll());
        model.addAttribute("listEquipment",equipmentRepository.findAll());
        model.addAttribute("listDepartment", departmentService.findAllDepartmentsList());
        model.addAttribute("listStatus", statusTicketRepository.findAll());
        model.addAttribute("ticketForm", ticketForm);
        return "ticket/createTicketPage";
    }


    @PostMapping()
    public String saveTickets(@Valid @ModelAttribute (value = "ticketForm") TicketForm ticketForm,
                              BindingResult result,
                              Model model){
        if (result.hasErrors()){
            model.addAttribute("listWorker", workerRepository.findAll());
            model.addAttribute("listEquipment",equipmentRepository.findAll());
            model.addAttribute("listDepartment", departmentService.findAllDepartmentsList());
            model.addAttribute("listStatus", statusTicketRepository.findAll());
            model.addAttribute("ticketForm", ticketForm);
            return "ticket/createTicketPage";
        }else {
            Ticket ticket = new Ticket();
            ticket.setClient(ticketForm.getClient());
            ticket.setEndDateTicket(ticketForm.getEndDateTicket());
            ticket.setStartDateTicket(ticketForm.getStartDateTicket());
            ticket.setTicketDescription(ticketForm.getTicketDescription());
            ticket.setEquipment(ticketForm.getEquipment());
            ticket.setClientDepartment(ticketForm.getClientDepartment());
            ticket.setPriority(ticketForm.getPriority());
            ticket.setStatus(ticketForm.getStatus());
            try {
                //TODO check ticket id
                System.out.println("TICKET " + ticket.toString());
                //ticketRepository.saveAndFlush(ticket);
            }catch (Exception e){
                System.out.println(e.getMessage());
                model.addAttribute("errorSave", e.getMessage());
                return "ticket/createTicketPage";
            }
        }
        return "redirect:/web/tickets";
    }



}
