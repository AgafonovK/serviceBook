package com.xvr.serviceBook.controller.restapi.dtorepresentation;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.xvr.serviceBook.entity.AppUser;
import lombok.*;
import org.apache.catalina.LifecycleState;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.RepresentationModel;

import java.util.List;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AppRoleModelRepresentation extends RepresentationModel<AppRoleModelRepresentation> {

    private Long appRoleId;

    private String roleName;

    private Set<AppUserModelRepresentation> appUsers;
}
