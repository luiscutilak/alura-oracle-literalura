package br.com.alura.literalura.principal;

import br.com.alura.literalura.model.Autor;
import br.com.alura.literalura.model.DadosInfo;
import br.com.alura.literalura.model.DadosLivro;
import br.com.alura.literalura.model.Livro;
import br.com.alura.literalura.repository.IAutorRepository;
import br.com.alura.literalura.service.ConsumoApi;
import br.com.alura.literalura.service.ConverteDados;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Principal {
    private Scanner sc = new Scanner(System.in);
    private ConsumoApi csm = new ConsumoApi();
    private ConverteDados cnv = new ConverteDados();

    private final String ENDERECO = "https://gutendex.com/books/";
    private IAutorRepository repositorio;

    public Principal(IAutorRepository repositorio){
        this.repositorio = repositorio;
    }

    public void exibeMenu() {

        var opcao = -1;

        while (opcao != 0) {
            var menu = """
                    ------------------------------------------------
                    Digite a opção desejada:
                    
                    1 - Buscar livro por título
                    2 - Listar livros registrados
                    3 - Listar autores registrados
                    4 - Listar autores vivos em um determinado ano
                    5 - Listar livros em um determinado idioma
                    6 - Listar estatisticas
                    7 - Listar Top 10 Livros
                    8 - Buscar por nome do Autor
                    9 - Buscar por autores com outras consultas
                    0 - Sair...
                    
                    ------------------------------------------------
                    
                    """;

            while (opcao != 0) {
                System.out.println(menu);

                try {
                    opcao = sc.nextInt();
                    sc.nextLine();

                    switch (opcao) {
                        case 1:
                            buscarLivroWeb();
                            break;
                        default:
                            System.out.println("Opção inválida\n");
                    }
            } catch (NumberFormatException e) {
                    System.out.println("Opção inválida: " + e.getMessage());
                }


            }

        }
    }

    public void buscarLivroWeb(){
        System.out.println("Digite o nome do livro para busca:");
        var nomeLivro = sc.nextLine();
        var json = csm.obterDados(ENDERECO + "?search=" + nomeLivro.replace(" ","+"));
        var dados = cnv.obterDados(json, DadosInfo.class);
        Optional<DadosLivro> livroBuscado = dados.livros().stream()
                .findFirst();
        if(livroBuscado.isPresent()){
            System.out.println(
                            "---------------- LIVRO -----------" +
                            "\nTitulo: " + livroBuscado.get().titulo() +
                            "\nAutor: " + livroBuscado.get().autores().stream()
                            .map(a -> a.nomeAutor()).limit(1).collect(Collectors.joining())+
                            "\nIdioma: " + livroBuscado.get().idioma().stream().collect(Collectors.joining()) +
                            "\nNumero de descargas: " + livroBuscado.get().numeroDownloads() +
                            "-----------------------------------"
            );

            try{
                List<Livro> livroEncontrado = livroBuscado.stream().map(a -> new Livro(a)).collect(Collectors.toList());
                Autor apiAutor = livroBuscado.stream().
                        flatMap(l -> l.autores().stream()
                                .map(a -> new Autor(a)))
                        .collect(Collectors.toList()).stream().findFirst().get();
                Optional<Autor> autorBD = repositorio.buscarAutorPorNome(livroBuscado.get().autores().stream()
                        .map(a -> a.nomeAutor())
                        .collect(Collectors.joining()));
                Optional<Livro> libroOptional = repositorio.buscarLivroPorNome(nomeLivro);
                if (libroOptional.isPresent()) {
                    System.out.println("Livro salvo no banco de dados com Sucesso!");
                } else {
                    Autor autor;
                    if (autorBD.isPresent()) {
                        autor = autorBD.get();
                        System.out.println("Autor Salvo no banco de dados com Sucesso!");
                    } else {
                        autor = apiAutor;
                        repositorio.save(autor);
                    }
                    autor.setLivros(livroEncontrado);
                    repositorio.save(autor);
                }
            } catch(Exception e) {
                System.out.println("Atenção! " + e.getMessage());
            }
        } else {
            System.out.println("Livro não encontrado!");
        }
    }
}
