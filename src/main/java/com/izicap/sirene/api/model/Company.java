package com.izicap.sirene.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString
@EqualsAndHashCode
@JsonRootName("etablissement")
public class Company {

    private Long id;

    @JsonProperty("siret")
    private String siret;

    @JsonProperty("nic")
    private String nic;

    @JsonProperty("geo_adresse")
    private String geoAdresse;

    @JsonProperty("date_creation")
    private String creationDate;

    @JsonProperty("nom_raison_sociale")
    private String fullName;

}
