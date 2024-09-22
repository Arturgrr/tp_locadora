package br.ufop.trabalho.IOConsole;

import br.ufop.trabalho.Util;
import br.ufop.trabalho.controle.Constantes;
import br.ufop.trabalho.controle.Controle;
import br.ufop.trabalho.entities.Data;
import br.ufop.trabalho.entities.Filme;

import java.util.ArrayList;
import java.util.Scanner;

public class MenuFilmeConsole {
    private final Controle controle;
    private final Scanner input;

    public MenuFilmeConsole(Controle controle, Scanner input) {
        this.controle = controle;
        this.input = input;
    }

    public void exibeMenuFilmes() {
        boolean continua = true;
        do {
            System.out.println("""
                    Digite a opcao:
                    
                    \t1 - Cadastrar filme
                    \t2 - Buscar filme
                    \t10 - Voltar
                    """);
            int opcaoEscolhida = Util.leInteiroConsole(input);
            switch (opcaoEscolhida) {
                case 1 -> cadastrarFilme();
                case 2 -> buscarFilme();
                case 10 -> {
                    return;
                }
                default -> System.out.println("Opcao invalida");
            }
        } while (continua);
    }

    private void buscarFilme() {
        System.out.println("""
                Selecione o tipo de busca:
                
                \t1 - Buscar por nome
                \t2 - Buscar por genero
                \t3 - Buscar por disponibilidade
                \t4 - Voltar
                """);
        int opcaoEscolhida = Util.leInteiroConsole(input);
        ArrayList<Filme> filmesEncontrados = null;
        switch (opcaoEscolhida) {
            case 1 -> {
                System.out.println("Digite o nome do filme que deseja buscar:");
                String nome = input.nextLine();
                filmesEncontrados = controle.buscarFilmesPorNome(nome);
            }
            case 2 -> {
                System.out.println("Digite o nome do genero:");
                String genero = input.nextLine();
                filmesEncontrados = controle.buscarFilmesPorGenero(genero);
            }
            case 3 -> filmesEncontrados = controle.buscarFilmesDisponiveis();
            default -> System.out.println("Opcao invalida");
        }

        if (filmesEncontrados != null && !filmesEncontrados.isEmpty()) {
            System.out.println("Filmes encontrados:");
            for (int i = 0; i < filmesEncontrados.size(); i++) {
                System.out.println((i + 1) + " - " + filmesEncontrados.get(i).toString());
            }
        } else {
            System.out.println("Nenhum filme encontrado.");
        }

    }

    private void cadastrarFilme() {
        input.nextLine();
        System.out.println("Digite o nome do filme: ");
        String nome = input.nextLine();

        System.out.println("Digite o genero do filme: ");
        String genero = input.nextLine();

        System.out.println("Digite o ano de lancamento do filme");
        Data anoDeLancamento = Util.lerDataValida(input);

        System.out.println("Digite a quantidade de dvd: ");
        int quantidadeDeDvd = Util.leInteiroConsole(input);

        System.out.println("Digite a quantidade de blueray: ");
        int quantidadeDeBluRay = Util.leInteiroConsole(input);

        int retorno = controle.addFilme(nome, anoDeLancamento, genero, quantidadeDeDvd, quantidadeDeBluRay);
        String msg = switch (retorno){
            case Constantes.RESULT_OK -> "Sucesso ao cadastrar filme";
            case Constantes.ERRO_CAMPO_VAZIO -> "Todos os campos devem estar preenchidos";
            default -> "";
        };
        System.out.println(msg);
    }
}
