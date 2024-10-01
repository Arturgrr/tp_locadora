package br.ufop.trabalho;

import br.ufop.trabalho.entities.Data;

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
     * @param texto String a ser verificada
     * @return boolean Retorna se a string está ou não preenchida
     */
    public static boolean verificaStringPreenchida(String texto) {
        return texto != null && !texto.trim().isEmpty();
    }

    /**
     * Verifica se todas as strings de uma lista estão preenchidas.
     *
     * @param strings Lista de strings a ser verificada.
     * @return boolean Retorna true se todas as strings estiverem preenchidas, false se qualquer uma estiver vazia ou nula.
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
     * @author Artur Guerra
     */
    public static boolean senhaComNumero(String senha) {
        for (char c : senha.toCharArray()) {
            if (Character.isDigit(c)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Inicia um scanner que só aceita Inteiros
     *
     * @param in Scanner a ser usado
     * @return number
     */
    public static int leInteiroConsole(Scanner in) {
        int r = 0;
        do {
            try {
                r = in.nextInt();
                break;
            } catch (InputMismatchException e) {
                System.out.println("Erro ao ler número! Digite novamente:");
                in.nextLine();
            }
        } while (true);
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
        String cpf;
        do {
            cpf = in.nextLine();
            if (!validarCpf(limparCpf(cpf))) {
                System.out.println("CPF inválido. Digite novamente.");
            }
        } while (!validarCpf(limparCpf(cpf)));
        return limparCpf(cpf);
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
                System.out.println("Certifique-se de usar dd/mm/aaaa.");
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

    /**
     * @author Artur Guerra
     */
    public static boolean validarCpf(String cpf) {
        return cpf != null && cpf.length() == 11;
    }

    /**
     * @author Artur Guerra
     */
    public static String limparCpf(String cpf) {
        return cpf.replaceAll("\\D", "");
    }
}
