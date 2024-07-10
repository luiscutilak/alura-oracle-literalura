package br.com.alura.literalura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


    @JsonIgnoreProperties(ignoreUnknown = true)
    public record DadosAutor(
            @JsonAlias("name") String nomeAutor,
            @JsonAlias("birth_year") Integer anoNascimentoAutor,
            @JsonAlias("death_year") Integer anoFalecimentoAutor
    ) {
    }

