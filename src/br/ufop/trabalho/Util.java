package br.ufop.trabalho;

import br.ufop.trabalho.entities.Data;

import java.util.ArrayList;
import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.Scanner;

import br.ufop.trabalho.entities.Dependente;
import br.ufop.trabalho.entities.Filme;
import br.ufop.trabalho.entities.Cliente;
import java.time.temporal.ChronoUnit;

public class Util {

    public static boolean verificaStringPreenchida(String texto) {
        return texto != null && !texto.trim().isEmpty();
    }

    public static boolean verificaListaStringPreenchida(String... strings) {
        for (String s : strings) {
            if (!verificaStringPreenchida(s))
                return false;
        }
        return true;
    }

    public static boolean senhaComNumero(String senha) {
        for (char c : senha.toCharArray()) {
            if (Character.isDigit(c)) {
                return true;
            }
        }
        return false;
    }

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

    public static boolean validarCpf(String cpf) {
        return cpf != null && cpf.length() == 11;
    }

    public static String limparCpf(String cpf) {
        return cpf.replaceAll("\\D", "");
    }

    public static void imprimeArrayListFilme(ArrayList<Filme> filmes) {
        int i = 1;
        for (Filme elemento : filmes) {

            System.out.println("Filme:  " + i);

            System.out.println(elemento.toString());
            i++;
        }
    }

    public static void imprimeArrayListCliente(ArrayList<Cliente> clientes) {
        int i = 1;
        for (Cliente elemento : clientes) {

            System.out.println("Cliente:  " + i);
            System.out.println(elemento.toString());
            i++;
        }

    }

    public static int calcularDiasAtraso(Data dataLocacao) {
        LocalDate dataLoc = LocalDate.of(dataLocacao.getAno(), dataLocacao.getMes(), dataLocacao.getDia());
        LocalDate dataAtual = LocalDate.now();
        long diasEntre = ChronoUnit.DAYS.between(dataLoc, dataAtual);
        int diasPermitidos = 7;
        int diasAtraso = (int) diasEntre - diasPermitidos;
        return Math.max(diasAtraso, 0);
    }

    public static Data calcularDataDevolucao(Data dataLocacao) {
        LocalDate dataLoc = LocalDate.of(dataLocacao.getAno(), dataLocacao.getMes(), dataLocacao.getDia());
        LocalDate dataDev = dataLoc.plusDays(7);
        return new Data(dataDev.getDayOfMonth(), dataDev.getMonthValue(), dataDev.getYear());
    }

    public static void imprimeArrayListDependente(ArrayList<Dependente> dependentes) {
        for (int i = 0; i < dependentes.size(); i++) {
            System.out.println((i + 1) + " - " + dependentes.get(i).getNome());
        }
    }

}
