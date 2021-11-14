package com.xvr.serviceBook.form;

import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
@ToString
@EqualsAndHashCode
@Builder
public class AppUserForm {

    private Long userId;

    @NotEmpty
    private String userName;

    @NotEmpty
    @Min(6)
    private String password;

    private boolean enabled;


}
