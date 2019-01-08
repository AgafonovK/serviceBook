package com.xvr.serviceBook.controller;

import com.xvr.serviceBook.entity.Equipment;
import com.xvr.serviceBook.entity.Report;
import com.xvr.serviceBook.form.ReportForm;
import com.xvr.serviceBook.repository.EquipmentRepository;
import com.xvr.serviceBook.repository.ReportRepository;
import com.xvr.serviceBook.repository.WorkerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping(value = "report")
public class ReportController {

    @Autowired
    ReportRepository reportRepository;

    @Autowired
    EquipmentRepository equipmentRepository;

    @Autowired
    WorkerRepository workerRepository;

    @RequestMapping(value = "/report", method = RequestMethod.GET)
    public String viewReport(Model model) {

        List<Report> list = reportRepository.findAll();
        model.addAttribute("title", "Report List");
        model.addAttribute("report", list);
        return "report/reportPage";
    }

    @RequestMapping(value = "/reportAdd", method = RequestMethod.GET)
    public String addReport(Model model) {
        ReportForm report = new ReportForm();
        List<Equipment> equipmentList = equipmentRepository.findAll();
        model.addAttribute("reportForm", report);
        model.addAttribute("listWorkers", workerRepository.findAll());
        model.addAttribute("listEquipment", equipmentList);

        return "report/reportAddPage";
    }


    @RequestMapping(value = "/reportAdd", method = RequestMethod.POST)
    public String saveReport(Model model, @ModelAttribute("ReportForm") ReportForm reportForm,
                             final RedirectAttributes redirectAttributes) {

        Long id = (long) reportRepository.findAll().size();
        Report report = new Report(id + 1, reportForm.getReportName(), reportForm.getStartDateReport(),
                reportForm.getEndDateReport(), reportForm.getDescriptionReport(),
                reportForm.getEquipment(), reportForm.getWorker()
        );
        try {
            reportRepository.saveAndFlush(report);
        }
        // Other error!!
        catch (Exception e) {
            model.addAttribute("errorMessage", "Error: " + e.getMessage());
            return "report/reportAddPage";
        }
        redirectAttributes.addFlashAttribute("flashUser", report);
        return "redirect:/report/reportAddSuccessful";
    }

    @RequestMapping(value = "/reportAddSuccessful", method = RequestMethod.GET)
    public String viewReportAddSuccessful() {
        return "report/reportAddSuccessfulPage";
    }


    @PostMapping(value = "/reportFindByEquipmentName")
    public String findReportByEquipmentName(Model model, @RequestParam ("equipmentName") String equipmentName) {
        System.out.println("equipmentName = " + equipmentName);

        List<Report> listReprot = reportRepository.findReportByEquipmentName(equipmentName);
        model.addAttribute("listReportByEquipmentName", listReprot);
        return "report/reportFindByEquipmentNamePage";
    }


}
