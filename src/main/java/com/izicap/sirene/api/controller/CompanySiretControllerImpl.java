package com.izicap.sirene.api.controller;

import com.izicap.sirene.api.controller.client.CompanySiretController;
import com.izicap.sirene.api.model.Company;
import com.izicap.sirene.api.service.client.CompanySiretService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
@Validated
public class CompanySiretControllerImpl implements CompanySiretController {

    @Autowired
    private CompanySiretService companySiretService;

    @Override
    public ResponseEntity<Company> getCompanyInfoBySiret(String siret) {
        return companySiretService.getCompanyInfoBySiret(siret);
    }
}
