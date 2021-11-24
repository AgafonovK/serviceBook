package com.xvr.serviceBook.controller.restapi.dtorepresentation;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.xvr.serviceBook.entity.AppRole;
import com.xvr.serviceBook.entity.Worker;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.RepresentationModel;

import javax.persistence.FetchType;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotEmpty;
import java.util.HashSet;
import java.util.Set;

@Getter
@Builder
@EqualsAndHashCode(callSuper = false)
public class AppUserModelRepresentation extends RepresentationModel<AppUserModelRepresentation> {

    @NotEmpty
    private String userName;

    private boolean enabled;

    @JsonProperty("User Role")
    private CollectionModel<AppRoleModelRepresentation> appRole;

    /* TODO
    @OneToOne(fetch = FetchType.LAZY)
    private Worker worker;*/

}
