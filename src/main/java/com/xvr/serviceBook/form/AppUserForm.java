package com.xvr.serviceBook.form;

import lombok.*;
import org.hibernate.validator.constraints.UniqueElements;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

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
    @Size(min = 6, max = 15)
    private String password;

    @NotEmpty
    @Size(min = 6, max = 15)
    private String confirmPassword;

    private boolean enabled;


}
