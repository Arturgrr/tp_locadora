package br.ufop.trabalho.IOConsole;

import br.ufop.trabalho.Util;
import br.ufop.trabalho.controle.Constantes;
import br.ufop.trabalho.controle.Controle;
import br.ufop.trabalho.entities.Data;
import br.ufop.trabalho.entities.Filme;
import br.ufop.trabalho.entities.Pessoa;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author Artur Guerra
 * @author Iaggo Rauta
 * 
 */
public class MenuFilmeConsole {
    private final Controle controle;
    private final Scanner input;

    /**
     * @author Artur Guerra
     */
    public MenuFilmeConsole(Controle controle, Scanner input) {
        this.controle = controle;
        this.input = input;
    }

    /**
     * @author Artur Guerra
     */
    public void exibeMenuFilmes() {
        boolean continua = true;
        do {
            System.out.println("""
                    Digite a opcao:
                    
                    \t1 - Cadastrar filme
                    \t2 - Buscar filme
                    \t10 - Voltar
                    """);
            int opcaoEscolhida = Util.leInteiroConsole(input);
            switch (opcaoEscolhida) {
                case 1 -> cadastrarFilme();
                case 2 -> buscarFilme();
                case 10 -> {
                    continua = false;
                }
                default -> System.out.println("Opcao invalida");
            }
        } while (continua);
    }

    /**
     * @author Artur Guerra
     */
    private void cadastrarFilme() {
        input.nextLine();
        System.out.println("Digite o nome do filme: ");
        String nome = input.nextLine();

        System.out.println("Digite o genero do filme: ");
        String genero = input.nextLine();

        System.out.println("Digite o ano de lancamento do filme (dd/MM/yyyy): ");
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

    /**
     * @author Artur Guerra
     */
    private void buscarFilme() {
        System.out.println("""
                Selecione o tipo de busca:
                
                \t1 - Buscar por nome
                \t2 - Buscar por genero
                \t3 - Buscar por disponibilidade
                \t4 - Voltar
                """);
        int opcaoEscolhida = Util.leInteiroConsole(input);
        ArrayList<Filme> filmesEncontrados = null;
        switch (opcaoEscolhida) {
            case 1 -> {
                System.out.println("Digite o nome do filme que deseja buscar:");
                String nome = input.nextLine();
                filmesEncontrados = controle.buscarFilmesPorNome(nome);
            }
            case 2 -> {
                System.out.println("Digite o nome do genero:");
                String genero = input.nextLine();
                filmesEncontrados = controle.buscarFilmesPorGenero(genero);
            }
            case 3 -> filmesEncontrados = controle.buscarFilmesDisponiveis();
            default -> System.out.println("Opcao invalida");
        }

        if (filmesEncontrados != null && !filmesEncontrados.isEmpty()) {
            System.out.println("Filmes encontrados:");
            for (int i = 0; i < filmesEncontrados.size(); i++) {
                System.out.println((i + 1) + " - " + filmesEncontrados.get(i).toString());
            }

            System.out.println("Selecione um filme para tomar uma acao (digite 0 para voltar)");
            int filmeSelecionado = Util.leInteiroConsole(input);

            if (filmeSelecionado > 0 && filmeSelecionado <= filmesEncontrados.size()) {
                Filme filme = filmesEncontrados.get(filmeSelecionado - 1);
                exibeOpcoesFilme(filme);
            }
        } else {
            System.out.println("Nenhum filme encontrado.");
        }
    }

    /**
     * @author Artur Guerra
     * @author Iaggo Rauta
     */
    private void exibeOpcoesFilme(Filme filmeSelecionado){
        System.out.println("""
                Selecione o que voce deseja fazer:
                
                \t1 - Editar filme
                \t2 - Excluir filme
                \t3 - Locar filme
                \t0 - Voltar
                """);
        int opcaoEscolhida = Util.leInteiroConsole(input);

        switch (opcaoEscolhida) {
            case 1 -> editarFilme(filmeSelecionado);
            case 2 -> excluirFilme(filmeSelecionado);
            case 3 -> System.out.println("Locar filme");
            case 0 -> System.out.println("Voltando...");
            default -> System.out.println("Opcao invalida, voltando ao menu");
        }
    }

    /**
     * @author Artur Guerra
     */
    public void editarFilme(Filme filme) {
        System.out.println("Editar filme: " + filme.getNome());


        System.out.println("Digite o novo nome do filme (ou enter para manter o atual): ");
        String novoNome = input.nextLine();
        if (!novoNome.isEmpty()) {
            filme.setNome(novoNome);
        }

        System.out.println("Digite o novo genero do filme (ou enter para manter o atual): ");
        String novoGenero = input.nextLine();
        if (!novoGenero.isEmpty()) {
            filme.setGenero(novoGenero);
        }

        System.out.println("Digite o novo ano de lancamento (ou enter para manter o atual): ");
        Data novaDataLancamento = Util.lerDataValida(input);
        if (novaDataLancamento != null) {
            filme.setAnoDeLancamento(novaDataLancamento);
        }

        System.out.println("Digite a nova quantidade de DVDs (ou -1 para manter a atual): ");
        int novaQtdDvd = Util.leInteiroConsole(input);
        if (novaQtdDvd >= 0) {
            filme.setQuantDvd(novaQtdDvd);
        }

        System.out.println("Digite a nova quantidade de Blu-rays (ou -1 para manter a atual): ");
        int novaQtdBluRay = Util.leInteiroConsole(input);
        if (novaQtdBluRay >= 0) {
            filme.setQuantBlueRay(novaQtdBluRay);
        }

        System.out.println("Filme editado com sucesso!");
    }

    /**
     * @author Artur Guerra
     */
    public void excluirFilme(Filme filme) {
        System.out.println("Deseja excluir o filme: " + filme.getNome() + "? (Digite S para confirmar)");

        String confirmacao = input.nextLine();
        if (confirmacao.equalsIgnoreCase("S")) {
            controle.excluirFilme(filme);
            System.out.println("Filme excluido com sucesso");
        } else {
            System.out.println("Solicitação cancelada");
        }
    }

    /**
     * @author Artur Guerra
     */
    private void locarFilme(Filme filme, Pessoa pessoa) {
        // TODO: Implementar aqui depois
        // TODO: Devo alternar para o caso de ser um depentende?

        System.out.println("Digite o nome do cliente que irá locar o filme");
        String nome = input.nextLine();


    }


}
