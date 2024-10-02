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
                    Digite a opcao:

                    \t1 - Cadastrar entrada
                    \t2 - Cadastrar saida
                    \t3 - Buscar uma movimentacao por nome
                    \t4 - Balancete por mes
                    \t5 - Balancete por ano
                    \t0 - Voltar
                    """);
            int op = Util.leInteiroConsole(input);
            switch (op) {
                case 1 -> cadastrarEntrada();
                case 2 -> cadastrarSaida();
                case 3 -> buscarMovimentacao();
                case 4 -> balancetePorMes();
                case 5 -> balancetePorAno();
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
        input.nextLine(); // Consome o '\n' pendente
        System.out.println("Digite o nome da Entrada:");
        String nomeDaEntrada = input.nextLine();

        System.out.println("Digite a descricao da Entrada:");
        String descricaoEntrada = input.nextLine();

        System.out.println("Digite o valor da Entrada:");
        Double valor = input.nextDouble();

        System.out.println("Digite a data da entrada no padrão dd/mm/aaaa:");
        Data dataDaEntrada = Util.lerDataValida(input);

        controle.cadastrarEntrada(nomeDaEntrada, descricaoEntrada, valor, dataDaEntrada);
        System.out.println("Entrada adicionada com sucesso!!");
    }

    /**
     * Método para adicionar saída
     * 
     * @author João Teixeira
     */
    public void cadastrarSaida() {
        input.nextLine(); 
        System.out.println("Digite o nome da Saida:");
        String nomeDaSaida = input.nextLine();

        System.out.println("Digite a descricao da Saida:");
        String descricaoSaida = input.nextLine();

        System.out.println("Digite o valor da Saida:");
        Double valor = input.nextDouble();

        System.out.println("Digite a data da saida no padrão dd/mm/aaaa:");
        Data dataDaSaida = Util.lerDataValida(input);

        controle.cadastrarSaida(nomeDaSaida, descricaoSaida, valor, dataDaSaida);
        System.out.println("Saida adicionada com sucesso!!");
    }

    /**
     * Método para buscar movimentação por nome
     * 
     * @author João Teixeira
     */
    public void buscarMovimentacao() {
        input.nextLine();
        System.out.println("Digite o nome para buscar alguma movimentacao:");
        String nomeMovimentacao = input.nextLine();
        ArrayList<Movimentacao> movimentacoesEncontradas = controle.buscarMovimentacoes(nomeMovimentacao);
        int i = 1;
        for (Movimentacao elemento : movimentacoesEncontradas) {

            System.out.println("movimentacao:  " + i);

            System.out.println(elemento.toString());
            i++;
        }
        boolean continua2 = true;
        do {
            System.out.println("""
                    Voce deseja editar ou excluir alguma movimentacao encontrada?:

                    \t1 - Editar

                    \t2 - Excluir

                    \t0 - Voltar
                    """);
            int op2 = Util.leInteiroConsole(input);
            switch (op2) {
                case 1 -> {
                    System.out.println("Digite o numero da movimentacao que deseja editar:");
                    int indice = Util.leInteiroConsole(input);
                    if (indice < 1 || indice > movimentacoesEncontradas.size()) {
                        System.out.println("ERRO! Indice invalido");
                        break;
                    }
                    input.nextLine();
                    System.out.println("Digite o novo nome da movimentacao:");
                    String novoNome = input.nextLine();

                    System.out.println("Digite a nova descrição da movimentacao:");
                    String novaDescricao = input.nextLine();

                    System.out.println("Digite o novo valor da movimentacao:");
                    Double novoValor = input.nextDouble();
                    input.nextLine();

                    System.out.println("Digite a nova data da movimentação no formato dd/mm/aaaa:");
                    Data novaData = Util.lerDataValida(input);

                    int flag=controle.editarMovimentacao(movimentacoesEncontradas, indice, novoNome, novaDescricao, novoValor,
                            novaData);
                    if (flag==1){
                        System.out.println("Movimentacao editada com sucesso!");
                    }else{
                        System.out.println("ERRO! Movimentacao nao encontrada na lista original.");

                    }
                    
                }
                case 2 -> {
                    System.out.println("Digite o numero da movimentacao que deseja excluir:");
                    int indice = Util.leInteiroConsole(input);
                    if (indice < 1 || indice > movimentacoesEncontradas.size()) {
                        System.out.println("ERRO! Indice invalido");
                        break;
                    }

                    int flag = controle.excluirMovimentacao(movimentacoesEncontradas, indice);
                    if (flag==1){
                        System.out.println("Movimentacao excluida com sucesso!");
                    }else{
                        System.out.println("ERRO! Movimentação nao encontrada na lista original.");

                    }
                }
                case 0 -> continua2 = false;
                default -> System.out.println("Opcao Invalida!");
            }

        } while (continua2);
    }

    /**
     * Método para buscar balancete por mês
     * 
     * @author João Teixeira
     */
    public void balancetePorMes() {
        System.out.println("Digite o numero do Mes (dois digitos, exemplo: 01 a 12) para exibir Balancete:");
        int mesBusca = input.nextInt();
        input.nextLine(); 

        ArrayList<Movimentacao> balancetePorMesEscolhido = controle.buscarMovimentacoesPorMes(mesBusca);
        
        double saldoPeriodo=0.0;
        
        for (Movimentacao elemento : balancetePorMesEscolhido) {
          
            System.out.println(elemento.toString());
            if(elemento.getTipoDaMovimentacao()==1){ // verifica se é entrada e saida e soma/subtrai no saldo do periodo pesquisado
                saldoPeriodo += elemento.getValor();
            }else{
                saldoPeriodo -= elemento.getValor();
            }

        }
        System.out.println("Salda do periodo pesquisado: R$" + saldoPeriodo +" reais");
    }

    /**
     * Método para buscar balancete por ano
     * 
     * @author João Teixeira
     */
    public void balancetePorAno() {
        System.out.println("Digite o ano para exibir Balancete:");
        int anoBusca = input.nextInt();
        input.nextLine(); 

        ArrayList<Movimentacao> balancetePorAnoEscolhido = controle.buscarMovimentacoesPorAno(anoBusca);

        double saldoPeriodo=0.0;

        for (Movimentacao elemento : balancetePorAnoEscolhido) {
            System.out.println(elemento.toString());
            if(elemento.getTipoDaMovimentacao()==1){ // verifica se é entrada e saida e soma/subtrai no saldo do periodo pesquisado
                saldoPeriodo += elemento.getValor();
            }else{
                saldoPeriodo -= elemento.getValor();
            }
        }
        System.out.println("Salda do periodo pesquisado: R$" + saldoPeriodo +" reais");
    }
}
