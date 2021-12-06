package com.xvr.serviceBook.controller.restapi.dtorepresentation;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

import javax.validation.constraints.NotEmpty;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AppUserModelRepresentation extends RepresentationModel<AppUserModelRepresentation> {

    @JsonProperty("App User Id")
    private Long id;

    @NotEmpty
    private String userName;

    private boolean enabled;

    @JsonProperty("User Role")
    private Set<AppRoleModelRepresentation> appRole;

    /* TODO
    @OneToOne(fetch = FetchType.LAZY)
    private Worker worker;*/

}
