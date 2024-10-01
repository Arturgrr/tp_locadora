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
        } while(continua);
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
                clientesEncontrados = controle.buscarClientePorNome(nome); //feito não testado
            }
            case 2 ->{
                System.out.println("Digite o codigo do cliente que deseja buscar"); 
                String nome = input.nextLine();
                clientesEncontrados = controle.buscarClientePorCodigo(nome); //feito não testado
            }
            case 3 -> {
                System.out.println("Digite o nome do dependente que deseja buscar");
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

}
