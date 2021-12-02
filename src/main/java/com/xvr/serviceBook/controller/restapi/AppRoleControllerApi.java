package com.xvr.serviceBook.controller.restapi;

import com.xvr.serviceBook.controller.restapi.assemblers.AppRolePaginationModelAssembler;
import com.xvr.serviceBook.controller.restapi.dtorepresentation.AppRoleModelRepresentation;
import com.xvr.serviceBook.entity.AppRole;
import com.xvr.serviceBook.service.AppRoleService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/rest/approles")
public class AppRoleControllerApi {

    private final AppRoleService appRoleService;
    private final PagedResourcesAssembler<AppRole> appRolePagedResourcesAssembler;
    private final AppRolePaginationModelAssembler appRolePaginationModelAssembler;
    public AppRoleControllerApi(AppRoleService appRoleService, PagedResourcesAssembler<AppRole> appRolePagedResourcesAssembler,
                                AppRolePaginationModelAssembler appRolePaginationModelAssembler) {
        this.appRoleService = appRoleService;
        this.appRolePagedResourcesAssembler = appRolePagedResourcesAssembler;
        this.appRolePaginationModelAssembler = appRolePaginationModelAssembler;
    }

    @GetMapping
    public ResponseEntity<PagedModel<AppRoleModelRepresentation>> getRoles(@PageableDefault(size = 5) Pageable pageable) {
        Page<AppRole> appRoles = appRoleService.findAll(pageable);
        PagedModel<AppRoleModelRepresentation> pagedModel = appRolePagedResourcesAssembler.toModel(appRoles, appRolePaginationModelAssembler);
        return pagedModel.getContent().isEmpty()
                ? ResponseEntity.notFound().build()
                : ResponseEntity.ok(pagedModel);

    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<AppRoleModelRepresentation> getRoleById(@PathVariable Long id) {

        return ResponseEntity.ok(AppRoleModelRepresentation.builder().build());
    }
}
