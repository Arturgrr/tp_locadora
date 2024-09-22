package br.ufop.trabalho;

import br.ufop.trabalho.entities.Data;
import br.ufop.trabalho.entities.Pessoa;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Esta classe tem a função de oferecer métodos úteis que poderão ser utilizados em vários momentos do código. Por isso, os métodos
 * serão estáticos de forma que possam ser acessados sem a necessidade de instanciar um Objeto da classe Util.
 */
public class Util {

    /**
     * Este método verifica se uma String recebida como parâmetro está preenchida com algum valor. Caso tenha pelo menos um caractere
     * retornará true, caso contrário retornará false.
     *
     * @param texto String a ser verificada, se ela for preenchida apenas com espaços, ele retorna false
     * @return boolean Retorna se a string está oou não preenchida
     */
    public static boolean verificaStringPreenchida(String texto) {
        return texto == null || !texto.trim().isEmpty();
    }

    /**
     * Este método verifica se uma lista de Strings está preenchida. Veja que o parâmetro recebe um número variável de Strings
     * que automaticamente é convertido em um array de Strings.
     *
     * @param strings Lista com as strings a serem verificadas, se alguma delas estiver preenchida apenas com espaços ele retorna false
     * @return boolean
     */
    public static boolean verificaListaStringPreenchida(String... strings) {
        for (String s : strings) {
            if (!verificaStringPreenchida(s))
                return false;
        }
        return true;
    }

    /**
     * Método que verifica se a senha tem pelo menos um número.
     *
     * @param senha String que contenha todos os números da senha a ser verificada
     * @return boolean
     */
    public static boolean senhaComNumero(String senha) {
        //FALTA IMPLEMENTAR
        return true;
    }

    /**
     * Inicia um scanner que só aceita Inteiros
     *
     * @param in Scanner a ser usado
     * @return number
     */
    public static int leInteiroConsole(Scanner in) {
        int r = 0;
        boolean continua = false;
        do {
            try {
                r = in.nextInt();
                continua = false;
            } catch (InputMismatchException e) {
                System.out.println("Erro ao ler n�mero! Digite novamente:");
                in.nextLine();
                continua = true;
            }
        } while (continua);
        return r;
    }

    /**
     * Inicia um scanner que só aceita String com um cpf valido
     *
     * @param in Scanner a ser usado
     * @return String
     * @author Artur Guerra
     */
    public static String lerCpfValido(Scanner in) {
        String cpf = "";
        boolean valido = false;
        do {
            try{
                cpf = in.nextLine();
                valido = Pessoa.validarCpf(Pessoa.limparCpf(cpf));
                if(!valido){
                    System.out.println("CPF inválido. Digite novamente.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Erro ao ler CPF! Digite novamente:");
            }
        } while (valido);
        return Pessoa.limparCpf(cpf);
    }

    /**
     * Inicia um scanner que só aceita uma Data válida
     *
     * @param in Scanner a ser usado
     * @return Data
     * @author Artur Guerra
     */
    public static Data lerDataValida(Scanner in) {
        Data data = null;
        boolean valido = false;

        do {
            String info = in.nextLine();
            String[] partes = info.split("/");

            if (partes.length != 3) {
                System.out.println("Formato inválido! Certifique-se de usar dd/mm/aaaa.");
            } else {
                try {
                    int dia = Integer.parseInt(partes[0]);
                    int mes = Integer.parseInt(partes[1]);
                    int ano = Integer.parseInt(partes[2]);

                    data = new Data(dia, mes, ano);
                    valido = true;
                } catch (NumberFormatException e) {
                    System.out.println("Erro: a data deve conter apenas números válidos.");
                } catch (Exception e) {
                    System.out.println("Erro ao criar a data. Verifique se os valores são válidos.");
                }
            }
        } while (!valido);

        return data;
    }

}
