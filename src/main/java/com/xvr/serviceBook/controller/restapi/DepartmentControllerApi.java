package com.xvr.serviceBook.controller.restapi;

import com.xvr.serviceBook.controller.restapi.assemblers.DepartmentPaginationModelAssembler;
import com.xvr.serviceBook.controller.restapi.dtorepresentation.DepartmentRepresentation;
import com.xvr.serviceBook.entity.Department;
import com.xvr.serviceBook.entity.Ticket;
import com.xvr.serviceBook.form.DepartmentForm;
import com.xvr.serviceBook.service.DepartmentService;
import com.xvr.serviceBook.service.TicketService;
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
    private final DepartmentPaginationModelAssembler departmentPaginationModelAssembler;
    private final TicketService ticketService;

    public DepartmentControllerApi(DepartmentService departmentService, PagedResourcesAssembler<Department> departmentPagedResourcesAssembler,
                                   TicketService ticketService, DepartmentPaginationModelAssembler departmentPaginationModelAssembler) {
        this.departmentService = departmentService;
        this.departmentPagedResourcesAssembler = departmentPagedResourcesAssembler;
        this.ticketService = ticketService;
        this.departmentPaginationModelAssembler = departmentPaginationModelAssembler;
    }
//https://computingfacts.com/post/Spring-HATEOAS-Adding-Pagination-Links-To-RESTful-API
    @GetMapping //https://grapeup.com/blog/how-to-build-hypermedia-api-with-spring-hateoas/
    public ResponseEntity<PagedModel<DepartmentRepresentation>> getAllDepartments(@PageableDefault(page = 0, size = 5) Pageable pageRequest) {
        Page<Department> departments = departmentService.findAllDepartments(pageRequest);

        PagedModel<DepartmentRepresentation> model = departmentPagedResourcesAssembler
                .toModel(departments, departmentPaginationModelAssembler);
        return !departments.isEmpty()
                ? ResponseEntity.ok(model)
                : ResponseEntity.notFound().build();
    }


    @GetMapping(value = "/{id}")
    public ResponseEntity<EntityModel<Department>> getDepartmentById(@PathVariable(value = "id") Long id) {
        Optional<Department> department = departmentService.findDepartmentById(id);
        return department.isEmpty()
                ? ResponseEntity.ok(new EntityModel<Department>(department.get(),
                linkTo(methodOn(DepartmentControllerApi.class).getDepartmentById(id)).withSelfRel(),
                //linkTo(methodOn(DepartmentControllerApi.class).saveDepartment()).withRel("create_new_department"),
                linkTo(methodOn(DepartmentControllerApi.class).deleteDepartmentById(id)).withRel("delete_department")))
                : ResponseEntity.notFound().build();
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<EntityModel<Department>> deleteDepartmentById(@PathVariable(value = "id") Long id) {
        Optional<Department> departmentToDelete = departmentService.findDepartmentById(id);

        if (departmentToDelete.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        departmentService.deleteDepartmentById(id);
        Department department = new Department();
        department.add(linkTo(methodOn(DepartmentControllerApi.class).getAllDepartments(PageRequest.of(1, 10))).withRel("get_departments"));
        return ResponseEntity.ok(new EntityModel<>(department));
    }

    @PostMapping
    public ResponseEntity<EntityModel<Department>> saveDepartment(@RequestParam(value = "name") String departmentName) {
        if (departmentService.findFirstDepartmentByName(DepartmentServiceDto.of(departmentName).getName()).isPresent()) {
            //Unprocessable Entity
            return ResponseEntity.status(422).build();
        } else {
            departmentService.saveDepartment(DepartmentServiceDto.of(departmentName));
            Department department = departmentService.findFirstDepartmentByName(DepartmentServiceDto.of(departmentName).getName()).get();
            department.add(
                    linkTo(methodOn(DepartmentControllerApi.class).getDepartmentById(department.getId())).withSelfRel(),
                    linkTo(methodOn(DepartmentControllerApi.class).deleteDepartmentById(department.getId())).withRel("delete_department")
            );
            return ResponseEntity.created(URI.create("/" + department.getId())).body(new EntityModel<>(department));
        }
    }

    // TODO check request if error
    @PutMapping(value = "/{id}")
    public ResponseEntity<EntityModel<Department>> updateDepartment(@Validated @RequestBody DepartmentForm departmentForm,
                                                                    @PathVariable(value = "id") Long id) {
        if (departmentService.findDepartmentById(id).isPresent()) {
            departmentService.updateDepartment(DepartmentServiceDto.of(departmentForm.getName()), id);
            Optional<Department> department = departmentService.findDepartmentById(id);
            department.ifPresent(depart -> depart.add(
                    linkTo(methodOn(DepartmentControllerApi.class).getDepartmentById(depart.getId())).withSelfRel(),
                    linkTo(methodOn(DepartmentControllerApi.class).deleteDepartmentById(depart.getId())).withRel("delete_department")
            ));
            return ResponseEntity.ok().body(new EntityModel<>(department.get()));
        } else {
            return ResponseEntity.badRequest().build();
        }

    }
    //TODO
    @GetMapping(value = "/{id}/tickets")
    public ResponseEntity<PagedModel<EntityModel<Ticket>>> getDepartmentTickets(@PageableDefault(page = 0, size = 10) Pageable pageRequest,
                                                                                @PathVariable(name = "id") Long departmentId) {
        Page<Ticket> ticketsByDepartmentId = ticketService.findTicketsByDepartmentId(pageRequest, departmentId);
        return null;
    }
}
