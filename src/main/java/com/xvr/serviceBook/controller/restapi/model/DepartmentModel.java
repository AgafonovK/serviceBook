package com.xvr.serviceBook.controller.restapi.model;

import com.xvr.serviceBook.entity.Department;
import lombok.*;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@AllArgsConstructor
@Relation(collectionRelation = "department")
public class DepartmentModel extends RepresentationModel<DepartmentModel> {
}
