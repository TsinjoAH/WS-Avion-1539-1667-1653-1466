package com.example.gestionflotte.controllers;

import com.example.gestionflotte.controllers.common.CrudController;
import com.example.gestionflotte.exception.CustomValidation;
import com.example.gestionflotte.models.plane.PlaneEntity;
import com.example.gestionflotte.models.plane.PlaneInfo;
import com.example.gestionflotte.models.receiver.ImgReceiver;
import com.example.gestionflotte.responses.Success;
import com.example.gestionflotte.services.PlaneService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/planes")
@CrossOrigin(methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class PlaneController extends CrudController<PlaneEntity, PlaneService> {

    public PlaneController(PlaneService service) {
        super(service);
    }

    @GetMapping("/expired/{month}")
    public ResponseEntity<Success> getExpireAbout(@PathVariable int month) throws CustomValidation {
        return CrudController.returnSuccess(service.expiredIn(month), HttpStatus.OK);
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<Success> findById(@PathVariable("id") Long id) {
        return returnSuccess(this.service.findPlaneInfoById(id), HttpStatus.OK);
    }

    @Override
    @GetMapping
    public ResponseEntity<Success> findAll() {
        return returnSuccess(this.service.findAllFullInfo(), HttpStatus.OK);
    }


    @PutMapping("/img/{id}")
    public ResponseEntity<Success> update(@PathVariable("id") Long id, @RequestBody ImgReceiver receiver) throws CustomValidation {
        PlaneEntity p = this.service.findById(id);
        p.setImg(receiver.getByte());
        this.service.update(p);
        return returnSuccess(this.service.findPlaneInfoById(id), HttpStatus.OK);
    }

}
