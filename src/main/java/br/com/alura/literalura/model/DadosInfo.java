package br.com.alura.literalura.model;

import com.fasterxml.jackson.annotation.JsonAlias;

import java.util.List;

public record DadosInfo(
        @JsonAlias("count") Integer total,
        @JsonAlias("results")List<DadosLivro> livros)
 {
}
