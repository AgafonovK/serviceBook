package com.xvr.serviceBook.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
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

    public Report(Long reportId, String reportName, LocalDate startDateReport, LocalDate endDateReport, String descriptionReport, Equipment equipment, Worker worker) {
        this.reportId = reportId;
        this.reportName = reportName;
        this.startDateReport = startDateReport;
        this.endDateReport = endDateReport;
        this.descriptionReport = descriptionReport;
        this.equipment = equipment;
        this.worker = worker;
    }

}
