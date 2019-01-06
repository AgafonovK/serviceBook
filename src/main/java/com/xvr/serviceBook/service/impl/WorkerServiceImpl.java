package com.xvr.serviceBook.service.impl;

import com.xvr.serviceBook.entity.Worker;
import com.xvr.serviceBook.repository.WorkerRepository;
import com.xvr.serviceBook.service.WorkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class WorkerServiceImpl implements WorkerService {

    @Autowired
    WorkerRepository workerRepository;
    private static final Map<Long, Worker> USERS_MAP = new HashMap<>();


    @Override
    public List<Worker> getAllWorker() {

        List<Worker> list = new ArrayList<>();
        list.addAll(USERS_MAP.values());
        return workerRepository.findAll();

    }


}
