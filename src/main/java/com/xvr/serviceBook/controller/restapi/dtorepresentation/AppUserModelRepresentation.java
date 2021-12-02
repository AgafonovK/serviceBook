package com.xvr.serviceBook.controller.restapi.dtorepresentation;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.xvr.serviceBook.entity.AppRole;
import com.xvr.serviceBook.entity.Worker;
import lombok.*;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.RepresentationModel;

import javax.persistence.FetchType;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotEmpty;
import java.util.HashSet;
import java.util.List;
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
