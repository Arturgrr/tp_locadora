package br.ufop.trabalho.IOConsole;

import java.util.Scanner;

import br.ufop.trabalho.Util;
import br.ufop.trabalho.controle.Constantes;
import br.ufop.trabalho.controle.Controle;
import br.ufop.trabalho.entities.Data;

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
                    
                    \t10 - Voltar
                    """);
            int op = Util.leInteiroConsole(input);
            switch (op) {
                case 1 -> leDadosCliente();
                case 2 -> System.out.println("Falta implementar!");
                case 5 -> imprimeListaClientes();
                case 10 -> {
                    return;
                }
                default -> System.out.println("Opcao Invalida!");
            }
        } while(continua);
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
