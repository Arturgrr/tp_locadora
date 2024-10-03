package br.ufop.trabalho.IOConsole;

import br.ufop.trabalho.Util;
import br.ufop.trabalho.controle.Controle;
import br.ufop.trabalho.entities.Cliente;
import br.ufop.trabalho.entities.Filme;

import java.util.ArrayList;
import java.util.Scanner;

public class MenuRelatorioConsole {

    private final Controle controle;
    private final Scanner input;

    
    public MenuRelatorioConsole(Controle controle, Scanner input) {
        this.controle = controle;
        this.input = input;
    }

    public void exibeMenuRelatorio() {
        boolean continua = true;
        while (continua) {
            System.out.println("""
                Escolha o relatório desejado:
                1. Relatório de Clientes
                2. Relatório de Filmes por Gênero
                3. Relatório de Filmes por Ano de Lançamento
                4. Relatório de Filmes por Nome (Ordem Alfabética)
                0. Voltar
                """);

            int opcao = Util.leInteiroConsole(input);

            switch (opcao) {
                case 1 -> exibirRelatorioClientes();
                case 2 -> {
                    System.out.print("Digite o gênero: ");
                    String genero = input.nextLine();
                    exibirRelatorioFilmesPorGenero(genero);
                }
                case 3 -> {
                    System.out.print("Digite o ano de lançamento: ");
                    int ano = Util.leInteiroConsole(input);
                    exibirRelatorioFilmesPorAno(ano);
                }
                case 4 -> exibirRelatorioFilmesPorNome();
                case 0 -> {
                    System.out.println("Voltando ao menu principal...");
                    continua = false;
                }
                default -> System.out.println("Opção inválida!");
            }
        }
    }

    private void exibirRelatorioClientes() {
        ArrayList<Cliente> clientes = controle.relatorioClientes();
        System.out.println("Relatório de Clientes:");
        for (Cliente cliente : clientes) {
            System.out.println(cliente.getNome() + " - " + cliente.getCodigo()); // Exibindo nome e código
        }
    }

    private void exibirRelatorioFilmesPorGenero(String genero) {
        ArrayList<Filme> filmes = controle.relatorioFilmesPorGenero(genero);
        System.out.println("Relatório de Filmes por Gênero: " + genero);
        for (Filme filme : filmes) {
            System.out.println(filme.getNome() + " - " + filme.getGenero()); // Exibindo nome e gênero
        }
    }

    private void exibirRelatorioFilmesPorAno(int ano) {
        ArrayList<Filme> filmes = controle.relatorioFilmesPorAno(ano);
        System.out.println("Relatório de Filmes de " + ano + ":");
        for (Filme filme : filmes) {
            System.out.println(filme.getNome() + " - " + filme.getAnoDeLancamento()); // Exibindo nome e data de
                                                                                      // lançamento
        }
    }

    private void exibirRelatorioFilmesPorNome() {
        ArrayList<Filme> filmes = controle.relatorioFilmesPorNome();
        System.out.println("Relatório de Filmes por Nome (Ordem Alfabética):");
        for (Filme filme : filmes) {
            System.out.println(filme.getNome() + " - " + filme.getGenero()); // Exibindo nome e gênero
        }
    }
}
