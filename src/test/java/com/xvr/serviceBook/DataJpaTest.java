package com.xvr.serviceBook;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.xvr.serviceBook.controller.restapi.assemblers.DepartmentPaginationModelAssembler;
import com.xvr.serviceBook.controller.restapi.dtorepresentation.*;
import com.xvr.serviceBook.entity.AppRole;
import com.xvr.serviceBook.entity.AppUser;
import com.xvr.serviceBook.entity.Department;
import com.xvr.serviceBook.repository.AppRoleRepository;
import com.xvr.serviceBook.repository.AppUserRepository;
import com.xvr.serviceBook.repository.TicketRepository;
import com.xvr.serviceBook.repository.WorkerRepository;
import com.xvr.serviceBook.service.AppUserService;
import com.xvr.serviceBook.service.DepartmentService;
import com.xvr.serviceBook.service.impl.AppUserServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@SpringBootTest
public class DataJpaTest {

    @Autowired
    private TicketRepository ticketRepository;
    @Autowired
    private WorkerRepository workerRepository;
    @Autowired
    private AppRoleRepository appRoleRepository;
    @Autowired
    private AppUserRepository appUserRepository;
    @Autowired
    private DepartmentPaginationModelAssembler departmentPaginationModelAssembler;
    @Autowired
    private DepartmentService departmentService;
    @Autowired
    private ModelMapper modelMapper;

    @BeforeEach
    public void entityShouldBeAdded() {
        AppRole appRoleAdmin = AppRole.builder()
                .appRoleId(3L)
                .roleName("ADMINPLUS")
                .build();
        AppRole appRoleUser = AppRole.builder()
                .appRoleId(4L)
                .roleName("USERPLUS")
                .build();
        appRoleRepository.save(appRoleAdmin);
        appRoleRepository.save(appRoleUser);
        Set<AppRole> appRoleSet = Set.of(appRoleAdmin, appRoleUser);
        AppUser appUser = AppUser.builder()
                .userName("dbadmin")
                .encryptedPassword("qwerty")
                .enabled(1)
                .appRole(appRoleSet)
                .build();
        appUserRepository.save(appUser);
        Assertions.assertEquals(3, appUserRepository.count());
        Assertions.assertEquals(4, appRoleRepository.count());
    }

    @Test
    @DisplayName("проверка связи таблиц")
    public void checkAppRolesFromAppUser() {
        List<AppUser> appRoleList = appUserRepository.findAll();
        for (AppUser appRole : appRoleList) {
            System.out.println(appRole.toString());
        }
    }

    @Test
    public void checkModel() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);

        AppRoleModelRepresentation appRoleModelRepresentation = new AppRoleModelRepresentation();
        appRoleModelRepresentation.setAppRoleId(1L);
        appRoleModelRepresentation.setRoleName("Adm");
        appRoleModelRepresentation.setAppUsers(null);

        AppRoleModelRepresentation appRoleModelRepresentation2 = new AppRoleModelRepresentation();
        appRoleModelRepresentation2.setAppRoleId(2L);
        appRoleModelRepresentation2.setRoleName("Use");
        appRoleModelRepresentation2.setAppUsers(null);

        AppUserModelRepresentation appUserModelRepresentation = new AppUserModelRepresentation();
        appUserModelRepresentation.setId(1L);
        appUserModelRepresentation.setUserName("nick");
        appUserModelRepresentation.setEnabled(true);
        appUserModelRepresentation.setAppRole(Set.of(appRoleModelRepresentation, appRoleModelRepresentation2));

        String jsonAppUser = mapper.writeValueAsString(appUserModelRepresentation);
        System.out.println(jsonAppUser);
    }

}
