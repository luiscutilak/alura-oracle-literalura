package br.com.alura.literalura.model;

import jakarta.persistence.*;

import java.util.stream.Collectors;

@Entity
@Table(name = "livros")
public class Livro {
    @Id
    private Long id;
    @Column(unique = true)
    private String titulo;
    @ManyToOne
    private Autor autor;
    @Enumerated(EnumType.STRING)
    private Linguagem linguagem;
    private Integer numeroDownloads;

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
    public Integer getNumeroDownloads() {
        return numeroDownloads;
    }

    public void setNumeroDownloads(Integer numeroDownloads) {
        this.numeroDownloads = numeroDownloads;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
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
