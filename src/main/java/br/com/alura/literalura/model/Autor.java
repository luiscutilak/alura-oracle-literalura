package br.com.alura.literalura.model;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.List;

public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String nomeAutor;
    private Integer anoNascimentoAutor;
    private Integer anoFalecimentoAutor;
    private List<Livro> livros;

    public Autor(){
    }

    public Autor(DadosAutor dadosAutor){
        this.nomeAutor = dadosAutor.nomeAutor();
        this.anoNascimentoAutor = dadosAutor.anoNascimentoAutor();
        this.anoFalecimentoAutor = dadosAutor.anoNascimentoAutor();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeAutor() {
        return nomeAutor;
    }

    public void setNomeAutor(String nomeAutor) {
        this.nomeAutor = nomeAutor;
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

    public List<Livro> getLivros() {
        return livros;
    }

    public void setLivros(List<Livro> livros) {
        livros.forEach(1 -> 1.setA); = livros;
    }
}
