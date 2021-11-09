package com.xvr.serviceBook.controller.restapi;

import com.xvr.serviceBook.entity.Department;
import com.xvr.serviceBook.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.hateoas.server.ExposesResourceFor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@ExposesResourceFor(Department.class)
@RequestMapping(value = "/rest/departments")
public class DepartmentControllerApi {

    private final DepartmentRepository departmentRepository;
    private final PagedResourcesAssembler<Department> departmentPagedResourcesAssembler;


    @Autowired
    public DepartmentControllerApi(DepartmentRepository departmentRepository, PagedResourcesAssembler<Department> departmentPagedResourcesAssembler) {
        this.departmentRepository = departmentRepository;
        this.departmentPagedResourcesAssembler = departmentPagedResourcesAssembler;
    }

    @GetMapping
    public ResponseEntity<PagedModel<EntityModel<Department>>> getAllDepartments(
            @PageableDefault(page = 0, size = 10) Pageable pageRequest
    ){
        Page<Department> departments = departmentRepository.findAll(pageRequest)
                .map(department -> {
                    department.add(linkTo(methodOn(DepartmentControllerApi.class).getAllDepartments(pageRequest)).withSelfRel());
                    department.add(linkTo(methodOn(DepartmentControllerApi.class).getDepartmentById(department.getId())).withRel("department"));
                    return department;
                });
        PagedModel<EntityModel<Department>> model = departmentPagedResourcesAssembler
                .toModel(departments);
        return !departments.isEmpty()
            ? ResponseEntity.ok(model)
            : ResponseEntity.notFound().build();
    }


    @GetMapping(value = "/{id}")
    public ResponseEntity<EntityModel<Department>> getDepartmentById(@PathVariable (value = "id") Long id){
        Optional<Department> department = departmentRepository.findById(id);

        return !department.isEmpty()
                ? ResponseEntity.ok(new EntityModel(department.get(),
                linkTo(methodOn(DepartmentControllerApi.class).getDepartmentById(id)).withSelfRel()))
                : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Void> newDepartment(@RequestBody Department department){
        return ResponseEntity.ok().build();
    }
}
