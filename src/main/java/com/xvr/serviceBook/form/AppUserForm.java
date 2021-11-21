package com.xvr.serviceBook.form;

import lombok.*;
import org.hibernate.validator.constraints.UniqueElements;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
@EqualsAndHashCode
@Builder
public class AppUserForm {

    @NotEmpty
    private String userName;

    @NotEmpty
    //@Min(6)
    private String password;

    @NotEmpty
    private String confirmPassword;

    private boolean enabled;


}
