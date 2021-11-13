package com.xvr.serviceBook.form;

import lombok.*;
import org.springframework.hateoas.server.core.Relation;

@Data
@Getter
@Setter
@Builder
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Relation(collectionRelation = "department")
public class DepartmentForm {

    private String name;

}
