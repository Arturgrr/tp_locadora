package br.ufop.trabalho.IOConsole;

import java.util.ArrayList;
import java.util.Scanner;

import br.ufop.trabalho.Util;
import br.ufop.trabalho.controle.Controle;
import br.ufop.trabalho.entities.Data;
import br.ufop.trabalho.entities.Movimentacao;

/**
 * @author João Teixeira
 */

public class MenuBalanceteConsole {

    private final Controle controle;
    private final Scanner input;

    public MenuBalanceteConsole(Controle controle, Scanner input) {
        this.controle = controle;
        this.input = input;
    }

    public void exibeMenuBalancete() {
        boolean continua = true;
        do {
            System.out.println("""
                    Digite a opcao:\

                    \t1 - Cadastrar Entrada\

                    \t2 - Cadastrar Saida\

                    \t3 - Buscar Movimentacao\

                    \t4 - Balancete por Mes\

                    \t5 - Balancete por Ano\

                    \t0 - Voltar
                    """);
            int op = Util.leInteiroConsole(input);
            ArrayList<Movimentacao> movimentacoesEncontradas;
            ArrayList<Movimentacao> balancetePorMesEscolhido;
            ArrayList<Movimentacao> balancetePorAnoEscolhido;
            switch (op) {
                case 1 -> cadastrarEntrada();
                case 2 -> cadastrarSaida();
                case 3 ->  {
                System.out.println("Digite o nome para buscar alguma movimentacao:");
                String nomeMovimentacao = input.nextLine();
                movimentacoesEncontradas = controle.buscarMovimentacoes(nomeMovimentacao);
                }
                case 4 -> {
                    System.out.println("Digite o numero do Mes (1 a 12) para exibir Balancete:");
                    int mesBusca = input.nextInt();
                    balancetePorMesEscolhido = controle.buscarMovimentacoesPorMes (mesBusca);
                }
                case 5 -> {
                    System.out.println("Digite o ano para exibir Balancete:");
                    int anoBusca = input.nextInt();
                    balancetePorAnoEscolhido = controle.buscarMovimentacoesPorAno (anoBusca);

                }
                case 0 -> continua = false;
                default -> System.out.println("Opcao Invalida!");
            }
        } while (continua);
    }

    /**
     * Método para adicionar entrada
     * 
     * @author João Teixeira
     */

    public void cadastrarEntrada() {
        input.nextLine();
        System.out.println("Digite o nome da Entrada:");
        String nomeDaEntrada = input.nextLine();

        System.out.println("Digite a descricao da Entrada:");
        String descricaoEntrada = input.nextLine();

        System.out.println("Digite o valor da Entrada:");
        Double valor = input.nextDouble();

        System.out.println("Digite a data da entrada no padrão dd/mm/aaaa:");
        Data dataDaEntrada = Util.lerDataValida(input);
       

        controle.cadastrarEntrada(nomeDaEntrada, descricaoEntrada, valor, dataDaEntrada);

    }

    public void cadastrarSaida() {
        input.nextLine();
        System.out.println("Digite o nome da Saida:");
        String nomeDaEntrada = input.nextLine();

        System.out.println("Digite a descricao da Saida:");
        String descricaoEntrada = input.nextLine();

        System.out.println("Digite o valor da Saida:");
        Double valor = input.nextDouble();

        System.out.println("Digite a data da saida no padrão dd/mm/aaaa:");
        Data dataDaEntrada = Util.lerDataValida(input);
        input.nextLine();

        controle.cadastrarEntrada(nomeDaEntrada, descricaoEntrada, valor, dataDaEntrada);

    }

   

	
    




}