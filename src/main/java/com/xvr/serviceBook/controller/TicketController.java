package com.xvr.serviceBook.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(name = "ticket")
public class TicketController {

    @RequestMapping (value = "/addTicket", method = RequestMethod.GET)
    public String addTicket(Model model){
        return "ticket/addTicketPage";
    }
}
