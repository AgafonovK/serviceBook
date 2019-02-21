package com.xvr.serviceBook.repository;

import com.xvr.serviceBook.entity.Department;
import com.xvr.serviceBook.entity.Equipment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EquipmentRepository extends JpaRepository <Equipment, Long> {

    Equipment findEquipmentByName(String name);


}
