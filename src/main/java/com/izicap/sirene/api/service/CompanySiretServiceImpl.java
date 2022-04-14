package com.izicap.sirene.api.service;

import com.izicap.sirene.api.business.client.CompanySiretBusiness;
import com.izicap.sirene.api.model.Company;
import com.izicap.sirene.api.service.client.CompanySiretService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CompanySiretServiceImpl implements CompanySiretService {

    @Autowired
    private CompanySiretBusiness companySiretBusiness;

    @Override
    public ResponseEntity<Company> getCompanyInfoBySiret(String siret) {
        return companySiretBusiness.getCompanyInfoBySiret(siret);
    }
}
