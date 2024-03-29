package com.xvr.serviceBook.controller.webapi;

import com.xvr.serviceBook.entity.Equipment;
import com.xvr.serviceBook.entity.Report;
import com.xvr.serviceBook.form.ReportForm;
import com.xvr.serviceBook.repository.EquipmentRepository;
import com.xvr.serviceBook.repository.ReportRepository;
import com.xvr.serviceBook.repository.WorkerRepository;
import com.xvr.serviceBook.service.WorkerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping(value = "/web/reports")
public class ReportController {

    private final ReportRepository reportRepository;

    private final EquipmentRepository equipmentRepository;

    private final WorkerService workerService;

    public ReportController(ReportRepository reportRepository, EquipmentRepository equipmentRepository,
                            WorkerService workerService) {
        this.reportRepository = reportRepository;
        this.equipmentRepository = equipmentRepository;
        this.workerService = workerService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Object> viewReport(Model model) {

        List<Report> list = reportRepository.findAll();
        //model.addAttribute("title", "Report List");
        //model.addAttribute("report", list);
        return new ResponseEntity<Object>(list, HttpStatus.ACCEPTED);
        //return "report/reportPage";
    }

    @RequestMapping(value = "/{reportId}", method = RequestMethod.GET)
    public String viewReportById(@RequestParam("reportId") Long reportId){
        Optional<Report> report = reportRepository.findById(reportId);
        if (report.isEmpty()) throw new EntityNotFoundException("Id " + reportId);
        return "report/reportPage";
    }
    @RequestMapping(value = "/report-add", method = RequestMethod.GET)
    public String addReport(Model model) {
        ReportForm report = new ReportForm();
        List<Equipment> equipmentList = equipmentRepository.findAll();
        model.addAttribute("reportForm", report);
        model.addAttribute("listWorkers", workerService.findAllWorker());
        model.addAttribute("listEquipment", equipmentList);

        return "report/reportAddPage";
    }


    @RequestMapping(method = RequestMethod.POST)
    public String saveReport(Model model, @ModelAttribute("ReportForm") ReportForm reportForm,
                             final RedirectAttributes redirectAttributes) {

        long id = (long) reportRepository.findAll().size();
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
            return "reports/reportAddPage";
        }
        redirectAttributes.addFlashAttribute("flashUser", report);
        return "redirect:/reports/reportAddSuccessful";
    }

    @RequestMapping(value = "/report-add-successful", method = RequestMethod.GET)
    public String viewReportAddSuccessful() {
        return "report/reportAddSuccessfulPage";
    }


    @PostMapping(value = "/reportFindByEquipmentName")
    public String findReportByEquipmentName(Model model, @RequestParam ("equipmentName") String equipmentName) {
        System.out.println("equipmentName = " + equipmentName);

        List<Report> listReport = reportRepository.findReportByEquipmentName(equipmentName);
        model.addAttribute("listReportByEquipmentName", listReport);
        return "report/reportFindByEquipmentNamePage";
    }


}
