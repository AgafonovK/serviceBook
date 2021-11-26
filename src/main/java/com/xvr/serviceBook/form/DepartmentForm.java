package com.xvr.serviceBook.form;

import lombok.*;
import org.springframework.hateoas.server.core.Relation;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@Getter
@Setter
@Builder
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Relation(collectionRelation = "department")
public class DepartmentForm {

    @NotNull
    @Min(1)
    private Long id;

    @NotEmpty
    private String name;

}
