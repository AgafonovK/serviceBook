package com.xvr.serviceBook.service;

import com.xvr.serviceBook.entity.Department;
import com.xvr.serviceBook.form.DepartmentForm;
import com.xvr.serviceBook.repository.DepartmentRepository;
import com.xvr.serviceBook.service.impl.DepartmentServiceImpl;
import com.xvr.serviceBook.service.servicedto.DepartmentServiceDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

import javax.swing.text.html.parser.Entity;
import java.util.List;
import java.util.Optional;

public interface DepartmentService {

    Page<Department> findAllDepartments(Pageable pageable);
    List<Department> findAllDepartmentsList();

    Optional<Department> findDepartmentById(Long id);


    Optional<Department> findDepartmentByName(String name);

    List<Department> findPaginated(Pageable pageable);

    void saveDepartment(DepartmentServiceDto departmentServiceDto);

    void deleteDepartmentById(Long id);

    void updateDepartment(DepartmentServiceDto departmentServiceDto);
}
