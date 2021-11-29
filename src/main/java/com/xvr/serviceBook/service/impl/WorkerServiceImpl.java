package com.xvr.serviceBook.service.impl;

import com.xvr.serviceBook.entity.Worker;
import com.xvr.serviceBook.form.WorkerForm;
import com.xvr.serviceBook.repository.DepartmentRepository;
import com.xvr.serviceBook.repository.WorkerRepository;
import com.xvr.serviceBook.service.WorkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkerServiceImpl implements WorkerService {

    private final WorkerRepository workerRepository;

    @Autowired
    public WorkerServiceImpl(WorkerRepository workerRepository, DepartmentRepository departmentRepository) {
        this.workerRepository = workerRepository;
    }

    @Override
    public Long getMaxUserId() {
        return (long) workerRepository.findAll().size();
    }


    @Override
    public Worker createWorker(WorkerForm form) {
        Long id = getMaxUserId();
        Worker worker = new Worker(id+1,form.getPositionWorker(),form.getFirstName(),form.getLastName(),form.getPatronymic(),form.getPhone(),
                form.getEmail(),form.getDateAccept(), form.getDateFired(),form.getDepartment());
        return workerRepository.saveAndFlush(worker);
    }

    @Override
    public List<Worker> findAllWorker() {
        return workerRepository.findAll();
    }

}
