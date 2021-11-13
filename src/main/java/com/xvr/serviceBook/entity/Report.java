package com.xvr.serviceBook.entity;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "report")
public class Report {

    @Id
    @Column(name = "id",nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reportId;

    @Column(name = "name")
    private String reportName;

    @Column(name = "start_date")
    private LocalDate startDateReport;

    @Column(name = "end_date")
    private LocalDate endDateReport;

    @Column(name = "description")
    private String descriptionReport;

    @ManyToOne(fetch = FetchType.LAZY)
    private Equipment equipment;

    @ManyToOne(fetch = FetchType.LAZY)
    private Worker worker;

    public Report() {
    }

    public Report(Long reportId, String reportName, LocalDate startDateReport, LocalDate endDateReport, String descriptionReport, Equipment equipment, Worker worker) {
        this.reportId = reportId;
        this.reportName = reportName;
        this.startDateReport = startDateReport;
        this.endDateReport = endDateReport;
        this.descriptionReport = descriptionReport;
        this.equipment = equipment;
        this.worker = worker;
    }


    public Equipment getEquipment() {
        return equipment;
    }

    public void setEquipment(Equipment equipment) {
        this.equipment = equipment;
    }

    public Worker getWorker() {
        return worker;
    }

    public void setWorker(Worker worker) {
        this.worker = worker;
    }

    public Long getReportId() {
        return reportId;
    }

    public void setReportId(Long reportId) {
        this.reportId = reportId;
    }

    public String getReportName() {
        return reportName;
    }

    public void setReportName(String reportName) {
        this.reportName = reportName;
    }

    public LocalDate getStartDateReport() {
        return startDateReport;
    }

    public void setStartDateReport(LocalDate startDateReport) {
        this.startDateReport = startDateReport;
    }

    public LocalDate getEndDateReport() {
        return endDateReport;
    }

    public void setEndDateReport(LocalDate endDateReport) {
        this.endDateReport = endDateReport;
    }

    public String getDescriptionReport() {
        return descriptionReport;
    }

    public void setDescriptionReport(String descriptionReport) {
        this.descriptionReport = descriptionReport;
    }
}
