package com.xvr.serviceBook.service;


import com.xvr.serviceBook.entity.Worker;
import com.xvr.serviceBook.repository.WorkerRepository;
import com.xvr.serviceBook.service.impl.WorkerServiceImpl;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface WorkerService {


    public List<Worker> getAllWorker();


}
