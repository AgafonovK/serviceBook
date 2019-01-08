package com.xvr.serviceBook.service.impl;

import com.xvr.serviceBook.entity.Report;
import com.xvr.serviceBook.repository.ReportRepository;
import com.xvr.serviceBook.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportServiceImpl implements ReportService {

    @Autowired
    ReportRepository reportRepository;

    List<Report> findAllReportByEquipmentName(String equipmentName){
        List<Report> list = reportRepository.findReportByEquipmentName(equipmentName);
        System.out.println(" SIZE " + list.size()+ " = " + list);
        return list;
    }

}
