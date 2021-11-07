package com.xvr.serviceBook.service.impl;

import com.xvr.serviceBook.entity.Department;
import com.xvr.serviceBook.repository.DepartmentRepository;
import com.xvr.serviceBook.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;

    @Autowired
    public DepartmentServiceImpl(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    @Override
    public List<Department> getAllDepartment() {
        return departmentRepository.findAll();
    }

    @Override
    public Department findDepartmentByName(String name) {
        return departmentRepository.findDepartmentByName(name);
    }

    @Override
    public List<Department> findPaginated(Pageable pageable) {
        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();

        Page<Department> allDepartment = departmentRepository.findAll(pageable);
        if (allDepartment.hasContent()){
            return allDepartment.getContent();
        }else{
            return new ArrayList<Department>();
        }

    }
}
