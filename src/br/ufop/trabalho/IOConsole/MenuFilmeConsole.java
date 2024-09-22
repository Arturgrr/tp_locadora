package br.ufop.trabalho.IOConsole;

import br.ufop.trabalho.Util;
import br.ufop.trabalho.controle.Constantes;
import br.ufop.trabalho.controle.Controle;
import br.ufop.trabalho.entities.Data;

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
                    \t 10 - Voltar
                    """);
            int op = Util.leInteiroConsole(input);
            switch (op) {
                case 1 -> cadastrarFilme();
                case 10 -> {
                    return;
                }
                default -> System.out.println("Opcao invalida");
            }
        } while (continua);
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
