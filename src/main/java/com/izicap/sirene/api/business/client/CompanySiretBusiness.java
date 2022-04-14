package com.izicap.sirene.api.business.client;

import com.izicap.sirene.api.model.Company;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import java.io.IOException;

public interface CompanySiretBusiness {

    ResponseEntity<Company> getCompanyInfoBySiret(@PathVariable("siret") String siret);

}
