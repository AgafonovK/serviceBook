package com.xvr.serviceBook.controller.webapi;

import com.xvr.serviceBook.entity.Ticket;
import com.xvr.serviceBook.form.TicketForm;
import com.xvr.serviceBook.service.*;
import com.xvr.serviceBook.service.servicedto.TicketServiceDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String ticketView(@PathVariable Long id,
                             Model model) {
        System.out.println("IDIDID " + id);
        model.addAttribute("ticket", ticketService.getTicketById(id).get());
        return "ticket/ticketPage";
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
    public String ticketUpdate(@PathVariable Long id,
                               @Validated @ModelAttribute(value = "ticketForm") TicketForm ticketForm,
                               BindingResult result,
                               RedirectAttributes redirectAttributes,
                               Model model) {

        if (result.hasErrors()) {
            redirectAttributes.addAttribute("error", "Error: " + result.getFieldError());
            return "redirect:/web/tickets/create-ticket?error=true";
        } else {
            try {
                ticketService.save(TicketServiceDto.builder()
                        .ticketDescription(ticketForm.getTicketDescription())
                        .startDateTicket(ticketForm.getStartDateTicket())
                        .endDateTicket(ticketForm.getEndDateTicket())
                        .worker(ticketForm.getWorkers())
                        .clientDepartment(ticketForm.getClientDepartment())
                        .equipment(ticketForm.getEquipment())
                        .priorityTicket(ticketForm.getPriorityTicket())
                        .status(ticketForm.getStatus())
                        .build());
            } catch (Exception e) {
                System.out.println(e.getMessage());
                model.addAttribute("errorSave", e.getMessage());
                return "ticket/createTicketPage";
            }
        }
        return "redirect:/web/tickets";
    }

    @RequestMapping(value = "create-ticket", method = RequestMethod.GET)
    public String addTicket(Model model) {
        TicketForm ticketForm = new TicketForm();
        model.addAttribute("listWorkers", workerService.findAllWorker());
        model.addAttribute("listEquipment", equipmentService.findAllEquipment());
        model.addAttribute("listDepartment", departmentService.findAllDepartmentsList());
        model.addAttribute("listStatus", statusTicketService.findAllStatusTicketList());
        model.addAttribute("listPriority", priorityTicketService.findAllPriorityTicketList());
        model.addAttribute("ticketForm", ticketForm);
        return "ticket/createTicketPage";
    }


    @PostMapping()
    public String saveTickets(@Validated @ModelAttribute(value = "ticketForm") TicketForm ticketForm,
                              BindingResult result,
                              RedirectAttributes redirectAttributes,
                              Model model) {
        if (result.hasErrors()) {
            redirectAttributes.addAttribute("error", "Error: " + result.getFieldError());
            return "redirect:/web/tickets/create-ticket?error=true";
        } else {
            try {
                ticketService.save(TicketServiceDto.builder()
                        .ticketDescription(ticketForm.getTicketDescription())
                        .startDateTicket(ticketForm.getStartDateTicket())
                        .endDateTicket(ticketForm.getEndDateTicket())
                        .worker(ticketForm.getWorkers())
                        .clientDepartment(ticketForm.getClientDepartment())
                        .equipment(ticketForm.getEquipment())
                        .priorityTicket(ticketForm.getPriorityTicket())
                        .status(ticketForm.getStatus())
                        .build());
            } catch (Exception e) {
                System.out.println(e.getMessage());
                model.addAttribute("errorSave", e.getMessage());
                return "ticket/createTicketPage";
            }
        }
        return "redirect:/web/tickets";
    }


}
