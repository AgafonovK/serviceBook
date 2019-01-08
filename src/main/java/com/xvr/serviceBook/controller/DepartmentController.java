package com.xvr.serviceBook.controller;

import com.xvr.serviceBook.entity.Department;
import com.xvr.serviceBook.form.DepartmentForm;
import com.xvr.serviceBook.repository.DepartmentRepository;
import com.xvr.serviceBook.service.impl.DepartmentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping(value = "department")
public class DepartmentController {

    @Autowired
    DepartmentServiceImpl departmentService;

    @Autowired
    DepartmentRepository departmentRepository;

    @RequestMapping(value = "/department", method = RequestMethod.GET)
    public String viewDepartment(Model model){

        List<Department> list = departmentRepository.findAll();
        model.addAttribute("title", "Department List");
        model.addAttribute("department", list);
        return "department/departmentPage";
    }

    @RequestMapping(value = "/departmentAdd", method = RequestMethod.GET)
    public String addDepartment(Model model){
        DepartmentForm equipment = new DepartmentForm();
        model.addAttribute("departmentForm", equipment);
        return "department/departmentAddPage";
    }


    @RequestMapping(value = "/departmentAdd", method = RequestMethod.POST)
    public String saveDepartment(Model model,@ModelAttribute("DepartmentForm") DepartmentForm departmentForm,
                             final RedirectAttributes redirectAttributes) {

        Long id = (long) departmentRepository.findAll().size();
        Department department = new Department(id+1,departmentForm.getName());
        try {
            departmentRepository.saveAndFlush(department);
        }
        // Other error!!
        catch (Exception e) {
            model.addAttribute("errorMessage", "Error: " + e.getMessage());
            return "department/departmentAddPage";
        }
        redirectAttributes.addFlashAttribute("flashUser", department);
        return "redirect:/department/departmentAddSuccessful";
    }

    @RequestMapping(value = "/departmentAddSuccessful", method = RequestMethod.GET)
    public String viewDepartmentAddSuccessful(){
        return "department/departmentAddSuccessfulPage";
    }

}
