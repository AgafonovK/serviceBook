package com.xvr.serviceBook.service;

import com.xvr.serviceBook.entity.Department;
import com.xvr.serviceBook.repository.DepartmentRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface DepartmentService {

    List<Department> getAllDepartment();

    Department findDepartmentByName(String name);

    List<Department> findPaginated(Pageable pageable);

}
