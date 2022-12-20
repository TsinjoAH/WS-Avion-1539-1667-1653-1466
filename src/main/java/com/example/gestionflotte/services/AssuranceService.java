package com.example.gestionflotte.services;

import com.example.gestionflotte.exception.CustomValidation;
import com.example.gestionflotte.models.Assurance;
import com.example.gestionflotte.models.plane.PlaneEntity;
import com.example.gestionflotte.repos.AssuranceRepo;
import com.example.gestionflotte.services.common.CrudServiceWithFK;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class AssuranceService extends CrudServiceWithFK<Assurance, PlaneEntity, AssuranceRepo> {
    public AssuranceService(AssuranceRepo repo) {
        super(repo);
    }

    @Override
    public Assurance create(Assurance obj) throws CustomValidation {
        return super.create(obj);
    }

    @Override
    public List<Assurance> findForFK(PlaneEntity planeEntity) {
        return this.repo.findByPlaneId(planeEntity.getId());
    }
    public List<Assurance> getExpiredAboutXMonth(int x) {
        return this.repo.expiredAboutXMonth(x);
    }
}
