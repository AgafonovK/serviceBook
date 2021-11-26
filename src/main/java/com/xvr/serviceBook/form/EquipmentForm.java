package com.xvr.serviceBook.form;

import com.xvr.serviceBook.entity.Department;
import com.xvr.serviceBook.entity.TypeEquipment;
import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
@ToString
@EqualsAndHashCode
@Builder
public class EquipmentForm {

    private Long id;

    @NotEmpty
    private String name;

    @NotEmpty
    @Size(min = 5, max = 100)
    private String description;

    private Department department;

    private TypeEquipment typeEquipment;
    @Size(min=5, max = 20)
    private String location;

}
