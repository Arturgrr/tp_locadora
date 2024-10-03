package br.ufop.trabalho;

import br.ufop.trabalho.entities.Data;
import br.ufop.trabalho.entities.Dependente;
import br.ufop.trabalho.entities.Filme;
import br.ufop.trabalho.entities.Cliente;

import java.util.ArrayList;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
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

    public static int leInteiroConsole(Scanner in) {
        int r;
        while (true) {
            try {
                r = Integer.parseInt(in.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Erro ao ler número! Digite novamente:");
            }
        }
        return r;
    }

    public static double leDoubleConsole(Scanner in) {
        double r;
        while (true) {
            try {
                r = Double.parseDouble(in.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Erro ao ler número! Digite novamente:");
            }
        }
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
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                LocalDate localDate = LocalDate.parse(info, formatter);
                data = new Data(localDate.getDayOfMonth(), localDate.getMonthValue(), localDate.getYear());
                valido = true;
            } catch (Exception e) {
                System.out.println("Data inválida. Certifique-se de usar o formato dd/MM/yyyy.");
            }
        } while (!valido);

        return data;
    }

    public static boolean validarCpf(String cpf) {
        cpf = limparCpf(cpf);
        if (cpf == null || cpf.length() != 11) {
            return false;
        }

        try {
            Long.parseLong(cpf);
        } catch (NumberFormatException e) {
            return false;
        }

        // Verificação simplificada apenas para o tamanho
        return true;
    }

    public static String limparCpf(String cpf) {
        return cpf.replaceAll("\\D", "");
    }

    public static void imprimeArrayListFilme(ArrayList<Filme> filmes) {
        for (int i = 0; i < filmes.size(); i++) {
            System.out.println((i + 1) + " - " + filmes.get(i).toString());
        }
    }

    public static void imprimeArrayListCliente(ArrayList<Cliente> clientes) {
        for (int i = 0; i < clientes.size(); i++) {
            System.out.println((i + 1) + " - " + clientes.get(i).toString());
        }
    }

    public static int calcularDiasAtraso(Data dataDevolucaoPrevista, Data dataDevolucaoReal) {
        LocalDate dataPrevista = LocalDate.of(dataDevolucaoPrevista.getAno(), dataDevolucaoPrevista.getMes(), dataDevolucaoPrevista.getDia());
        LocalDate dataReal = LocalDate.of(dataDevolucaoReal.getAno(), dataDevolucaoReal.getMes(), dataDevolucaoReal.getDia());
        long diasEntre = ChronoUnit.DAYS.between(dataPrevista, dataReal);
        int diasAtraso = (int) diasEntre;
        return Math.max(diasAtraso, 0);
    }

    public static Data calcularDataDevolucao(Data dataLocacao, int prazoDevolucao) {
        LocalDate dataLoc = LocalDate.of(dataLocacao.getAno(), dataLocacao.getMes(), dataLocacao.getDia());
        LocalDate dataDev = dataLoc.plusDays(prazoDevolucao);
        return new Data(dataDev.getDayOfMonth(), dataDev.getMonthValue(), dataDev.getYear());
    }

    public static void imprimeArrayListDependente(ArrayList<Dependente> dependentes) {
        for (int i = 0; i < dependentes.size(); i++) {
            System.out.println((i + 1) + " - " + dependentes.get(i).getNome());
        }
    }

    public static Data obterDataAtual() {
        LocalDate hoje = LocalDate.now();
        return new Data(hoje.getDayOfMonth(), hoje.getMonthValue(), hoje.getYear());
    }

}
