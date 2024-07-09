package br.com.alura.literalura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

public class DadosAutor {
    @JsonIgnoreProperties(ignoreUnknown = true)
    public record DatosAutor(
            @JsonAlias("name") String nomeAutor,
            @JsonAlias("birth_year") Integer anoNascimentoAutor,
            @JsonAlias("death_year") Integer anoFalecimentoAutor
    ) {
    }
}
