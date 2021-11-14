package com.xvr.serviceBook.form;

import lombok.*;
import org.springframework.hateoas.server.core.Relation;

import javax.validation.constraints.NotEmpty;

@Data
@Getter
@Setter
@Builder
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Relation(collectionRelation = "department")
public class DepartmentForm {

    @NotEmpty
    private String name;

}
