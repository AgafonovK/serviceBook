package com.xvr.serviceBook.controller.webapi;

import com.xvr.serviceBook.entity.Ticket;
import com.xvr.serviceBook.form.TicketForm;
import com.xvr.serviceBook.service.*;
import com.xvr.serviceBook.service.servicedto.TicketServiceDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.Arrays;
import java.util.Optional;

@Controller
@RequestMapping(value = "web/tickets")
public class TicketController {

    private final TicketService ticketService;
    private final DepartmentService departmentService;
    private final EquipmentService equipmentService;
    private final WorkerService workerService;
    private final StatusTicketService statusTicketService;
    private final PriorityTicketService priorityTicketService;
    private static final Logger logger = LoggerFactory.getLogger(TicketController.class);

    @Autowired
    public TicketController(TicketService ticketService, DepartmentService departmentService,
                            EquipmentService equipmentService, WorkerService workerService,
                            StatusTicketService statusTicketService, PriorityTicketService priorityTicketService) {
        this.ticketService = ticketService;
        this.departmentService = departmentService;
        this.equipmentService = equipmentService;
        this.workerService = workerService;
        this.statusTicketService = statusTicketService;
        this.priorityTicketService = priorityTicketService;
    }


    @RequestMapping(method = RequestMethod.GET)
    public String ticketsView(@RequestParam(value = "page") Optional<Integer> page,
                              @RequestParam(value = "size") Optional<Integer> size,
                              Model model) {
        Page<Ticket> ticketsPaginated = ticketService.findAllTicketsPaginated(PageRequest.of(page.orElse(1) - 1, size.orElse(5)));
        model.addAttribute("tickets", ticketsPaginated);
        return "ticket/ticketsPage";
    }

    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN', 'VIEWER')")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String ticketView(@PathVariable Long id,
                             Model model) {
        Optional<Ticket> ticket = ticketService.getTicketById(id);
        if (ticket.isPresent()) {
            model.addAttribute("ticketForm", ticket.get());
            model.addAttribute("startDateTick", ticket.get().getStartDateTicket().toInstant());
            model.addAttribute("listWorkers", workerService.findAllWorker());
            model.addAttribute("listEquipment", equipmentService.findAllEquipment());
            model.addAttribute("listDepartment", departmentService.findAllDepartmentsList());
            model.addAttribute("listStatus", statusTicketService.findAllStatusTicketList());
            model.addAttribute("listPriority", priorityTicketService.findAllPriorityTicketList());
            return "ticket/ticketPage";
        } else {
            model.addAttribute("error", "WTF");
            return "ticket/ticketPage";
        }
    }

    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    @PutMapping(value = "/update/{id}")
    public String ticketUpdate(@PathVariable Long id,
                               @Validated @ModelAttribute(value = "ticketForm") TicketForm ticketForm,
                               BindingResult result,
                               Model model) {
        System.out.println("TICKET " + ticketForm);
        if (result.hasErrors()) {
            model.addAttribute("ticketForm", ticketForm);
            model.addAttribute("listWorkers", workerService.findAllWorker());
            model.addAttribute("listEquipment", equipmentService.findAllEquipment());
            model.addAttribute("listDepartment", departmentService.findAllDepartmentsList());
            model.addAttribute("listStatus", statusTicketService.findAllStatusTicketList());
            model.addAttribute("listPriority", priorityTicketService.findAllPriorityTicketList());
            model.addAttribute("nameError", "Не правильно заполнены поля! Смотри ниже.");
            return "ticket/ticketPage";
        } else {
            try {
                ticketService.save(TicketServiceDto.builder()
                        .ticketId(ticketForm.getId())
                        .ticketDescription(ticketForm.getTicketDescription())
                        .startDateTicket(ticketForm.getStartDateTicket().atStartOfDay(ZoneId.systemDefault()))
                        .endDateTicket(LocalDateTime.of(ticketForm.getEndDateTicket(),LocalTime.now()))
                        .worker(ticketForm.getWorkers())
                        .clientDepartment(ticketForm.getClientDepartment())
                        .equipment(ticketForm.getEquipment())
                        .priorityTicket(ticketForm.getPriorityTicket())
                        .status(ticketForm.getStatusTicket())
                        .build());
            } catch (Exception e) {
                logger.error("Not UPDATE " + e.getStackTrace());
                model.addAttribute("ticketForm", ticketForm);
                model.addAttribute("listWorkers", workerService.findAllWorker());
                model.addAttribute("listEquipment", equipmentService.findAllEquipment());
                model.addAttribute("listDepartment", departmentService.findAllDepartmentsList());
                model.addAttribute("listStatus", statusTicketService.findAllStatusTicketList());
                model.addAttribute("listPriority", priorityTicketService.findAllPriorityTicketList());
                model.addAttribute("errorSave", e.getMessage());
                return "ticket/ticketPage";
            }
        }
        return "redirect:/web/tickets";
    }

    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    @RequestMapping(value = "create-ticket", method = RequestMethod.GET)
    public String addTicket(Model model) {
        TicketForm ticketForm = new TicketForm();
        model.addAttribute("ticketForm", ticketForm);
        model.addAttribute("listWorkers", workerService.findAllWorker());
        model.addAttribute("listEquipment", equipmentService.findAllEquipment());
        model.addAttribute("listDepartment", departmentService.findAllDepartmentsList());
        model.addAttribute("listStatus", statusTicketService.findAllStatusTicketList());
        model.addAttribute("listPriority", priorityTicketService.findAllPriorityTicketList());
        return "ticket/createTicketPage";
    }

    @PostMapping()
    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    public String saveTickets(@Validated @ModelAttribute(value = "ticketForm") TicketForm ticketForm,
                              BindingResult result,
                              Model model) {
        if (result.hasErrors()) {
            model.addAttribute("ticketForm", ticketForm);
            model.addAttribute("listWorkers", workerService.findAllWorker());
            model.addAttribute("listEquipment", equipmentService.findAllEquipment());
            model.addAttribute("listDepartment", departmentService.findAllDepartmentsList());
            model.addAttribute("listStatus", statusTicketService.findAllStatusTicketList());
            model.addAttribute("listPriority", priorityTicketService.findAllPriorityTicketList());
            model.addAttribute("nameError", "Не верно заполнены поля");
            return "ticket/createTicketPage";
        } else {
            try {
                ticketService.save(TicketServiceDto.builder()
                        .ticketDescription(ticketForm.getTicketDescription())
                        .startDateTicket(ticketForm.getStartDateTicket().atStartOfDay(ZoneId.systemDefault()))
                        .endDateTicket(LocalDateTime.of(ticketForm.getEndDateTicket(),LocalTime.now()))
                        .worker(ticketForm.getWorkers())
                        .clientDepartment(ticketForm.getClientDepartment())
                        .equipment(ticketForm.getEquipment())
                        .priorityTicket(ticketForm.getPriorityTicket())
                        .status(ticketForm.getStatusTicket())
                        .build());
            } catch (Exception e) {
                model.addAttribute("errorSave", e.getMessage());
                return "ticket/createTicketPage";
            }
        }
        return "redirect:/web/tickets";
    }


}
