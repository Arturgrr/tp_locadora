package br.ufop.trabalho.IOConsole;

import br.ufop.trabalho.Util;
import br.ufop.trabalho.controle.Constantes;
import br.ufop.trabalho.controle.Controle;
import br.ufop.trabalho.entities.Data;
import br.ufop.trabalho.entities.Cliente;
import br.ufop.trabalho.entities.Filme;

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
        String msg = switch (retorno) {
            case Constantes.RESULT_OK -> "Sucesso ao cadastrar filme";
            case Constantes.ERRO_CAMPO_VAZIO -> "Todos os campos devem estar preenchidos";
            default -> "";
        };
        System.out.println(msg);
    }

    /**
     * BUSCAR FILMES COMPLETA
     * 
     * @author João Teixeira
     */
    boolean continua2 = true;

    private void buscarFilme() {
        do {
            System.out.println("""
                    Selecione o tipo de busca:

                    \t1 - Buscar por nome
                    \t2 - Buscar por genero
                    \t3 - Buscar por disponibilidade
                    \t0 - Voltar
                    """);
            int opcaoEscolhida = Util.leInteiroConsole(input);
            ArrayList<Filme> filmesEncontrados = null;
            boolean flagSub = true;
            switch (opcaoEscolhida) {

                case 1 -> {
                    do {
                        System.out.println("Digite o nome do filme que deseja buscar:");
                        input.nextLine();
                        String nomeFilme = input.nextLine();
                        filmesEncontrados = controle.buscarFilmesPorNome(nomeFilme);
                        if (filmesEncontrados != null && !filmesEncontrados.isEmpty()) {
                            System.out.println("Filmes encontrados:");
                            Util.imprimeArrayListFilme(filmesEncontrados);
                            System.out.println("Selecione um dos filmes encontrados:");
                            int indiceFilme = input.nextInt();
                            if (indiceFilme < 1 || indiceFilme > filmesEncontrados.size()) {
                                System.out.println("ERRO! Indice invalido");
                                break;
                            }
                            exibeSubMenuBusca(filmesEncontrados, indiceFilme);
                            break;
                        } else {
                            System.out.println("ERRO! nenhum filme encontrado");
                            flagSub = true;
                        }
                    } while (flagSub);

                }
                case 2 -> {

                    do {
                        System.out.println("Digite o nome do genero:");
                        input.nextLine();
                        String genero = input.nextLine();
                        filmesEncontrados = controle.buscarFilmesPorGenero(genero);
                        if (filmesEncontrados != null && !filmesEncontrados.isEmpty()) {
                            System.out.println("Filmes encontrados:");
                            Util.imprimeArrayListFilme(filmesEncontrados);
                            System.out.println("Selecione um dos filmes encontrados:");
                            int indiceFilme = input.nextInt();
                            if (indiceFilme < 1 || indiceFilme > filmesEncontrados.size()) {
                                System.out.println("ERRO! Indice invalido");
                                break;
                            }
                            exibeSubMenuBusca(filmesEncontrados, indiceFilme);
                            break;
                        } else {
                            System.out.println("ERRO! nenhum filme encontrado");
                            flagSub = true;
                        }
                    } while (flagSub);
                }

                case 3 -> {
                    filmesEncontrados = controle.buscarFilmesDisponiveis();
                    if (filmesEncontrados != null && !filmesEncontrados.isEmpty()) {
                        System.out.println("Filmes encontrados:");
                        Util.imprimeArrayListFilme(filmesEncontrados);
                        System.out.println("Selecione um dos filmes encontrados:");
                        int indiceFilme = input.nextInt();
                        if (indiceFilme < 1 || indiceFilme > filmesEncontrados.size()) {
                            System.out.println("ERRO! Indice invalido");
                            break;
                        }
                        exibeSubMenuBusca(filmesEncontrados, indiceFilme);
                        break;
                    } else {
                        System.out.println("ERRO! nenhum filme disponivel");
                        flagSub = true;
                    }
                }
                case 0 -> {
                    System.out.println("Voltando...");
                    continua2 = false;

                }
                default -> System.out.println("Opcao invalida");
            }
        } while (continua2);
    }

    /**
     * SUB MENU DE BUSCA (EDITAR, ECLUIR, LOCAR)
     * 
     * @author João Teixeira
     */
    public void exibeSubMenuBusca(ArrayList<Filme> filmesEnc, int indiceEnc) {
        boolean flagSubMenu = true;
        do {
            System.out.println("""
                    Você deseja realizar alguma ação com o filme selecionado?

                    \t1 - Editar
                    \t2 - Excluir
                    \t3 - Locar para Cliente
                    \t0 - Voltar
                    """);

            int opcaoAcao = Util.leInteiroConsole(input);
            switch (opcaoAcao) {
                case 1 -> {
                    input.nextLine();
                    System.out.println("Digite o novo nome do Filme:");
                    String novoNome = input.nextLine();

                    System.out.println("Digite o novo genero do filme:");
                    String novoGenero = input.nextLine();

                    System.out.println("Digite a nova quantidade de DVDS:");
                    int novaQuantDvd = input.nextInt();

                    System.out.println("Digite a nova quantidade de BlueRay:");
                    int novaQuantBlue = input.nextInt();
                    input.nextLine();

                    System.out.println("Digite a nova data de lançamento no formato dd/mm/aaaa:");
                    Data novaData = Util.lerDataValida(input);

                    int flagOp = controle.editarFilme(filmesEnc, indiceEnc, novoNome, novaData,
                            novoGenero, novaQuantDvd, novaQuantBlue);
                    if (flagOp == 1) {
                        System.out.println("Filme editado com sucesso");
                    } else {
                        System.out.println("ERRO Filme nao editado");
                    }

                }
                case 2 -> {
                    int flagOp = controle.excluirFilme(filmesEnc, indiceEnc);
                    if (flagOp == 1) {
                        System.out.println("Filme excluido com sucesso");
                    } else {
                        System.out.println("ERRO Filme nao excluido");
                    }

                }
                case 3 -> {
                    System.out.println("Digite o nome do cliente para locar esse filme:");
                    input.nextLine();
                    String clienteLocarPorBusca = input.nextLine();

                    ArrayList<Cliente> clientesEncontrados = controle.buscarClientePorNome(clienteLocarPorBusca);
                    if (clientesEncontrados == null || clientesEncontrados.isEmpty()) {
                        System.out.println("ERRO! Cliente nao encontrado.");
                    } else {
                        // Exibir os clientes encontrados e permitir a seleção de um deles
                        System.out.println("Clientes encontrados:");
                        Util.imprimeArrayListCliente(clientesEncontrados);
                        System.out.println("Selecione um dos clientes encontrados pelo número correspondente:");
                        int indiceCliente = Util.leInteiroConsole(input);

                        if (indiceCliente < 1 || indiceCliente > clientesEncontrados.size()) {
                            System.out.println("ERRO! Indice invalido");
                        } else {
                            Cliente clienteSelecionado = clientesEncontrados.get(indiceCliente - 1);

                            // Chame o método para realizar a locação do filme para o cliente
                            int flagLocacao = controle.locarFilme(clienteSelecionado, filmesEnc.get(indiceEnc - 1));
                            if (flagLocacao == Constantes.RESULT_OK) {
                                System.out.println("Filme locado com sucesso para " + clienteSelecionado.getNome());
                            } else if (flagLocacao == Constantes.RESULT_ERRO) {
                                System.out.println("ERRO! Filme indisponível para locação.");
                            } else {
                                System.out.println("ERRO ao tentar locar o filme.");
                            }
                        }
                    }

                }
                case 0 -> {
                    System.out.println("Voltando...");
                    flagSubMenu = false;
                    break;
                }
                default -> System.out.println("Opcao invalida");
            }

        } while (flagSubMenu);
    }
}