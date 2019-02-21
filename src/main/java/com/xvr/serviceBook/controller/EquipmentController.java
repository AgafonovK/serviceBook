package com.xvr.serviceBook.controller;

import com.xvr.serviceBook.entity.Department;
import com.xvr.serviceBook.entity.Equipment;
import com.xvr.serviceBook.entity.Worker;
import com.xvr.serviceBook.form.EquipmentForm;
import com.xvr.serviceBook.form.WorkerForm;
import com.xvr.serviceBook.repository.EquipmentRepository;
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
@RequestMapping(value = "/equipment")
public class EquipmentController {

    @Autowired
    EquipmentRepository equipmentRepository;
    @Autowired
    DepartmentServiceImpl departmentService;

    @RequestMapping(value = "equipment", method = RequestMethod.GET)
    public String viewEquipment(Model model){

        List<Equipment> list = equipmentRepository.findAll();
        for (int i=0; i<list.size();i++){
            System.out.println(list.get(i).getDepartment().getName());
        }
        model.addAttribute("title", "Equipment List");
        model.addAttribute("equipment", list);
        return "equipment/equipmentPage";
    }

    @RequestMapping(value = "equipmentAdd", method = RequestMethod.GET)
    public String addEquipment(Model model){
        EquipmentForm equipment = new EquipmentForm();
        List<Department> list = departmentService.getAllDepartment();
        model.addAttribute("equipmentForm", equipment);
        model.addAttribute("listDepartment", list);
        return "equipment/equipmentAddPage";
    }

    @RequestMapping(value = "equipmentAdd", method = RequestMethod.POST)
    public String saveEquipment(Model model,@ModelAttribute("EquipmentForm") EquipmentForm equipmentForm,
                             final RedirectAttributes redirectAttributes) {

        Long id = (long) equipmentRepository.findAll().size();
        Equipment equipment = new Equipment(id+1,equipmentForm.getName(),equipmentForm.getDescription(),equipmentForm.getDepartment(),equipmentForm.getTypeEquipment(),equipmentForm.getLocation());
        try {
             equipmentRepository.saveAndFlush(equipment);
        }
        // Other error!!
        catch (Exception e) {
            model.addAttribute("errorMessage", "Error: " + e.getMessage());
            return "equipment/equipmentAddPage";
        }
        redirectAttributes.addFlashAttribute("flashUser", equipment);
        return "redirect:/equipment/equipmentAddSuccessful";
    }

    @RequestMapping(value = "equipmentAddSuccessful",method = RequestMethod.GET)
    public String viewEquipmentAddSuccessful(){
        return "equipment/equipmentAddSuccessfulPage";
    }
}
