package br.ufop.trabalho.IOConsole;

import java.util.Scanner;

import br.ufop.trabalho.Util;
import br.ufop.trabalho.controle.Constantes;
import br.ufop.trabalho.controle.Controle;
import br.ufop.trabalho.entities.Data;

public class MenuClienteConsole {
    private Controle controle;
    private Scanner input;

    public MenuClienteConsole(Controle controle, Scanner input) {
        this.controle = controle;
        this.input = input;
    }

    public void exibeMenuClientes() {
        boolean continua = true;
        int op = 0;
        do {
            System.out.println("""
                    Digite a opcao:\
                    
                    \t1 - Cadastrar Cliente\
                    
                    \t2 - Buscar clientes\
                    
                    \t5 - imprime Lista de Clientes\
                    
                    \t10 - Voltar
                    """);
            op = Util.leInteiroConsole(input);
            switch (op) {
                case 1:
                    leDadosCliente();
                    break;
                case 2:
                    System.out.println("Falta implementar!");
                    break;
                case 5:
                    imprimeListaClientes();
                    break;
                case 10:
                    return;
                default:
                    System.out.println("Op��o Inv�lida!");
            }
        } while(continua);
    }

    private void leDadosCliente() {
        input.nextLine();
        String nome, end, cpf;
        Data dataDN;
        int codigo;
        System.out.println("Digite o nome do cliente");
        nome = input.nextLine();
        System.out.println("Digite o endereco do cliente");
        end = input.nextLine();
        System.out.println("Digite o cpf do cliente");
        cpf = Util.lerCpfValido(input);
        System.out.println("Digite a data de nascimento do cliente no padrão dd/mm/aaaa");
        dataDN = Util.lerDataValida(input);
        System.out.println("Digite o codigo do cliente");
        codigo = Util.leInteiroConsole(input);
        input.nextLine();
        int retorno = controle.addCliente(nome, end, cpf, dataDN, codigo);
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
