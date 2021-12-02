package com.xvr.serviceBook.controller.webapi;

import com.xvr.serviceBook.entity.Department;
import com.xvr.serviceBook.entity.Worker;
import com.xvr.serviceBook.form.WorkerForm;
import com.xvr.serviceBook.service.impl.DepartmentServiceImpl;
import com.xvr.serviceBook.service.impl.WorkerServiceImpl;
import com.xvr.serviceBook.service.servicedto.WorkerServiceDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping(value = "/web/workers")
public class WorkerController {

    private final WorkerServiceImpl workerService;

    private final DepartmentServiceImpl departmentService;

    private final ModelMapper modelMapper;

    @Autowired
    public WorkerController(WorkerServiceImpl workerService, DepartmentServiceImpl departmentService, ModelMapper modelMapper) {
        this.workerService = workerService;
        this.departmentService = departmentService;
        this.modelMapper = modelMapper;
    }


    @GetMapping
    public String viewWorkers(Model model) {
        List<Worker> list = workerService.findAllWorker();
        model.addAttribute("title", "Workers List");
        model.addAttribute("workers", list);
        return "worker/workerPage";
    }

    @RequestMapping(value = "/worker-create", method = RequestMethod.GET)
    public String addWorker(Model model) {
        WorkerForm worker = new WorkerForm();
        List<Department> list = departmentService.findAllDepartmentsList();
        model.addAttribute("workerForm", worker);
        model.addAttribute("listDepartment", list);
        return "worker/workerAddPage";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String saveWorker(@Validated @ModelAttribute("WorkerForm") WorkerForm workerForm,
                             BindingResult bindingResult,
                             final RedirectAttributes redirectAttributes,
                             Model model) {
        try {
            workerService.createWorker(modelMapper.map(workerForm, WorkerServiceDto.class));
        }
        catch (Exception e) {
            model.addAttribute("errorMessage", "Error: " + e.getMessage());
            return "worker/workerAddPage";
        }
        redirectAttributes.addFlashAttribute("flashUser", workerForm);
        return "redirect:/worker/workerAddSuccessful";
    }

    @RequestMapping(value = "/workerAddSuccessful", method = RequestMethod.GET)
    public String viewWorkerAddSuccessful() {
        return "worker/workerAddSuccessfulPage";
    }


}
