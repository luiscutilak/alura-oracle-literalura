package br.com.alura.literalura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosLivro(@JsonAlias("title") String titulo,
                         @JsonAlias("authors") String autor,
                         @JsonAlias("languages") String idioma,
                         @JsonAlias("download_count") Double numeroDownloads,
                         @JsonAlias("birth_year") Integer anoNascimentoAutor,
                         @JsonAlias("death_year") Integer anoFalecimentoAutor
) {
}
