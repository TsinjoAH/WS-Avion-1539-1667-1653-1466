package com.example.gestionflotte.services;

import com.example.gestionflotte.exception.CustomValidation;
import com.example.gestionflotte.models.Odometer;
import com.example.gestionflotte.models.plane.PlaneEntity;
import com.example.gestionflotte.models.plane.PlaneInfo;
import com.example.gestionflotte.repos.OdometerRepo;
import com.example.gestionflotte.repos.PlaneInfoRepo;
import com.example.gestionflotte.services.common.CrudServiceWithFK;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OdometerService extends CrudServiceWithFK <Odometer, PlaneEntity, OdometerRepo> {

    protected final PlaneInfoRepo infoRepo;

    public OdometerService(OdometerRepo repo, PlaneInfoRepo infoRepo) {
        super(repo);
        this.infoRepo = infoRepo;
    }

    @Override
    public Odometer create(Odometer obj) throws CustomValidation {
        PlaneInfo info = this.infoRepo.findById(obj.getPlane().getId()).orElseThrow(() -> new CustomValidation("plane not found"));
        if (info.getCurrentKm() != null) {
            if (obj.getEnd() < info.getCurrentKm()) {
                throw new CustomValidation("End odometer must be greater than current odometer");
            }
        }
        if (obj.getStart() > obj.getEnd()) {
            throw new CustomValidation("End odometer must be greater than start odometer");
        }
        return super.create(obj);
    }

    @Override
    public List<Odometer> findForFK(PlaneEntity entity) {
        return this.repo.findByPlaneId(entity.getId());
    }

}
