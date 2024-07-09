package br.com.alura.literalura.principal;

import br.com.alura.literalura.model.DadosLivro;
import br.com.alura.literalura.model.Livro;
import br.com.alura.literalura.repository.LivroRepository;
import br.com.alura.literalura.service.ConsumoApi;
import br.com.alura.literalura.service.ConverteDados;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Principal {
    private Scanner sc = new Scanner(System.in);
    private ConsumoApi csm = new ConsumoApi();
    private ConverteDados cnv = new ConverteDados();

    private final String ENDERECO = "https://gutendex.com/books?search=";
    private List<DadosLivro> dadosLivros = new ArrayList<>();

    private LivroRepository repositorio;
    private List<Livro> livros = new ArrayList<>();

    private Optional<Livro> livroBusca;
    public Principal(LivroRepository repositorio) {
        this.repositorio = repositorio;
    }

    public void exibeMenu() {

        var opcao = -1;

        while (opcao != 0) {
            var menu = """
                    1 - Buscar livro por título
                    2 - Listar livros registrados
                    3 - Listar autores registrados
                    4 - Listar autores vivos em um determinado ano
                    5 - Listar livros em um determinado idioma
                    0 - Sair...
                    """;
            System.out.println(menu);
            opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {
                case 1:
                    buscarLivroWeb();
                    break;
                default:
                    System.out.println("Opção inválida\n");
            }
        }
    }

    private void buscarLivroWeb() {

        DadosLivro dados = getDadosLivro();
        Livro livro = new Livro(dados);
        repositorio.save(livro);
        System.out.println(dados);
    }

    private DadosLivro getDadosLivro() {

        System.out.println("Digite o nome do livro para busca:");

        var nomeLivro = sc.nextLine();
        var json = csm.obterDados(ENDERECO + nomeLivro.replace(" ", "%20"));
        DadosLivro dados = cnv.obterDados(json, DadosLivro.class);
        return dados;
    }
}
