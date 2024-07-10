package br.com.alura.literalura.repository;

import br.com.alura.literalura.model.Autor;
import br.com.alura.literalura.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface IAutorRepository extends JpaRepository<Autor, Long> {
    @Query("SELECT a FROM Livro l JOIN l.autor a WHERE a.nomeAutor LIKE %:nomeAutor%")
    Optional<Autor> buscarAutorPorNome(String nomeAutor);
    @Query("SELECT l FROM Livro l JOIN l.autor a WHERE l.titulo LIKE %:nomeLivro%")
    Optional<Livro> buscarLivroPorNome(String nomeLivro);
}
