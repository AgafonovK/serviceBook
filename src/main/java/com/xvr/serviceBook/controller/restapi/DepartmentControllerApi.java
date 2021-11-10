package com.xvr.serviceBook.controller.restapi;

import com.xvr.serviceBook.entity.Department;
import com.xvr.serviceBook.form.DepartmentForm;
import com.xvr.serviceBook.service.DepartmentService;
import com.xvr.serviceBook.service.servicedto.DepartmentServiceDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.hateoas.server.ExposesResourceFor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Max;
import java.net.URI;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@ExposesResourceFor(Department.class)
@RequestMapping(value = "/rest/departments")
public class DepartmentControllerApi {

    private final DepartmentService departmentService;
    private final PagedResourcesAssembler<Department> departmentPagedResourcesAssembler;

    public DepartmentControllerApi(DepartmentService departmentService, PagedResourcesAssembler<Department> departmentPagedResourcesAssembler) {
        this.departmentService = departmentService;
        this.departmentPagedResourcesAssembler = departmentPagedResourcesAssembler;
    }

    @GetMapping
    public ResponseEntity<PagedModel<EntityModel<Department>>> getAllDepartments(@PageableDefault(page = 0, size = 10) Pageable pageRequest) {

        Page<Department> departments = departmentService.findAllDepartments(pageRequest)
                .map(department -> {
                    department.add(linkTo(methodOn(DepartmentControllerApi.class).getAllDepartments(pageRequest)).withSelfRel());
                    department.add(linkTo(methodOn(DepartmentControllerApi.class).getDepartmentById(department.getId())).withRel("department"));
                    //department.add(linkTo(methodOn(DepartmentControllerApi.class).newDepartment())).withRel("create_new_department"));
                    department.add(linkTo(methodOn(DepartmentControllerApi.class).deleteDepartmentById(department.getId())).withRel("delete_department"));
                    return department;
                });
        PagedModel<EntityModel<Department>> model = departmentPagedResourcesAssembler
                .toModel(departments);
        return !departments.isEmpty()
                ? ResponseEntity.ok(model)
                : ResponseEntity.notFound().build();
    }


    @GetMapping(value = "/{id}")
    public ResponseEntity<EntityModel<Department>> getDepartmentById(@PathVariable(value = "id") Long id) {
        Optional<Department> department = departmentService.findDepartmentById(id);
        return  !department.isPresent()
                ? ResponseEntity.ok(new EntityModel<Department>(department.get(),
                        linkTo(methodOn(DepartmentControllerApi.class).getDepartmentById(id)).withSelfRel(),
                        //linkTo(methodOn(DepartmentControllerApi.class).newDepartment()).withRel("create_new_department"),
                        linkTo(methodOn(DepartmentControllerApi.class).deleteDepartmentById(id)).withRel("delete_department")))
                : ResponseEntity.notFound().build();
                /*department.<ResponseEntity<EntityModel<Department>>> map (value -> ResponseEntity.ok(new EntityModel(value,
                        linkTo(methodOn(DepartmentControllerApi.class).getDepartmentById(id)).withSelfRel(),
                        linkTo(methodOn(DepartmentControllerApi.class).newDepartment(new DepartmentForm(department.get().getId(), department.get().getName()))).withRel("create_new_department"),
                        linkTo(methodOn(DepartmentControllerApi.class).deleteDepartmentById(id)).withRel("delete_department"))))
                .orElseGet(() -> ResponseEntity.notFound().build());*/
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<EntityModel<Department>> deleteDepartmentById(@PathVariable(value = "id") Long id) {
        Optional<Department> departmentToDelete = departmentService.findDepartmentById(id);

        if (departmentToDelete.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        departmentService.deleteDepartmentById(id);
        Department department = new Department();
        department.add(linkTo(methodOn(DepartmentControllerApi.class).getAllDepartments(PageRequest.of(0, 10))).withRel("get_departments"));
        return ResponseEntity.ok(new EntityModel<>(department));
    }

    //TODO get id for the URI
    @PostMapping
    public ResponseEntity<Void> newDepartment(@RequestParam (value = "name") String departmentName) {
        departmentService.saveDepartment(DepartmentServiceDto.of(departmentName));
        Long id = departmentService.findDepartmentByName(DepartmentServiceDto.of(departmentName).getName()).get().getId();
        return ResponseEntity.created(URI.create("/"+id)).build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<EntityModel<Department>> updateDepartment(@Validated @RequestBody DepartmentForm departmentForm){
        Long id = departmentService.findDepartmentByName(DepartmentServiceDto.of(departmentForm.getName()).getName()).get().getId();

        Optional<Department> optional = departmentService.findDepartmentById(id);
        if (optional.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        departmentService.updateDepartment(DepartmentServiceDto.of(departmentForm.getId()));
        return ResponseEntity.ok().build();
    }
}
