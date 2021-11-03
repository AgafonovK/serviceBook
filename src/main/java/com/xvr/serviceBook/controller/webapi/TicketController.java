package com.xvr.serviceBook.controller.webapi;

import com.xvr.serviceBook.entity.Report;
import com.xvr.serviceBook.entity.Ticket;
import com.xvr.serviceBook.form.TicketForm;
import com.xvr.serviceBook.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping(value = "/web/tickets")
public class TicketController {

    @Autowired
    TicketRepository ticketRepository;
    @Autowired
    DepartmentRepository departmentRepository;
    @Autowired
    EquipmentRepository equipmentRepository;
    @Autowired
    WorkerRepository workerRepository;
    @Autowired
    StatusTicketRepository statusTicketRepository;

    @RequestMapping (method = RequestMethod.GET)
    public String ticketsView(@RequestParam(value = "error", required = false) String error,
                              @RequestParam(value = "result", required = false) String result,
                              Model model){
        List<Ticket> list = ticketRepository.findAll();
        model.addAttribute("tickets",list);
        if (error!=null){
            System.out.println("Sorryan error " + result + " error="+error);
            model.addAttribute("error", result);
            return "ticket/ticketPage";
        }
        return "ticket/ticketPage";
    }

    @RequestMapping (value = "createTicket", method = RequestMethod.GET)
    public String addTicket(Model model){
        TicketForm ticketForm = new TicketForm();
        model.addAttribute("listWorker", workerRepository.findAll());
        model.addAttribute("listEquipment",equipmentRepository.findAll());
        model.addAttribute("listDepartment", departmentRepository.findAll());
        model.addAttribute("listStatus", statusTicketRepository.findAll());
        model.addAttribute("ticketForm", ticketForm);
        return "ticket/createTicketPage";
    }

    //TODO
    @PostMapping()
    public String saveTickets(@Valid @ModelAttribute (value = "ticketForm") TicketForm ticketForm,
                              BindingResult result,
                              Model model){
        System.out.println(ticketForm.toString());
        if (result.hasErrors()){
            model.addAttribute("listWorker", workerRepository.findAll());
            model.addAttribute("listEquipment",equipmentRepository.findAll());
            model.addAttribute("listDepartment", departmentRepository.findAll());
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
                ticketRepository.saveAndFlush(ticket);
            }catch (Exception e){
                System.out.println(e.getMessage());
                model.addAttribute("errorSave", e.getMessage());
                return "ticket/createTicketPage";
            }
        }
        return "redirect:/web/tickets";
    }



}
