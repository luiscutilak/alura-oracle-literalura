package br.com.alura.literalura.model;

import jakarta.persistence.*;

import java.util.stream.Collectors;

@Entity
@Table(name = "Livros")
public class Livro {
    private Long id;
    @Column(unique = true)
    private String titulo;
    @ManyToOne
    private String autor;
    @Enumerated(EnumType.STRING)
    private Linguagem linguagem;
    private Double numeroDownloads;

    public Livro(){
    }

    public Livro(DadosLivro livro) {
        this.id = livro.id();
        this.titulo = livro.titulo();
        this.linguagem = Linguagem.fromString(livro.idioma().stream()
                .limit(1).collect(Collectors.joining()));
        this.numeroDownloads = livro.numeroDownloads();


    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    public Linguagem getLinguagem() {
        return linguagem;
    }

    public void setLinguagem(Linguagem linguagem) {
        this.linguagem = linguagem;
    }
    public Double getNumeroDownloads() {
        return numeroDownloads;
    }

    public void setNumeroDownloads(Double numeroDownloads) {
        this.numeroDownloads = numeroDownloads;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    @Override
    public String toString() {
        return
                "id = " + id +
                        ", Titulo = " + titulo + '\'' +
                        ", Idioma = " + linguagem + '\'' +
                        ", Numero de Downloads = " + numeroDownloads +
                        ", Autor = " + autor + '\'';

    }
}
