package com.xvr.serviceBook.form;

import com.xvr.serviceBook.entity.Department;
import com.xvr.serviceBook.entity.TypeEquipment;
import lombok.*;

import javax.validation.constraints.NotEmpty;

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
    private String description;

    private Department department;

    private TypeEquipment typeEquipment;

    private String location;

}
