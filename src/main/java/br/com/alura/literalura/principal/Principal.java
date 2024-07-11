package br.com.alura.literalura.principal;

import br.com.alura.literalura.model.*;
import br.com.alura.literalura.repository.IAutorRepository;
import br.com.alura.literalura.service.ConsumoApi;
import br.com.alura.literalura.service.ConverteDados;

import java.util.IntSummaryStatistics;
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
                        case 2:
                            buscarLivrosRegistrados();
                            break;
                        case 3:
                            buscarAutoresRegistrados();
                            break;
                        case 4:
                            buscarAutoresVivosPorData();
                            break;
                        case 5:
                            buscarLivrosPorIdioma();
                            break;
                        case 6:
                            listarEstatisticas();
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

    private void listarEstatisticas() {
        var json = csm.obterDados(ENDERECO);
        var dados = cnv.obterDados(json, DadosInfo.class);
        IntSummaryStatistics est = dados.livros().stream()
                .filter(l -> l.numeroDownloads() > 0)
                .collect(Collectors.summarizingInt(DadosLivro::numeroDownloads));
        Integer media = (int) est.getAverage();
        System.out.println("\n----- ESTATISTICAS -----");
        System.out.println("Media de downloads: " + media);
        System.out.println("Maior número de downloads: " + est.getMax());
        System.out.println("Menor número de downloads: " + est.getMin());
        System.out.println("Registros para calculo das estatisticas: " + est.getCount());
        System.out.println("-----------------\n");
    }

    private void buscarLivrosPorIdioma() {
        var menu = """
                Digite uma das opções abaixo para pesquisar seu Livro por Idioma:
                
                pt - Português
                en - Inglês
                es - Espanhol
                fr - Francês
                """;
        System.out.println(menu);
        var idioma = sc.nextLine();
        if(idioma.equalsIgnoreCase("pt") || idioma.equalsIgnoreCase("en") ||
                idioma.equalsIgnoreCase("es") || idioma.equalsIgnoreCase("fr")){
            Linguagem linguagem = Linguagem.fromString(idioma);
            List<Livro> livros = repositorio.buscarLivrosPorIdioma(linguagem);
            if(livros.isEmpty()){
                System.out.println("Não disponibilizamos livros nesse idioma.");
            } else{
                System.out.println();
                livros.forEach(l -> System.out.println(
                        "----- LIVRO -----" +
                                "\nTitulo: " + l.getTitulo() +
                                "\nAutor: " + l.getAutor().getNomeAutor() +
                                "\nIdioma: " + l.getLinguagem().getIdioma() +
                                "\nNumero de downloads: " + l.getNumeroDownloads() +
                                "\n-----------------\n"
                ));
            }
        } else{
            System.out.println("Digite um idioma válido!");
        }
    }

    private void buscarAutoresVivosPorData() {
        System.out.println("Digite o ano de nascimento do autor que deseja Pesquisar:");
        try{
            var data = Integer.valueOf(sc.nextLine());
            List<Autor> autores = repositorio.buscarAutoresVivos(data);
            if(!autores.isEmpty()){
                System.out.println();
                autores.forEach(a -> System.out.println(
                        "Autor: " + a.getNomeAutor() +
                                "\nData de nascimento: " + a.getAnoNascimentoAutor() +
                                "\nData de falecimento: " + a.getAnoFalecimentoAutor() +
                                "\nLivros: " + a.getLivros().stream()
                                .map(l -> l.getTitulo()).collect(Collectors.toList()) + "\n"
                ));
            } else{
                System.out.println("Não existe autores vivos com esta data em nosso banco de dados!");
            }
        } catch(NumberFormatException e){
            System.out.println("Digite um ano válido! " + e.getMessage());
        }
    }

    private void buscarAutoresRegistrados() {
        List<Autor> autores = repositorio.findAll();
        System.out.println();
        autores.forEach(l-> System.out.println(
                "Autor: " + l.getNomeAutor() +
                        "\nAno de nascimento: " + l.getAnoNascimentoAutor() +
                        "\nAno de falecimento: " + l.getAnoFalecimentoAutor() +
                        "\nLivros: " + l.getLivros().stream()
                        .map(t -> t.getTitulo()).collect(Collectors.toList()) + "\n"
        ));
    }

    private void buscarLivrosRegistrados() {
        List<Livro> livros = repositorio.buscarTodosLivrosRegistrados();
        livros.forEach(l -> System.out.println(
                "----- LIVRO -----" +
                        "\nTitulo: " + l.getTitulo() +
                        "\nAutor: " + l.getAutor().getNomeAutor() +
                        "\nIdioma: " + l.getLinguagem().getIdioma() +
                        "\nNumero de downloads: " + l.getNumeroDownloads() +
                        "\n-----------------\n"
        ));
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
                            "\n---------------- LIVRO -----------" +
                            "\nTitulo: " + livroBuscado.get().titulo() +
                            "\nAutor: " + livroBuscado.get().autores().stream()
                            .map(a -> a.nomeAutor()).limit(1).collect(Collectors.joining())+
                            "\nIdioma: " + livroBuscado.get().idioma().stream().collect(Collectors.joining()) +
                            "\nNumero de downloads: " + livroBuscado.get().numeroDownloads() +
                            "\n-----------------------------------\n"
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
                Optional<Livro> livroOptional = repositorio.buscarLivroPorNome(nomeLivro);
                if (livroOptional.isPresent()) {
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
