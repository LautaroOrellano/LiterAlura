package com.literAlura.LiterAlura.model.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DatosPersona(

        @JsonAlias("birth_year")
        Integer fechaNacimiento,

        @JsonAlias("death_year")
        Integer fechaFallecimiento,

        @JsonAlias("name")
        String nombre) {

}
