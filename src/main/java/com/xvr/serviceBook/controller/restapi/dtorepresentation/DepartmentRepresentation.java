package com.xvr.serviceBook.controller.restapi.dtorepresentation;

import com.xvr.serviceBook.entity.Ticket;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.springframework.hateoas.RepresentationModel;

import javax.persistence.Column;
import javax.persistence.OneToMany;
import java.util.Set;

@Getter
@Builder
@EqualsAndHashCode(callSuper = false)
public class DepartmentRepresentation extends RepresentationModel<DepartmentRepresentation> {

    private Long id;
    private String name;

}
