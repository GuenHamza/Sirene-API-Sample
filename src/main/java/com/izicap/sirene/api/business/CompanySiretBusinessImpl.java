package com.izicap.sirene.api.business;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;
import com.izicap.sirene.api.business.client.CompanySiretBusiness;
import com.izicap.sirene.api.common.exception.IzicapInternalErrorException;
import com.izicap.sirene.api.common.exception.IzicapNotFoundException;

import com.izicap.sirene.api.model.Company;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@Slf4j
@RequiredArgsConstructor
public class CompanySiretBusinessImpl implements CompanySiretBusiness {

    @Value("${izicap.sirene.api.endpoint}")
    private String sireneApiEndPoint;

    @Override
    public ResponseEntity<Company> getCompanyInfoBySiret(String siret) {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        Request request = new Request.Builder()
                .url(new StringBuilder(sireneApiEndPoint).append(siret).toString())
                .method("GET", null)
                .build();
        Response response;
        Company company;
        try {
            response = client.newCall(request).execute();
            if (!response.isSuccessful() && response.code() == 404) {
                throw new IzicapNotFoundException("Company Not Found");
            }
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            objectMapper.configure(DeserializationFeature.UNWRAP_ROOT_VALUE, true);
            company = objectMapper.readValue(response.body().string(), Company.class);
        } catch (UnrecognizedPropertyException e) {
            log.error("Unrecognized field in response from Sirene API {},", e.getMessage());
            throw new IzicapInternalErrorException("Error During parsing response from Sirene API - Please contact the API Provider : {} ", e.getCause());
        } catch (JsonMappingException e) {
            log.error("Error During parsing response from Sirene API {},", e.getMessage());
            throw new IzicapInternalErrorException("Error During parsing response from Sirene API - Please contact the API Provider ", e.getCause());
        } catch (JsonProcessingException e) {
            log.error("Error During processing response from Sirene API : {} ", e.getMessage());
            throw new IzicapInternalErrorException("Error During parsing response from Sirene API - Please contact the API Provider ", e.getCause());
        } catch (IOException e) {
            log.error("Unreachable Sirene API, {} ", e.getMessage());
            throw new IzicapInternalErrorException("Unreachable Sirene API", e.getCause());
        }
        return ResponseEntity.ok(company);
    }
}
