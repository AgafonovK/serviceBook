package com.xvr.serviceBook.repository;

import com.xvr.serviceBook.entity.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReportRepository extends JpaRepository <Report, Long> {
}
