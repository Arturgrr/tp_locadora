package br.ufop.trabalho.IOConsole;

import java.util.ArrayList;
import java.util.Scanner;

import br.ufop.trabalho.Util;
import br.ufop.trabalho.controle.Constantes;
import br.ufop.trabalho.controle.Controle;
import br.ufop.trabalho.entities.Cliente;
import br.ufop.trabalho.entities.Data;

// @author Iaggo Rauta
public class MenuClienteConsole {

    private final Controle controle;
    private final Scanner input;

    public MenuClienteConsole(Controle controle, Scanner input) {
        this.controle = controle;
        this.input = input;
    }

    public void exibeMenuClientes() {
        boolean continua = true;
        do {
            System.out.println("""
                    Digite a opcao:\

                    \t1 - Cadastrar Cliente\

                    \t2 - Buscar clientes\

                    \t5 - imprime Lista de Clientes\

                    \t0 - Voltar
                    """);
            int op = Util.leInteiroConsole(input);
            switch (op) {
                case 1 -> leDadosCliente();
                case 2 -> buscarCliente();
                case 5 -> imprimeListaClientes();
                case 0 -> continua = false;
                default -> System.out.println("Opcao Invalida!");
            }
        } while (continua);
    }

    // @author Iaggo Rauta
    private void buscarCliente() {
        System.out.println("""
                Selecione o tipo de busca:

                \t1 - Buscar pelo nome
                \t2 - Buscar pelo código
                \t3 - Buscar pelo nome do dependente
                \t0 - Voltar
                """);
        int op = Util.leInteiroConsole(input);
        ArrayList<Cliente> clientesEncontrados;
        switch (op) {
            case 1 -> {
                System.out.println("Digite o nome do cliente que deseja buscar");
                String nome = input.nextLine();
                clientesEncontrados = controle.buscarClientePorNome(nome); // feito não testado
            }
            case 2 -> {
                System.out.println("Digite o codigo do cliente que deseja buscar");
                int codigo = input.nextInt();
                clientesEncontrados = controle.buscarClientePorCodigo(codigo); // feito não testado
            }
            case 3 -> {
                System.out.println("Digite o nome do dependente que deseja buscar");
                input.nextLine();
                String nome = input.nextLine();
                clientesEncontrados = controle.buscarClientePeloNomeDoDepentende(nome);
            }
            case 0 -> System.out.println("Voltando...");
            default -> System.out.println("Opcao Invalida!");
        }
    }

    private void leDadosCliente() {
        input.nextLine();
        System.out.println("Digite o nome do cliente");
        String nome = input.nextLine();

        System.out.println("Digite o endereco do cliente");
        String endereco = input.nextLine();

        System.out.println("Digite o cpf do cliente");
        String cpf = Util.lerCpfValido(input);

        System.out.println("Digite a data de nascimento do cliente no padrão dd/mm/aaaa");
        Data dataDN = Util.lerDataValida(input);

        System.out.println("Digite o codigo do cliente");
        int codigo = Util.leInteiroConsole(input);

        input.nextLine();
        int retorno = controle.addCliente(nome, endereco, cpf, dataDN, codigo);
        String msg = switch (retorno) {
            case Constantes.ERRO_CAMPO_VAZIO -> "Todos os campos devem ser preenchidos!";
            case Constantes.RESULT_OK -> "Cliente cadastrado com sucesso!";
            default -> "";
        };
        System.out.println(msg);
    }

    private void imprimeListaClientes() {
        System.out.println("******** LISTA DE CLIENTES CADASTRADOS *********");
        for (int i = 0; i < controle.getQtdClientes(); i++) {
            System.out.println(controle.getClienteNaPosicao(i).toString());
        }
        System.out.println("******** FIM DA LISTA DE CLIENTES  *********");
    }

    public void locarfilme() {
        System.out.println("Digite o código do cliente que deseja locar um filme:");
        int codigoCliente = Util.leInteiroConsole(input);

        // Buscar cliente pelo código
        Cliente cliente = controle.buscarClientePorCodigo(codigoCliente);
        //começa
        if (escolhaCliente == 1) {
            // Exibir lista de cliente
            System.out.println("Selecione o Cliente:");
            for (int i = 0; i < cliente.getDependentes().size(); i++) {
                System.out.println((i + 1) + " - " + cliente.getDependentes().get(i).getNome());
            }
            int opcaoDependente = Util.leInteiroConsole(input) - 1;

            if (opcaoDependente >= 0 && opcaoDependente < cliente.getDependentes().size()) {
                dependente = controle.getClienteNaPosicao(opcaoDependente);
                System.out.println("Dependente selecionado: " + dependente.getNome());
            } else {
                System.out.println("Opção inválida. Voltando ao cliente principal.");
            }
        }

        //acaba
        if (cliente == null) {
            System.out.println("Cliente não encontrado.");
            return;
        }

        // Exibir lista de dependentes (se houver)
        Cliente dependente = null;
        if (cliente.temDependentes()) {
            System.out.println("Esse cliente possui dependentes. Deseja locar o filme em nome de algum dependente?");
            System.out.println("1 - Sim\n2 - Não, locar para o cliente principal.");
            int escolhaDependente = Util.leInteiroConsole(input);

            if (escolhaDependente == 1) {
                // Exibir lista de dependentes
                System.out.println("Selecione o dependente:");
                for (int i = 0; i < cliente.getDependentes().size(); i++) {
                    System.out.println((i + 1) + " - " + cliente.getDependentes().get(i).getNome());
                }
                int opcaoDependente = Util.leInteiroConsole(input) - 1;

                if (opcaoDependente >= 0 && opcaoDependente < cliente.getDependentes().size()) {
                    dependente = controle.getClienteNaPosicao(opcaoDependente);
                    System.out.println("Dependente selecionado: " + dependente.getNome());
                } else {
                    System.out.println("Opção inválida. Voltando ao cliente principal.");
                }
            }
        }

        // Solicitar o nome do filme
        System.out.println("Digite o nome do filme que deseja locar:");
        String nomeFilme = input.nextLine();

        // Verificar se o filme está disponível para locação
        if (!controle.filmeDisponivel(nomeFilme)) {
            System.out.println("Filme não disponível para locação.");
            return;
        }

        // Escolher o formato da mídia: DVD ou Blu-ray
        System.out.println("""
                Selecione o formato da mídia:

                \t1 - DVD
                \t2 - Blu-ray
                """);
        int opcaoMidia = Util.leInteiroConsole(input);
        String formatoMidia;
        switch (opcaoMidia) {
            case 1 -> formatoMidia = "DVD";
            case 2 -> formatoMidia = "Blu-ray";
            default -> {
                System.out.println("Opção inválida!");
                return;
            }
        }

        // Delegar a lógica da locação para a classe Controle
        boolean sucessoLocacao = controle.locarFilmeParaCliente(cliente, nomeFilme, formatoMidia, dependente);
        if (sucessoLocacao) {
            System.out.println("Filme " + nomeFilme + " locado com sucesso no formato " + formatoMidia + "!");
        } else {
            System.out.println("Não há " + formatoMidia + " disponível para o filme " + nomeFilme + ".");
        }
    }
}
