package com.izicap.sirene.api.service.client;

import com.izicap.sirene.api.model.Company;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

public interface CompanySiretService {
    ResponseEntity<Company> getCompanyInfoBySiret(@PathVariable("siret") String siret);
}
