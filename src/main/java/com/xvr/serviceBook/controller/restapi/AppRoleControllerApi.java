package com.xvr.serviceBook.controller.restapi;

import com.xvr.serviceBook.controller.restapi.dtorepresentation.AppRoleModelRepresentation;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/rest/approles")
public class AppRoleControllerApi {

    @GetMapping(value = "/{id}")
    public ResponseEntity<AppRoleModelRepresentation> getRoleById(@PathVariable Long id){

        return ResponseEntity.ok(AppRoleModelRepresentation.builder().build());
    }
}
