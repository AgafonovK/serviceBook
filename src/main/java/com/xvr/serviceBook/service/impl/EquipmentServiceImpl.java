package com.xvr.serviceBook.service.impl;

import com.xvr.serviceBook.entity.Equipment;
import com.xvr.serviceBook.repository.EquipmentRepository;
import com.xvr.serviceBook.service.EquipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EquipmentServiceImpl implements EquipmentService {

    @Autowired
    EquipmentRepository equipmentRepository;

    @Override
    public List<Equipment> getAllEquipment() {
        return equipmentRepository.findAll();
    }
}
