package br.com.alura.literalura.model;

import jakarta.persistence.*;

import java.util.List;
@Entity
@Table(name = "autores", schema = "public")
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String nomeAutor;
    private Integer anoNascimentoAutor;
    private Integer anoFalecimentoAutor;
    @OneToMany(mappedBy = "autor", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
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
        livros.forEach(l -> l.setAutor(this));
        this.livros = livros;
    }

    @Override
    public String toString() {
        return "Autor" + nomeAutor +
                "id= " + id +
                ", nomeAutor= '" + nomeAutor + '\'' +
                ", anoNascimentoAutor= " + anoNascimentoAutor +
                ", anoFalecimentoAutor= " + anoFalecimentoAutor +
                ", livros= " + livros;

    }
}
