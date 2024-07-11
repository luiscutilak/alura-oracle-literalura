package br.com.alura.literalura.repository;

import br.com.alura.literalura.model.Autor;
import br.com.alura.literalura.model.Linguagem;
import br.com.alura.literalura.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface IAutorRepository extends JpaRepository<Autor, Long> {
    @Query("SELECT a FROM Livro l JOIN l.autor a WHERE a.nomeAutor LIKE %:nome%")
    Optional<Autor> buscarAutorPorNome(String nome);
    @Query("SELECT l FROM Livro l JOIN l.autor a WHERE l.titulo LIKE %:nome%")
    Optional<Livro> buscarLivroPorNome(String nome);

    @Query("SELECT l FROM Autor a JOIN a.livros l")
    List<Livro> buscarTodosLivrosRegistrados();

    @Query("SELECT a FROM Autor a WHERE a.anoFalecimentoAutor > :data")
    List<Autor> buscarAutoresVivos(Integer data);
    @Query("SELECT l FROM Autor a JOIN a.livros l WHERE l.linguagem = :idioma ")
    List<Livro> buscarLivrosPorIdioma(Linguagem idioma);

    @Query("SELECT l FROM Autor a JOIN a.livros l ORDER BY l.numeroDownloads DESC LIMIT 10")
    List<Livro> listarTop10Livros();

    @Query("SELECT a FROM Autor a WHERE a.anoNascimentoAutor = :data")
    List<Autor> buscarAutoresPorNascimento(Integer data);
    @Query("SELECT a FROM Autor a WHERE a.anoFalecimentoAutor = :data")
    List<Autor> buscarAutoresPorFalecimento(Integer data);
}
