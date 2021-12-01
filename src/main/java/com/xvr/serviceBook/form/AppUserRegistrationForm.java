package com.xvr.serviceBook.form;

import com.xvr.serviceBook.entity.AppRole;
import lombok.*;
import org.hibernate.validator.constraints.UniqueElements;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
@EqualsAndHashCode
@Builder
public class AppUserRegistrationForm {

    @NotEmpty
    private String userName;

    @NotEmpty
    @Size(min = 6, max = 15)
    private String password;

    @NotEmpty
    @Size(min = 6, max = 15)
    private String confirmPassword;

    private boolean enabled;

    private Set<AppRole> appRole;

    public void addAppRole(AppRole appRole){
        this.appRole.add(appRole);
    }

    public void deleteAppRole(AppRole appRole){
        this.appRole.remove(appRole);
    }

}
