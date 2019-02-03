package com.xvr.serviceBook.repository;

import com.xvr.serviceBook.entity.Equipment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EquipmentRepository extends JpaRepository <Equipment, Long> {


}