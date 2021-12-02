package com.xvr.serviceBook.controller.restapi;

import com.xvr.serviceBook.controller.restapi.assemblers.DepartmentPaginationModelAssembler;
import com.xvr.serviceBook.controller.restapi.assemblers.TicketPaginationModelAssembler;
import com.xvr.serviceBook.controller.restapi.dtorepresentation.DepartmentModelRepresentation;
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
    private final PagedResourcesAssembler<Ticket> ticketPagedResourcesAssembler;
    private final TicketPaginationModelAssembler ticketPaginationModelAssembler;

    public DepartmentControllerApi(DepartmentService departmentService, PagedResourcesAssembler<Department> departmentPagedResourcesAssembler,
                                   TicketService ticketService, DepartmentPaginationModelAssembler departmentPaginationModelAssembler,
                                   PagedResourcesAssembler<Ticket> ticketPagedResourcesAssembler, TicketPaginationModelAssembler ticketPaginationModelAssembler) {
        this.departmentService = departmentService;
        this.departmentPagedResourcesAssembler = departmentPagedResourcesAssembler;
        this.ticketService = ticketService;
        this.departmentPaginationModelAssembler = departmentPaginationModelAssembler;
        this.ticketPagedResourcesAssembler = ticketPagedResourcesAssembler;
        this.ticketPaginationModelAssembler = ticketPaginationModelAssembler;
    }

    @GetMapping
    public ResponseEntity<PagedModel<DepartmentModelRepresentation>> getAllDepartments(@PageableDefault(size = 5) Pageable pageRequest) {
        Page<Department> departments = departmentService.findAllDepartments(pageRequest);

        PagedModel<DepartmentModelRepresentation> model = departmentPagedResourcesAssembler
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
    public ResponseEntity<EntityModel<DepartmentModelRepresentation>> deleteDepartmentById(@PathVariable(value = "id") Long id) {
        Optional<Department> departmentToDelete = departmentService.findDepartmentById(id);

        if (departmentToDelete.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        departmentService.deleteDepartmentById(id);
        DepartmentModelRepresentation department = DepartmentModelRepresentation.builder().build();
        department.add(linkTo(methodOn(DepartmentControllerApi.class).getAllDepartments(PageRequest.of(1, 10))).withRel("get_departments"));
        return ResponseEntity.ok(new EntityModel<>(department));
    }

    @PostMapping
    public ResponseEntity<EntityModel<DepartmentModelRepresentation>> saveDepartment(@RequestParam(value = "name") String departmentName) {
        if (departmentService.findFirstDepartmentByName(DepartmentServiceDto.of(departmentName).getName()).isPresent()) {
            //Unprocessable Entity
            return ResponseEntity.status(422).build();
        } else {
            departmentService.saveDepartment(DepartmentServiceDto.of(departmentName));
            Department department = departmentService.findFirstDepartmentByName(DepartmentServiceDto.of(departmentName).getName()).get();
            DepartmentModelRepresentation departmentModelRepresentation = DepartmentModelRepresentation.builder()
                    .id(department.getId())
                    .name(department.getName())
                    //.departmentTickets(ticketPaginationModelAssembler.toCollectionModel(department.getTickets()))
                    .build();
            departmentModelRepresentation.add(
                    linkTo(methodOn(DepartmentControllerApi.class).getDepartmentById(department.getId())).withSelfRel(),
                    linkTo(methodOn(DepartmentControllerApi.class).deleteDepartmentById(department.getId())).withRel("delete_department")
            );
            return ResponseEntity.created(URI.create("/" + departmentModelRepresentation.getId())).body(new EntityModel<>(departmentModelRepresentation));
        }
    }

    // TODO check request if error
    @PutMapping(value = "/update/{id}")
    public ResponseEntity<EntityModel<DepartmentModelRepresentation>> updateDepartment(@Validated @RequestBody DepartmentForm departmentForm,
                                                                    @PathVariable(value = "id") Long id) {
        if (departmentService.findDepartmentById(id).isPresent()) {
            departmentService.updateDepartment(DepartmentServiceDto.of(departmentForm.getName()), id);
            Optional<Department> department = departmentService.findDepartmentById(id);
            if (department.isPresent()){
                DepartmentModelRepresentation departmentModelRepresentation = DepartmentModelRepresentation.builder()
                        .id(department.get().getId())
                        .name(department.get().getName())
                        .departmentTickets(ticketPaginationModelAssembler.toCollectionModel(department.get().getTickets()))
                        .build().add(
                                linkTo(methodOn(DepartmentControllerApi.class).getDepartmentById(department.get().getId())).withSelfRel(),
                                linkTo(methodOn(DepartmentControllerApi.class).deleteDepartmentById(department.get().getId())).withRel("delete_department")
                        );
                return ResponseEntity.ok().body(new EntityModel<>(departmentModelRepresentation));
            }else {
                return ResponseEntity.badRequest().build();
            }
        } else {
            return ResponseEntity.badRequest().build();
        }

    }

    //TODO
    @GetMapping(value = "/{id}/tickets")
    public ResponseEntity<PagedModel<Ticket>> getDepartmentTickets(@PageableDefault(size = 5) Pageable pageRequest,
                                                                   @PathVariable(name = "id") Long departmentId) {
        Page<Ticket> ticketsByDepartmentId = ticketService.findTicketsByDepartmentId(pageRequest, departmentId);
        return null;
    }
}
