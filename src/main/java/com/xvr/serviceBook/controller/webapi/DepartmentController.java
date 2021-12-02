package com.xvr.serviceBook.controller.webapi;

import com.xvr.serviceBook.entity.Department;
import com.xvr.serviceBook.form.DepartmentForm;
import com.xvr.serviceBook.service.impl.DepartmentServiceImpl;
import com.xvr.serviceBook.service.servicedto.DepartmentServiceDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.persistence.EntityNotFoundException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;


@Controller
@RequestMapping(value = "/web/departments")
public class DepartmentController {


    private final DepartmentServiceImpl departmentService;
    private static final Logger logger = LoggerFactory.getLogger(DepartmentController.class);

    @Autowired
    public DepartmentController(DepartmentServiceImpl departmentService) {
        this.departmentService = departmentService;
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(method = RequestMethod.GET)
    public String viewDepartments(@PageableDefault(size = 5) Pageable pageable,
                                  Model model) {
        Page<Department> departmentPage = departmentService.findAllDepartments(pageable);
        model.addAttribute("title", "Department List");
        model.addAttribute("departments", departmentPage);
        return "department/departmentsPage";
    }

    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    @RequestMapping(value = "/{departmentId}", method = RequestMethod.GET)
    public String viewDepartmentById(@PathVariable("departmentId") Long departmentId,
                                     Model model) {

        Optional<Department> department;
        try {
            department = departmentService.findDepartmentById(departmentId);
        }catch (Exception e){
            //TODO
            model.addAttribute("errorFindDepartment", "");
            logger.error("Exception in department service, findById: " + Arrays.toString(e.getStackTrace()));
            return "department/departmentPage";
        }
        model.addAttribute("department", department.get());
        //TODO refactor page
        return "department/departmentPage";
    }

    @PreAuthorize("hasAnyAuthority('USERPLUS', 'ADMIN')")
    @RequestMapping(value = "create-department", method = RequestMethod.GET)
    public String createDepartment(Model model) {
        DepartmentForm departmentForm = new DepartmentForm();
        Pageable pageable = PageRequest.of(0, 5);
        List<Department> departments;
        try {
            departments = departmentService.findPaginated(pageable);
        }catch (Exception e){
            //TODO add to model attribute
            model.addAttribute("errorGetDepartment", " ");
            logger.error("Exception on get all departments service: " + Arrays.toString(e.getStackTrace()));
            return "department/createDepartmentPage";
        }
        model.addAttribute("title", "Department List");
        model.addAttribute("departments", departments);
        model.addAttribute("departmentForm", departmentForm);
        return "department/createDepartmentPage";
    }

    @PreAuthorize("hasAnyAuthority('USERPLUS','ADMIN')")
    @PostMapping
    public String saveDepartment(@Validated @ModelAttribute("departmentForm") DepartmentForm departmentForm,
                                 BindingResult bindingResult,
                                 RedirectAttributes redirectAttributes,
                                 Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("departmentForm", departmentForm);
            model.addAttribute("nameError", "Не верно заполнены поля");
            logger.warn("Check validation in department Form: " + Objects.requireNonNull(bindingResult.getFieldError()).getField());
            return "department/create-department";
        }
        try {
            departmentService.saveDepartment(DepartmentServiceDto.of(departmentForm.getName()));
        } catch (Exception e) {
            logger.error("Some error in saveDepartmentService: " + Arrays.toString(e.getStackTrace()));
            model.addAttribute("errorMessage", "Error: " + e.getMessage());
            return "department/createDepartmentPage";
        }
        logger.info("New department " + departmentForm.getName() + " as added to DB.");
        redirectAttributes.addFlashAttribute("flashDep", departmentForm);
        return "redirect:/web/departments/department-add-successful";
    }

    @PreAuthorize("hasAnyAuthority('USERPLUS','ADMIN')")
    @RequestMapping(value = "department-add-successful", method = RequestMethod.GET)
    public String viewDepartmentAddSuccessful(Model model) {
        Pageable pageable = PageRequest.of(0, 5);
        List<Department> departments = departmentService.findPaginated(pageable);
        model.addAttribute("departments", departments);
        return "department/departmentAddSuccessfulPage";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = "/{departmentId}", method = RequestMethod.DELETE)
    public String viewDeleteDepartment(@RequestParam(value = "departmentId") Long departmentId,
                                       Model model) {
        try {
            departmentService.deleteDepartmentById(departmentId);
        }catch (Exception e) {
            logger.info("Delete department saveDepartmentService: " + Arrays.toString(e.getStackTrace()));
            //TODO
            model.addAttribute("errorDeleteDepartment",  "Department not delete");
            return "department/departmentsPage";
        }
        return "redirect:/web/departments";
    }

}
