package com.xvr.serviceBook.controller.restapi;

import com.xvr.serviceBook.controller.restapi.assemblers.AppUserPaginationModelAssembler;
import com.xvr.serviceBook.controller.restapi.dtorepresentation.AppUserModelRepresentation;
import com.xvr.serviceBook.entity.AppUser;
import com.xvr.serviceBook.service.impl.AppUserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/rest/users")
public class AppUserControllerApi {

    private final AppUserServiceImpl userDetailService;
    private final PagedResourcesAssembler<AppUser> appUserPagedResourcesAssembler;
    private final AppUserPaginationModelAssembler appUserPaginationModelAssembler;

    @Autowired
    public AppUserControllerApi(AppUserServiceImpl userDetailService,
                                PagedResourcesAssembler<AppUser> appUserPagedResourcesAssembler,
                                AppUserPaginationModelAssembler appUserPaginationModelAssembler) {
        this.userDetailService = userDetailService;
        this.appUserPagedResourcesAssembler = appUserPagedResourcesAssembler;
        this.appUserPaginationModelAssembler = appUserPaginationModelAssembler;
    }

    @GetMapping
    public ResponseEntity<PagedModel<AppUserModelRepresentation>> getUsers(@PageableDefault(page = 0, size = 5) Pageable pageable) {
        Page<AppUser> appUsers = userDetailService.findAllAppUsersPaginated(pageable);
        PagedModel<AppUserModelRepresentation> model = appUserPagedResourcesAssembler.toModel(appUsers, appUserPaginationModelAssembler);
        return !appUsers.isEmpty()
                ? ResponseEntity.ok(model)
                : ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<AppUserModelRepresentation> getUserById(@PathVariable Long id){

        return null;
    }
}
