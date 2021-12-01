package com.xvr.serviceBook.form;

import com.xvr.serviceBook.entity.AppRole;
import com.xvr.serviceBook.entity.Worker;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotEmpty;
import java.util.Set;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
@EqualsAndHashCode
@Builder
public class AppUserForm {

    private Long userId;

    private String userName;

    private boolean enabled;

    private Set<AppRole> appRole;

    private Worker worker;

}
