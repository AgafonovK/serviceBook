package com.xvr.serviceBook.controller.webapi;

import com.xvr.serviceBook.entity.Department;
import com.xvr.serviceBook.entity.Worker;
import com.xvr.serviceBook.form.WorkerForm;
import com.xvr.serviceBook.service.impl.DepartmentServiceImpl;
import com.xvr.serviceBook.service.impl.WorkerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping(value = "workers")
public class WorkerController {

    private final WorkerServiceImpl workerService;

    private final DepartmentServiceImpl departmentService;

    @Autowired
    public WorkerController(WorkerServiceImpl workerService, DepartmentServiceImpl departmentService) {
        this.workerService = workerService;
        this.departmentService = departmentService;
    }


    @GetMapping
    public String viewWorkers(Model model){
        List<Worker> list = workerService.getAllWorker();
        for (Worker w: list){
            System.out.println(w.toString());
        }
        model.addAttribute("title", "Workers List");
        model.addAttribute("worker", list);
        return "worker/workerPage";
    }

    @RequestMapping(value = "/workerAdd", method = RequestMethod.GET)
    public String addWorker(Model model){
        WorkerForm worker = new WorkerForm();
        List<Department> list = departmentService.getAllDepartment();
        model.addAttribute("workerForm", worker);
        model.addAttribute("listDepartment", list);
        return "worker/workerAddPage";
    }

    //TODO Create validation
    @RequestMapping(value = "/workerAdd", method = RequestMethod.POST)
    public String saveWorker(Model model,@ModelAttribute("WorkerForm")  WorkerForm workerForm,
                             final RedirectAttributes redirectAttributes) {

        Worker worker;
        try {
            worker = workerService.createWorker(workerForm);
        }
        // Other error!!
        catch (Exception e) {
            model.addAttribute("errorMessage", "Error: " + e.getMessage());
            return "worker/workerAddPage";
        }
        redirectAttributes.addFlashAttribute("flashUser", worker);
        return "redirect:/worker/workerAddSuccessful";
    }

    @RequestMapping(value = "/workerAddSuccessful",method = RequestMethod.GET)
    public String viewWorkerAddSuccessful(){
        return "worker/workerAddSuccessfulPage";
    }


}
