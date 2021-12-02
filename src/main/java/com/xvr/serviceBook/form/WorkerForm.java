package com.xvr.serviceBook.form;

import com.xvr.serviceBook.entity.Department;
import com.xvr.serviceBook.entity.PositionWorker;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WorkerForm {

    private Long id;

    private PositionWorker positionWorker;

    @NotEmpty
    private String firstName;
    @NotEmpty
    private String lastName;
    @NotEmpty
    private String patronymic;

    private Long phone;

    @Email
    private String email;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate dateAccept;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate dateFired;

    private Department department;

}
