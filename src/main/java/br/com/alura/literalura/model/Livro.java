package br.com.alura.literalura.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Livros")
public class Livro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String titulo;
    private String autor;
    private String idioma;
    private Double numeroDownloads;
    private Integer anoNascimentoAutor;
    private Integer anoFalecimentoAutor;

    public Long getId() {
        return id;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public Integer getAnoNascimentoAutor() {
        return anoNascimentoAutor;
    }

    public void setAnoNascimentoAutor(Integer anoNascimentoAutor) {
        this.anoNascimentoAutor = anoNascimentoAutor;
    }

    public Integer getAnoFalecimentoAutor() {
        return anoFalecimentoAutor;
    }

    public void setAnoFalecimentoAutor(Integer anoFalecimentoAutor) {
        this.anoFalecimentoAutor = anoFalecimentoAutor;
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

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public Double getNumeroDownloads() {
        return numeroDownloads;
    }

    public void setNumeroDownloads(Double numeroDownloads) {
        this.numeroDownloads = numeroDownloads;
    }

    @Override
    public String toString() {
        return
                "-------- LIVRO -------- " +
                "id = " + id +
                ", Titulo = " + titulo + '\'' +
                ", Autor = " + autor + '\'' +
                ", Idioma = " + idioma + '\'' +
                ", Numero de Downloads = " + numeroDownloads +
                ", Ano de Nascimento Autor = " + anoNascimentoAutor +
                ", Ano de Falecimento Autor=" + anoFalecimentoAutor +
                "-------------------------";
    }
}
