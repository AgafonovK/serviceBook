package com.xvr.serviceBook.form;

import com.xvr.serviceBook.entity.Equipment;
import com.xvr.serviceBook.entity.Worker;

import java.time.LocalDate;

public class ReportForm {

    private Long reportId;

    private String reportName;

    private LocalDate startDateReport;

    private LocalDate endDateReport;

    private String descriptionReport;

    private Equipment equipment;

    private Worker worker;

    public ReportForm() {
    }

    public ReportForm(Long reportId, String reportName, LocalDate startDateReport, LocalDate endDateReport, String descriptionReport, Equipment equipment, Worker worker) {
        this.reportId = reportId;
        this.reportName = reportName;
        this.startDateReport = startDateReport;
        this.endDateReport = endDateReport;
        this.descriptionReport = descriptionReport;
        this.equipment = equipment;
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
}
