package com.xvr.serviceBook.service.impl;

import com.xvr.serviceBook.entity.Department;
import com.xvr.serviceBook.repository.DepartmentRepository;
import com.xvr.serviceBook.service.DepartmentService;
import com.xvr.serviceBook.service.servicedto.DepartmentServiceDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;
    private final EntityManagerFactory entityManagerFactory;

    @Autowired
    public DepartmentServiceImpl(DepartmentRepository departmentRepository, EntityManagerFactory entityManagerFactory) {
        this.departmentRepository = departmentRepository;
        this.entityManagerFactory = entityManagerFactory;
    }

    @Override
    public Page<Department> findAllDepartments(Pageable pageable) {

        Page<Department> allDepartment = departmentRepository.findAll(pageable);

        return allDepartment;
    }

    @Override
    public List<Department> findAllDepartmentsList(){
        return departmentRepository.findAll();
    }

    @Override
    public Optional<Department> findDepartmentById(Long id) {
        return departmentRepository.findById(id);
    }

    @Override
    public Optional<Department> findFirstDepartmentByName(String name) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Optional<Department> dep = entityManager
                .createQuery("select dep from Department dep where dep.name =:name", Department.class)
                .setParameter("name", name)
                .getResultStream().findAny();

        return dep.flatMap(department -> departmentRepository.findById(department.getId()));
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

    @Override
    @Transactional
    public void saveDepartment(DepartmentServiceDto departmentServiceDto){
        Department department = new Department();
        department.setName(departmentServiceDto.getName());
        departmentRepository.save(department);
    }

    @Override
    @Transactional
    public void deleteDepartmentById(Long id) {
        departmentRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void updateDepartment(DepartmentServiceDto departmentServiceDto, Long id) {
        departmentRepository.findById(id).map(department -> {
            department.setName(departmentServiceDto.getName());
            return departmentRepository.save(department);
        });
    }
}
