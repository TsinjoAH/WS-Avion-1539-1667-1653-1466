package com.example.gestionflotte.controllers;

import com.example.gestionflotte.controllers.common.CrudWithFK;
import com.example.gestionflotte.models.Assurance;
import com.example.gestionflotte.models.plane.PlaneEntity;
import com.example.gestionflotte.services.AssuranceService;
import com.example.gestionflotte.services.PlaneService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/assurances/{fkId}")
@CrossOrigin(methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class AssuranceController extends CrudWithFK<PlaneEntity, PlaneService, Assurance, AssuranceService> {

    protected final AssuranceService service;

    public AssuranceController(AssuranceService service, PlaneService fkService) {
        super(service, fkService);
        this.service = service;
    }

}
