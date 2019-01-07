package com.xvr.serviceBook.service;

import com.xvr.serviceBook.entity.Department;
import com.xvr.serviceBook.repository.DepartmentRepository;

import java.util.List;

public interface DepartmentService {

    List<Department> getAllDepartment();

    Department findDepartmentByName(String name);
}
