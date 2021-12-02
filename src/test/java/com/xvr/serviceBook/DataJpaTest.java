package com.xvr.serviceBook;

import com.xvr.serviceBook.controller.restapi.assemblers.DepartmentPaginationModelAssembler;
import com.xvr.serviceBook.controller.restapi.dtorepresentation.DepartmentModelRepresentation;
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

import java.util.List;

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
    private AppUserServiceImpl appUserService;
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
            List<AppUser> appRoleList = appUserService.findAllAppUsers();
            for (AppUser appRole : appRoleList){
                System.out.println(appRole.toString());
            }
        }

        @Test
    public void checkModel(){
            Department department = departmentService.findDepartmentById(2L).get();
            System.out.println(department.getTickets());
            System.out.println(modelMapper.map(department,DepartmentModelRepresentation.class));
        DepartmentModelRepresentation departmentModelRepresentation = departmentPaginationModelAssembler.toModel(department);
            System.out.println();

        }
}
