package com.xvr.serviceBook.service.servicedto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class DepartmentServiceDto {

    private String name;

    public static DepartmentServiceDto of(String name) {return new DepartmentServiceDto(name);}

    private DepartmentServiceDto(String name){
        this.name = name;
    }
}
