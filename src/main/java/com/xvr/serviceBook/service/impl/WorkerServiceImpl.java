package com.xvr.serviceBook.service.impl;

import com.xvr.serviceBook.entity.Worker;
import com.xvr.serviceBook.repository.DepartmentRepository;
import com.xvr.serviceBook.repository.WorkerRepository;
import com.xvr.serviceBook.service.WorkerService;
import com.xvr.serviceBook.service.servicedto.WorkerServiceDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkerServiceImpl implements WorkerService {

    private final WorkerRepository workerRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public WorkerServiceImpl(WorkerRepository workerRepository, DepartmentRepository departmentRepository, ModelMapper modelMapper) {
        this.workerRepository = workerRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Long getMaxUserId() {
        return (long) workerRepository.findAll().size();
    }


    @Override
    public Worker createWorker(WorkerServiceDto workerServiceDto) {
        return workerRepository.save(modelMapper.map(workerServiceDto, Worker.class));
    }

    @Override
    public List<Worker> findAllWorker() {
        return workerRepository.findAll();
    }

}
