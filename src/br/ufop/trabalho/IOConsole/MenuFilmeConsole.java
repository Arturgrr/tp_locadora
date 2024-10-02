package br.ufop.trabalho.IOConsole;

import br.ufop.trabalho.Util;
import br.ufop.trabalho.controle.Constantes;
import br.ufop.trabalho.controle.Controle;
import br.ufop.trabalho.entities.Cliente;
import br.ufop.trabalho.entities.Data;
import br.ufop.trabalho.entities.Dependente;
import br.ufop.trabalho.entities.Filme;

import java.util.ArrayList;
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
                    Digite a opção:
                    \t1 - Cadastrar filme
                    \t2 - Buscar filme
                    \t0 - Voltar
                    """);
            int opcaoEscolhida = Util.leInteiroConsole(input);
            switch (opcaoEscolhida) {
                case 1 -> cadastrarFilme();
                case 2 -> buscarFilme();
                case 0 -> continua = false;
                default -> System.out.println("Opção inválida");
            }
        } while (continua);
    }

    private void cadastrarFilme() {
        input.nextLine();
        System.out.println("Digite o nome do filme:");
        String nome = input.nextLine();

        System.out.println("Digite o gênero do filme:");
        String genero = input.nextLine();

        System.out.println("Digite a data de lançamento do filme (dd/MM/yyyy):");
        Data anoDeLancamento = Util.lerDataValida(input);

        System.out.println("Digite a quantidade de DVDs:");
        int quantidadeDeDvd = Util.leInteiroConsole(input);

        System.out.println("Digite a quantidade de Blu-rays:");
        int quantidadeDeBluRay = Util.leInteiroConsole(input);

        System.out.println("""
                Digite o tipo de filme:
                \t1 - Lançamento
                \t2 - Novo
                \t3 - Antigo
                """);
        int tipo = Util.leInteiroConsole(input);

        int retorno = controle.addFilme(nome, anoDeLancamento, genero, quantidadeDeDvd, quantidadeDeBluRay, tipo);
        String msg = switch (retorno) {
            case Constantes.RESULT_OK -> "Sucesso ao cadastrar filme";
            case Constantes.ERRO_CAMPO_VAZIO -> "Todos os campos devem estar preenchidos";
            case Constantes.ERRO_FILME_EXISTENTE -> "Já existe um filme com esse nome";
            default -> "Erro desconhecido ao cadastrar filme";
        };
        System.out.println(msg);
    }

    private void buscarFilme() {
        boolean continua = true;
        do {
            System.out.println("""
                    Selecione o tipo de busca:

                    \t1 - Buscar por nome
                    \t2 - Buscar por gênero
                    \t3 - Buscar por disponibilidade
                    \t0 - Voltar
                    """);
            int opcaoEscolhida = Util.leInteiroConsole(input);
            ArrayList<Filme> filmesEncontrados;
            switch (opcaoEscolhida) {
                case 1 -> {
                    input.nextLine();
                    System.out.println("Digite o nome do filme que deseja buscar:");
                    String nomeFilme = input.nextLine();
                    filmesEncontrados = controle.buscarFilmesPorNome(nomeFilme);
                    if (!filmesEncontrados.isEmpty()) {
                        exibeFilmesEncontrados(filmesEncontrados);
                    } else {
                        System.out.println("Nenhum filme encontrado com esse nome.");
                    }
                }
                case 2 -> {
                    input.nextLine();
                    System.out.println("Digite o gênero do filme que deseja buscar:");
                    String generoFilme = input.nextLine();
                    filmesEncontrados = controle.buscarFilmesPorGenero(generoFilme);
                    if (!filmesEncontrados.isEmpty()) {
                        exibeFilmesEncontrados(filmesEncontrados);
                    } else {
                        System.out.println("Nenhum filme encontrado com esse gênero.");
                    }
                }
                case 3 -> {
                    filmesEncontrados = controle.buscarFilmesDisponiveis();
                    if (!filmesEncontrados.isEmpty()) {
                        exibeFilmesEncontrados(filmesEncontrados);
                    } else {
                        System.out.println("Nenhum filme disponível.");
                    }
                }
                case 0 -> {
                    System.out.println("Voltando...");
                    continua = false;
                }
                default -> System.out.println("Opção inválida");
            }
        } while (continua);
    }

    private void exibeFilmesEncontrados(ArrayList<Filme> filmesEncontrados) {
        System.out.println("Filmes encontrados:");
        Util.imprimeArrayListFilme(filmesEncontrados);
        System.out.println("Selecione um dos filmes encontrados pelo número correspondente ou 0 para voltar:");
        int indiceFilme = Util.leInteiroConsole(input);
        if (indiceFilme == 0) {
            return;
        }
        if (indiceFilme < 1 || indiceFilme > filmesEncontrados.size()) {
            System.out.println("Índice inválido.");
        } else {
            exibeSubMenuBusca(filmesEncontrados, indiceFilme);
        }
    }

    private void exibeSubMenuBusca(ArrayList<Filme> filmesEnc, int indiceEnc) {
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
                    System.out.println("Digite o novo nome do filme:");
                    String novoNome = input.nextLine();

                    System.out.println("Digite o novo gênero do filme:");
                    String novoGenero = input.nextLine();

                    System.out.println("Digite a nova quantidade de DVDs:");
                    int novaQuantDvd = Util.leInteiroConsole(input);

                    System.out.println("Digite a nova quantidade de Blu-rays:");
                    int novaQuantBlue = Util.leInteiroConsole(input);

                    System.out.println("Digite a nova data de lançamento no formato dd/MM/yyyy:");
                    Data novaData = Util.lerDataValida(input);

                    System.out.println("""
                            Digite o novo tipo de filme:
                            \t1 - Lançamento
                            \t2 - Novo
                            \t3 - Antigo
                            """);
                    int novoTipo = Util.leInteiroConsole(input);

                    int flagOp = controle.editarFilme(filmesEnc, indiceEnc, novoNome, novaData,
                            novoGenero, novaQuantDvd, novaQuantBlue, novoTipo);
                    if (flagOp == 1) {
                        System.out.println("Filme editado com sucesso");
                    } else {
                        System.out.println("ERRO: Filme não editado");
                    }
                }
                case 2 -> {
                    int flagOp = controle.excluirFilme(filmesEnc, indiceEnc);
                    if (flagOp == 1) {
                        System.out.println("Filme excluído com sucesso");
                        flagSubMenu = false;
                    } else {
                        System.out.println("ERRO: Filme não excluído");
                    }
                }
                case 3 -> {
                    input.nextLine();
                    System.out.println("Digite o nome do cliente para locar esse filme:");
                    String clienteLocarPorBusca = input.nextLine();

                    ArrayList<Cliente> clientesEncontrados = controle.buscarClientePorNome(clienteLocarPorBusca);
                    if (clientesEncontrados == null || clientesEncontrados.isEmpty()) {
                        System.out.println("ERRO: Cliente não encontrado.");
                    } else {
                        System.out.println("Clientes encontrados:");
                        Util.imprimeArrayListCliente(clientesEncontrados);
                        System.out.println("Selecione um dos clientes encontrados pelo número correspondente:");
                        int indiceCliente = Util.leInteiroConsole(input);

                        if (indiceCliente < 1 || indiceCliente > clientesEncontrados.size()) {
                            System.out.println("ERRO: Índice inválido");
                        } else {
                            Cliente clienteSelecionado = clientesEncontrados.get(indiceCliente - 1);
                            Filme filmeSelecionado = filmesEnc.get(indiceEnc - 1);

                            System.out.println("Selecione o formato da mídia para locação:");
                            System.out.println("1 - DVD");
                            System.out.println("2 - Blu-ray");
                            int opcaoMidia = Util.leInteiroConsole(input);
                            String formatoMidia;
                            if (opcaoMidia == 1) {
                                formatoMidia = "DVD";
                            } else if (opcaoMidia == 2) {
                                formatoMidia = "Blu-ray";
                            } else {
                                System.out.println("Opção inválida.");
                                break;
                            }

                            Dependente dependenteSelecionado = null;
                            if (!clienteSelecionado.getDependentes().isEmpty()) {
                                System.out.println("Deseja locar para um dependente?");
                                System.out.println("1 - Sim");
                                System.out.println("2 - Não");
                                int opcaoDependente = Util.leInteiroConsole(input);
                                if (opcaoDependente == 1) {
                                    ArrayList<Dependente> dependentes = clienteSelecionado.getDependentes();
                                    System.out.println("Selecione o dependente:");
                                    Util.imprimeArrayListDependente(dependentes);
                                    int indiceDependente = Util.leInteiroConsole(input);
                                    if (indiceDependente >= 1 && indiceDependente <= dependentes.size()) {
                                        dependenteSelecionado = dependentes.get(indiceDependente - 1);
                                    } else {
                                        System.out.println("Índice de dependente inválido.");
                                        break;
                                    }
                                }
                            }

                            int flagLocacao = controle.locarFilmeParaCliente(clienteSelecionado, filmeSelecionado, formatoMidia, dependenteSelecionado);
                            if (flagLocacao == Constantes.RESULT_OK) {
                                System.out.println("Filme locado com sucesso para " + clienteSelecionado.getNome());
                            } else if (flagLocacao == Constantes.RESULT_ERRO_EXCEDE_MAX_FILMES) {
                                System.out.println("O cliente já atingiu o limite máximo de filmes locados.");
                            } else if (flagLocacao == Constantes.RESULT_ERRO) {
                                System.out.println("ERRO: Filme indisponível para locação no formato selecionado.");
                            } else {
                                System.out.println("ERRO ao tentar locar o filme.");
                            }
                        }
                    }
                }
                case 0 -> {
                    System.out.println("Voltando...");
                    flagSubMenu = false;
                }
                default -> System.out.println("Opção inválida");
            }
        } while (flagSubMenu);
    }
}
