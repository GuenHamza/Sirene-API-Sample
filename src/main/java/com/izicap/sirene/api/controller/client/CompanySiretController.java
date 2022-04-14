package com.izicap.sirene.api.controller.client;

import com.izicap.sirene.api.model.Company;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.validation.constraints.*;


@Validated
public interface CompanySiretController {

    @GetMapping(value = "/api/companyInfo/{siret}")
    ResponseEntity<Company> getCompanyInfoBySiret(@PathVariable("siret") @NotNull @Pattern(regexp="^(\\d{14})$") String siret);
}
