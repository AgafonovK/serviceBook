package com.xvr.serviceBook.controller.restapi;

import com.xvr.serviceBook.controller.restapi.dtorepresentation.DepartmentRepresentation;
import com.xvr.serviceBook.entity.Department;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
public class PaginationModelAssembler implements RepresentationModelAssembler<Department, DepartmentRepresentation> {

    //TODO Add link's
    @Override
    public DepartmentRepresentation toModel(Department department) {
        Class<DepartmentControllerApi> controllerApiClass = DepartmentControllerApi.class;
        DepartmentRepresentation departmentRepresentation = buildDeparmentRepresentation(department);
        return null;
    }

    private DepartmentRepresentation buildDeparmentRepresentation(Department department){
        return DepartmentRepresentation.builder()
                .id(department.getId())
                .name(department.getName())
                .build();
    }
}
