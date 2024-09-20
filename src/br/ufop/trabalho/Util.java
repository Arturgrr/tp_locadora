package br.ufop.trabalho;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Esta classe tem a função de oferecer métodos úteis que poderão ser utilizados em vários momentos do código. Por isso, os métodos
 * serão estáticos de forma que possam ser acessados sem a necessidade de instanciar um Objeto da classe Util.
 *
 * @author Filipe
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

}
