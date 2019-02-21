package com.xvr.serviceBook.controller;

import com.xvr.serviceBook.entity.Report;
import com.xvr.serviceBook.entity.Ticket;
import com.xvr.serviceBook.form.TicketForm;
import com.xvr.serviceBook.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping(value = "/ticket")
public class TicketController {

    @Autowired
    TicketRepository ticketRepository;
    @Autowired
    DepartmentRepository departmentRepository;
    @Autowired
    EquipmentRepository equipmentRepository;
    @Autowired
    WorkerRepository workerRepository;
    @RequestMapping (value = "addTicket", method = RequestMethod.GET)
    public String addTicket(Model model){
        TicketForm ticketForm = new TicketForm();
        model.addAttribute("listWorker", workerRepository.findAll());
        model.addAttribute("listEquipment",equipmentRepository.findAll());
        model.addAttribute("listDepartment", departmentRepository.findAll());
        model.addAttribute("ticketForm", ticketForm);
        return "ticket/addTicketPage";
    }

    @RequestMapping (value = "ticket", method = RequestMethod.GET)
    public String ticketView(Model model){
        List<Ticket> list = ticketRepository.findAll();
        model.addAttribute("ticket",list);
       return "ticket/ticketPage";
    }
}
