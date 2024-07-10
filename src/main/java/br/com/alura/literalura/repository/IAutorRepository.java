package br.com.alura.literalura.repository;

import br.com.alura.literalura.model.Autor;
import br.com.alura.literalura.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface IAutorRepository extends JpaRepository<Autor, Long> {
    @Query("SELECT a FROM Livro l JOIN l.autor a WHERE a.nomeAutor LIKE %:nome%")
    Optional<Autor> buscarAutorPorNome(String nome);
    @Query("SELECT l FROM Livro l JOIN l.autor a WHERE l.titulo LIKE %:nome%")
    Optional<Livro> buscarLivroPorNome(String nome);
}
