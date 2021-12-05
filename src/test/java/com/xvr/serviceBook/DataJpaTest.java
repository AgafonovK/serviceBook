package com.xvr.serviceBook;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.xvr.serviceBook.controller.restapi.assemblers.DepartmentPaginationModelAssembler;
import com.xvr.serviceBook.controller.restapi.dtorepresentation.DepartmentModelRepresentation;
import com.xvr.serviceBook.controller.restapi.dtorepresentation.StatusTicketModelRepresentation;
import com.xvr.serviceBook.controller.restapi.dtorepresentation.TicketModelRepresentation;
import com.xvr.serviceBook.entity.AppUser;
import com.xvr.serviceBook.entity.Department;
import com.xvr.serviceBook.repository.AppRoleRepository;
import com.xvr.serviceBook.repository.TicketRepository;
import com.xvr.serviceBook.repository.WorkerRepository;
import com.xvr.serviceBook.service.DepartmentService;
import com.xvr.serviceBook.service.impl.AppUserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Commit;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
@Commit
public class DataJpaTest {

    @Autowired
    private TicketRepository ticketRepository;
    @Autowired
    private WorkerRepository workerRepository;
    @Autowired
    private AppRoleRepository appRoleRepository;
    @Autowired
    private DepartmentPaginationModelAssembler departmentPaginationModelAssembler;
    @Autowired
    private DepartmentService departmentService;
    @Autowired
    private ModelMapper modelMapper;

    @BeforeEach
    public void booksShouldBeAdded() {
        /*AppRole appRoleAdmin = AppRole.builder()
                .appRoleId(1L)
                .roleName("ADMIN")
                .build();
        AppRole appRoleUser = AppRole.builder()
                .appRoleId(2L)
                .roleName("USER")
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
        Assertions.assertEquals(1, appUserRepository.count());
        Assertions.assertEquals(2, appRoleRepository.count());*/
    }

    @Test
    @DisplayName("проверка связи таблиц")
    public void checkAppRolesFromAppUser() {
        /*List<AppUser> appRoleList = appUserService.findAllAppUsers();
        for (AppUser appRole : appRoleList) {
            System.out.println(appRole.toString());
        }*/
    }

    @Test
    public void checkModel() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
        String jsonPerson = mapper.writerWithDefaultPrettyPrinter()
                .writeValueAsString(DepartmentModelRepresentation.builder()
                        .id(3L)
                        .name("depart2")
                        .departmentTickets(Set.of(TicketModelRepresentation.builder()
                                        .id(3L)
                                        .endDateTicket(LocalDate.now().plusDays(1))
                                        .startDateTicket(LocalDate.now().minusDays(1))
                                        .ticketDescription("errrorr")
                                        .statusTicket(StatusTicketModelRepresentation.builder()
                                                .id(3L)
                                                .statusName("FIXED")
                                                .statusTickets(null)
                                                .build()
                                        )
                                        .clientDepartmentRepresentation(null)
                                        .build(),
                                TicketModelRepresentation.builder()
                                        .id(4L)
                                        .endDateTicket(LocalDate.now().plusDays(1))
                                        .startDateTicket(LocalDate.now().minusDays(1))
                                        .ticketDescription("errrorr")
                                        .statusTicket(StatusTicketModelRepresentation.builder()
                                                .id(4L)
                                                .statusName("FIXED")
                                                .statusTickets(null)
                                                .build()
                                        )
                                        .clientDepartmentRepresentation(null)
                                        .build())));
        System.out.println(jsonPerson);

    }

}
