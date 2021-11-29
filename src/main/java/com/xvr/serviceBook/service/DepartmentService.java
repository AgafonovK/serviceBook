package com.xvr.serviceBook.service;

import com.xvr.serviceBook.entity.Department;
import com.xvr.serviceBook.service.servicedto.DepartmentServiceDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface DepartmentService {

    Page<Department> findAllDepartments(Pageable pageable);
    List<Department> findAllDepartmentsList();

    Optional<Department> findDepartmentById(Long id);


    Optional<Department> findFirstDepartmentByName(String name);

    List<Department> findPaginated(Pageable pageable);

    void saveDepartment(DepartmentServiceDto departmentServiceDto);

    void deleteDepartmentById(Long id);

    void updateDepartment(DepartmentServiceDto departmentServiceDto, Long id);
}
