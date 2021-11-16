package com.xvr.serviceBook.controller.webapi;

import com.xvr.serviceBook.entity.Department;
import com.xvr.serviceBook.form.DepartmentForm;
import com.xvr.serviceBook.repository.DepartmentRepository;
import com.xvr.serviceBook.service.impl.DepartmentServiceImpl;
import com.xvr.serviceBook.service.servicedto.DepartmentServiceDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

//TODO add delete-department
@Controller
@RequestMapping(value = "web/departments")
public class DepartmentController {


    private final DepartmentServiceImpl departmentService;

    @Autowired
    public DepartmentController(DepartmentServiceImpl departmentService) {
        this.departmentService = departmentService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String viewDepartments(@RequestParam(value = "page") Optional<Integer> page,
                                  @RequestParam(value = "size") Optional<Integer> size,
                                  Model model) {
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(5);
        Page<Department> departmentPage = departmentService.findAllDepartments(PageRequest.of(currentPage-1,pageSize));
        model.addAttribute("title", "Department List");
        model.addAttribute("departments", departmentPage);
        return "department/departmentsPage";
    }

    @RequestMapping(value = "/{departmentId}", method = RequestMethod.GET)
    public String viewDepartmentById(@RequestParam("departmentId") Long departmentId,
                                     Model model) {
        Optional<Department> department = departmentService.findDepartmentById(departmentId);
        if (department.isEmpty()) throw new EntityNotFoundException("id-" + departmentId);
        model.addAttribute("department", department);
        return department.toString();
    }

    @RequestMapping(value = "create-department", method = RequestMethod.GET)
    public String createDepartment(Model model) {
        DepartmentForm departmentForm = new DepartmentForm();
        Pageable pageable = PageRequest.of(0, 5);
        List<Department> departments = departmentService.findPaginated(pageable);
        model.addAttribute("title", "Department List");
        model.addAttribute("departments", departments);
        model.addAttribute("departmentForm", departmentForm);

        return "department/createDepartmentPage";
    }

    @PostMapping
    public String saveDepartment(@Validated @ModelAttribute("departmentForm") DepartmentForm departmentForm,
                                 final RedirectAttributes redirectAttributes,
                                 BindingResult bindingResult,
                                 Model model) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addAttribute("nameError", "Error: name is Empty");
            return "redirect:/web/departments/create-department";
        }
        try {
            departmentService.saveDepartment(DepartmentServiceDto.of(departmentForm.getName()));
        }
        // Other error!!
        catch (Exception e) {
            model.addAttribute("errorMessage", "Error: " + e.getMessage());
            return "department/createDepartmentPage";
        }

        redirectAttributes.addFlashAttribute("flashDep", departmentForm);
        return "redirect:/web/departments/department-add-successful";
    }

    @RequestMapping(value = "department-add-successful", method = RequestMethod.GET)
    public String viewDepartmentAddSuccessful(Model model) {
        Pageable pageable = PageRequest.of(0, 5);
        List<Department> departments = departmentService.findPaginated(pageable);
        model.addAttribute("departments", departments);
        return "department/departmentAddSuccessfulPage";
    }

    @RequestMapping(value = "/{departmentId}", method = RequestMethod.DELETE)
    public String viewDeleteDepartment(@RequestParam(value = "departmentId") Long departmentId){
        System.out.println("DEPARTMENT ID " + departmentId);
        departmentService.deleteDepartmentById(departmentId);
        return "redirect:/web/departments";
    }

}
