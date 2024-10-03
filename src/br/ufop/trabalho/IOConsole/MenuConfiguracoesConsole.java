package br.ufop.trabalho.IOConsole;

import br.ufop.trabalho.Util;
import br.ufop.trabalho.controle.Controle;

import java.util.Scanner;

public class MenuConfiguracoesConsole {
    private final Controle controle;
    private final Scanner input;

    public MenuConfiguracoesConsole(Controle controle, Scanner input) {
        this.controle = controle;
        this.input = input;
    }

    public void exibeMenuConfiguracoes() {
        boolean continua = true;
        do {
            System.out.println("""
                Configurações:
                \t1 - Definir valor da multa
                \t2 - Definir quantidade máxima de filmes por cliente
                \t3 - Definir prazo de devolução em dias
                \t4 - Definir valor de locação para Lançamentos
                \t5 - Definir valor de locação para Novos
                \t6 - Definir valor de locação para Antigos
                \t0 - Voltar
                """);
            int opcaoEscolhida = Util.leInteiroConsole(input);
            switch (opcaoEscolhida) {
                case 1 -> definirValorMulta();
                case 2 -> definirQuantMaxFilmes();
                case 3 -> definirPrazoDevolucao();
                case 4 -> definirValorLocacaoLancamento();
                case 5 -> definirValorLocacaoNovo();
                case 6 -> definirValorLocacaoAntigo();
                case 0 -> continua = false;
                default -> System.out.println("Opção inválida");
            }
        } while (continua);
    }

    private void definirValorLocacaoLancamento() {
        System.out.println("Digite o novo valor de locação para filmes Lançamento:");
        double novoValor = Util.leDoubleConsole(input);
        controle.VALOR_LOCACAO_LANCAMENTO = novoValor;
        System.out.println("Valor de locação para filmes Lançamento atualizado para: R$ " + novoValor);
    }

    private void definirValorLocacaoNovo() {
        System.out.println("Digite o novo valor de locação para filmes Novos:");
        double novoValor = Util.leDoubleConsole(input);
        controle.VALOR_LOCACAO_NOVO = novoValor;
        System.out.println("Valor de locação para filmes Novos atualizado para: R$ " + novoValor);
    }

    private void definirValorLocacaoAntigo() {
        System.out.println("Digite o novo valor de locação para filmes Antigos:");
        double novoValor = Util.leDoubleConsole(input);
        controle.VALOR_LOCACAO_ANTIGO = novoValor;
        System.out.println("Valor de locação para filmes Antigos atualizado para: R$ " + novoValor);
    }


    private void definirValorMulta() {
        System.out.println("Digite o novo valor da multa por dia de atraso:");
        double novoValorMulta = Util.leDoubleConsole(input);
        controle.VALOR_MULTA_DIA = novoValorMulta;
        System.out.println("Valor da multa atualizado para: " + novoValorMulta);
    }

    private void definirQuantMaxFilmes() {
        System.out.println("Digite a nova quantidade máxima de filmes por cliente:");
        int novaQuantMax = Util.leInteiroConsole(input);
        controle.QUANT_MAX_FILME_CLIENTE = novaQuantMax;
        System.out.println("Quantidade máxima de filmes por cliente atualizada para: " + novaQuantMax);
    }

    private void definirPrazoDevolucao() {
        System.out.println("Digite o novo prazo de devolução em dias:");
        int novoPrazo = Util.leInteiroConsole(input);
        controle.PRAZO_DEVOLUCAO = novoPrazo;
        System.out.println("Prazo de devolução atualizado para: " + novoPrazo + " dias");
    }
}
