package com.xvr.serviceBook.service.servicedto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DepartmentServiceDto {

    Long id;
    String name;

    public static DepartmentServiceDto of(Long id, String name){
        return new DepartmentServiceDto(id, name);
    }
    public static DepartmentServiceDto of(String name) {return new DepartmentServiceDto(name);}

    public static DepartmentServiceDto of(Long id){return new DepartmentServiceDto(id);}
    private DepartmentServiceDto(Long id, String name){
        this.id = id;
        this.name = name;
    }
    private DepartmentServiceDto(String name){
        this.name = name;
    };
    private DepartmentServiceDto(Long id){
        this.id = id;
    };
}
