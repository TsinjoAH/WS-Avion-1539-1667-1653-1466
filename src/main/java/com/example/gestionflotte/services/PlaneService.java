package com.example.gestionflotte.services;

import com.example.gestionflotte.exception.CustomValidation;
import com.example.gestionflotte.models.plane.PlaneEntity;
import com.example.gestionflotte.models.plane.PlaneInfo;
import com.example.gestionflotte.repos.OdometerRepo;
import com.example.gestionflotte.repos.PlaneInfoRepo;
import com.example.gestionflotte.repos.PlaneRepo;
import com.example.gestionflotte.services.common.CrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class PlaneService extends CrudService<PlaneEntity, PlaneRepo> {


    private OdometerRepo odoRepo;
    @Autowired
    private PlaneInfoRepo infoRepo;

    public PlaneService(PlaneRepo repo, OdometerRepo odoRepo) {
        super(repo);
        this.odoRepo = odoRepo;
    }

    public List<PlaneEntity> expiredIn(int months) {
        return this.repo.findExpiredAboutXMonth(months);
    }

    public PlaneInfo findPlaneInfoById(Long id) {
        return infoRepo.findById(id).orElse(null);
    }

    public List<PlaneInfo> findAllFullInfo() {
        return infoRepo.findAll();
    }

    @Override
    public PlaneEntity create(PlaneEntity obj) throws CustomValidation {
        checkPlate(obj);
        return super.create(obj);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        this.odoRepo.deleteByPlaneId(id);
        super.delete(id);
    }

    @Override
    public PlaneEntity update(PlaneEntity plane) throws CustomValidation {
        if (plane.getId() == null)
            throw new CustomValidation("Plane id is null");
    //    checkPlate(plane);
        return super.update(plane);
    }


    private void checkPlate (PlaneEntity obj) throws CustomValidation {
        List<PlaneEntity> planes = this.repo.findByLicensePlate(obj.getLicensePlate());
        if (planes.size() != 0)
            throw new CustomValidation("License plate already exists");
    }


}
