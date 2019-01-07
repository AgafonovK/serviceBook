package com.xvr.serviceBook.service;

import com.xvr.serviceBook.entity.Worker;
import com.xvr.serviceBook.form.WorkerForm;

import java.util.List;

public interface WorkerService {


    List<Worker> getAllWorker();
    Long getMaxUserId();
    Worker createWorker(WorkerForm worker);

}
