package com.xvr.serviceBook.service;

import com.xvr.serviceBook.entity.Worker;
import com.xvr.serviceBook.form.WorkerForm;
import com.xvr.serviceBook.service.servicedto.WorkerServiceDto;

import java.util.List;

public interface WorkerService {


    List<Worker> findAllWorker();
    Long getMaxUserId();
    Worker createWorker(WorkerServiceDto workerServiceDto);

}
