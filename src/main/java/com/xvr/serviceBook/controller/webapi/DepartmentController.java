package com.xvr.serviceBook.controller.webapi;

import com.xvr.serviceBook.entity.Department;
import com.xvr.serviceBook.form.DepartmentForm;
import com.xvr.serviceBook.repository.DepartmentRepository;
import com.xvr.serviceBook.service.impl.DepartmentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(value = "web/departments")
public class DepartmentController {

    @Autowired
    DepartmentServiceImpl departmentService;

    @Autowired
    DepartmentRepository departmentRepository;

    @RequestMapping(method = RequestMethod.GET)
    public String viewDepartments(@RequestParam(value = "page") Optional<Integer> page,
                                  @RequestParam(value = "size") Optional<Integer> size,
                                  Model model) {
        int currentPage = page.orElse(0);
        int pageSize = size.orElse(10);
        Pageable pageable = PageRequest.of(currentPage,pageSize);
        List<Department> departments = departmentService.findPaginated(pageable);
        model.addAttribute("title", "Department List");
        model.addAttribute("departments", departments);
        return "department/departmentPage";
    }

    @RequestMapping(value = "/{departmentId}", method = RequestMethod.GET)
    public String viewDepartmentById(@PathVariable("departmentId") Long departmentId) {
        Optional<Department> department = departmentRepository.findById(departmentId);
        if (department.isEmpty()) throw new EntityNotFoundException("id-" + departmentId);
        return "";
    }

    @RequestMapping(value = "create-department", method = RequestMethod.GET)
    public String createDepartment(Model model) {
        DepartmentForm departmentForm = new DepartmentForm();
        model.addAttribute("departmentForm", departmentForm);
        return "department/createDepartmentPage";
    }

    @PostMapping
    public String saveDepartment(@ModelAttribute("departmentForm") DepartmentForm departmentForm,
                                 final RedirectAttributes redirectAttributes,
                                 Model model) {
        //long id = departmentRepository.findAll().size();
        //Department department = new Department(id + 1, departmentForm.getName());
        Department department = new Department();
        department.setName(departmentForm.getName());
        if (departmentForm.getName().isEmpty()){
            redirectAttributes.addAttribute("nameError", "Error: name is Empty");
            return "redirect:/departments/createDepartment";
        }
        try {
            departmentRepository.saveAndFlush(department);
        }
        // Other error!!
        catch (Exception e) {
            model.addAttribute("errorMessage", "Error: " + e.getMessage());
            return "department/createDepartmentPage";
        }
        redirectAttributes.addFlashAttribute("flashUser", department);
        return "redirect:/departments/departmentAddSuccessful";
    }

    @RequestMapping(value = "department-add-successful", method = RequestMethod.GET)
    public String viewDepartmentAddSuccessful() {
        return "department/departmentAddSuccessfulPage";
    }

}
