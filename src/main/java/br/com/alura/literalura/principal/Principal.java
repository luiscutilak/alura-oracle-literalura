package br.com.alura.literalura.principal;

import br.com.alura.literalura.model.DadosLivro;
import br.com.alura.literalura.service.ConsumoApi;
import br.com.alura.literalura.service.ConverteDados;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Principal {
    private Scanner sc = new Scanner(System.in);
    private ConsumoApi csm = new ConsumoApi();
    private ConverteDados cnd = new ConverteDados();

    private final String ENDERECO = "https://gutendex.com/books?search=";
    private List<DadosLivro> dadosLivros = new ArrayList<>();
}
