package com.example.gestionflotte.controllers;

import com.example.gestionflotte.controllers.common.CrudWithFK;
import com.example.gestionflotte.models.Odometer;
import com.example.gestionflotte.models.plane.PlaneEntity;
import com.example.gestionflotte.services.OdometerService;
import com.example.gestionflotte.services.PlaneService;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/planes/{fkId}/odometers")
@CrossOrigin(methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class OdometerController extends CrudWithFK<PlaneEntity, PlaneService, Odometer, OdometerService> {

    public OdometerController(OdometerService service, PlaneService fkService) {
        super(service, fkService);
    }

}
