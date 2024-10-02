package br.ufop.trabalho.IOConsole;

import br.ufop.trabalho.Util;
import br.ufop.trabalho.controle.Constantes;
import br.ufop.trabalho.controle.Controle;
import br.ufop.trabalho.entities.Cliente;
import br.ufop.trabalho.entities.Data;
import br.ufop.trabalho.entities.Dependente;
import br.ufop.trabalho.entities.Filme;
import br.ufop.trabalho.entities.Locacao;
import java.util.ArrayList;
import java.util.Scanner;

public class MenuClienteConsole {
    private final Controle controle;
    private final Scanner input;

    public MenuClienteConsole(Controle controle, Scanner input) {
        this.controle = controle;
        this.input = input;
    }

    public void exibeMenuCliente() {
        boolean continua = true;
        do {
            System.out.println("""
                    Digite a opção:
                    \t1 - Cadastrar cliente
                    \t2 - Buscar cliente
                    \t0 - Voltar
                    """);
            int opcaoEscolhida = Util.leInteiroConsole(input);
            switch (opcaoEscolhida) {
                case 1 -> cadastrarCliente();
                case 2 -> buscarCliente();
                case 0 -> continua = false;
                default -> System.out.println("Opção inválida");
            }
        } while (continua);
    }

    private void cadastrarCliente() {
        input.nextLine();
        System.out.println("Digite o nome do cliente:");
        String nome = input.nextLine();

        System.out.println("Digite o endereço do cliente:");
        String endereco = input.nextLine();

        System.out.println("Digite o CPF do cliente:");
        String cpf =  Util.lerCpfValido(input);

        System.out.println("Digite a data de nascimento do cliente (dd/MM/yyyy):");
        Data dataNascimento = Util.lerDataValida(input);

        System.out.println("Digite o código do cliente:");
        int codigo = Util.leInteiroConsole(input);

        int retorno = controle.addCliente(nome, endereco, cpf, dataNascimento, codigo);
        String msg = switch (retorno) {
            case Constantes.RESULT_OK -> "Cliente cadastrado com sucesso!";
            case Constantes.ERRO_CAMPO_VAZIO -> "Todos os campos devem estar preenchidos.";
            case Constantes.ERRO_CLIENTE_EXISTENTE_CODIGO -> "Já existe um cliente com esse código.";
            case Constantes.ERRO_CLIENTE_EXISTENTE_CPF -> "Já existe um cliente com esse CPF.";
            default -> "Erro desconhecido ao cadastrar cliente.";
        };
        System.out.println(msg);

        if (retorno == Constantes.RESULT_OK) {
            Cliente cliente = controle.buscarClientePorCodigo(codigo);
            if (cliente != null) {
                adicionarDependentes(cliente);
            }
        }
    }

    private void adicionarDependentes(Cliente cliente) {
        while (cliente.getDependentes().size() < 3) {
            System.out.println("Deseja adicionar um dependente? (1 - Sim, 2 - Não)");
            int opcao = Util.leInteiroConsole(input);
            if (opcao != 1) {
                break;
            }

            input.nextLine();
            System.out.println("Digite o nome do dependente:");
            String nomeDep = input.nextLine();

            System.out.println("Digite o endereço do dependente:");
            String enderecoDep = input.nextLine();

            System.out.println("Digite o CPF do dependente:");
            String cpfDep = input.nextLine();

            System.out.println("Digite a data de nascimento do dependente (dd/MM/yyyy):");
            Data dataNascimentoDep = Util.lerDataValida(input);

            Dependente dependente = new Dependente(nomeDep, enderecoDep, cpfDep, dataNascimentoDep, cliente);
            cliente.addDependentes(dependente);
            System.out.println("Dependente adicionado com sucesso!");
        }
    }

    private void buscarCliente() {
        boolean continuaBusca = true;
        do {
            System.out.println("""
                    Selecione o tipo de busca:
                    \t1 - Buscar por nome
                    \t2 - Buscar por código
                    \t3 - Buscar por nome do dependente
                    \t0 - Voltar
                    """);
            int opcaoBusca = Util.leInteiroConsole(input);
            ArrayList<Cliente> clientesEncontrados = null;
            switch (opcaoBusca) {
                case 1 -> {
                    System.out.println("Digite o nome do cliente:");
                    input.nextLine();
                    String nome = input.nextLine();
                    clientesEncontrados = controle.buscarClientePorNome(nome);
                    exibirResultadosBusca(clientesEncontrados);
                }
                case 2 -> {
                    System.out.println("Digite o código do cliente:");
                    int codigo = Util.leInteiroConsole(input);
                    Cliente cliente = controle.buscarClientePorCodigo(codigo);
                    if (cliente != null) {
                        clientesEncontrados = new ArrayList<>();
                        clientesEncontrados.add(cliente);
                        exibirResultadosBusca(clientesEncontrados);
                    } else {
                        System.out.println("Cliente não encontrado.");
                    }
                }
                case 3 -> {
                    System.out.println("Digite o nome do dependente:");
                    input.nextLine();
                    String nomeDep = input.nextLine();
                    clientesEncontrados = controle.buscarClientePeloNomeDoDependente(nomeDep);
                    exibirResultadosBusca(clientesEncontrados);
                }
                case 0 -> continuaBusca = false;
                default -> System.out.println("Opção inválida");
            }
        } while (continuaBusca);
    }

    private void exibirResultadosBusca(ArrayList<Cliente> clientesEncontrados) {
        if (clientesEncontrados == null || clientesEncontrados.isEmpty()) {
            System.out.println("Nenhum cliente encontrado.");
            return;
        }

        System.out.println("Clientes encontrados:");
        Util.imprimeArrayListCliente(clientesEncontrados);
        System.out.println("Selecione um dos clientes pelo número correspondente:");
        int indiceCliente = Util.leInteiroConsole(input);

        if (indiceCliente < 1 || indiceCliente > clientesEncontrados.size()) {
            System.out.println("Índice inválido.");
            return;
        }

        Cliente clienteSelecionado = clientesEncontrados.get(indiceCliente - 1);
        exibeSubMenuCliente(clienteSelecionado);
    }

    private void exibeSubMenuCliente(Cliente cliente) {
        boolean continuaSubMenu = true;
        do {
            System.out.println("""
                    Você deseja realizar alguma ação com o cliente selecionado?
                    \t1 - Editar
                    \t2 - Excluir
                    \t3 - Locar filme
                    \t4 - Devolver filme
                    \t0 - Voltar
                    """);
            int opcaoAcao = Util.leInteiroConsole(input);
            switch (opcaoAcao) {
                case 1 -> editarCliente(cliente);
                case 2 -> excluirCliente(cliente);
                case 3 -> locarFilme(cliente);
                case 4 -> devolverFilme(cliente);
                case 0 -> continuaSubMenu = false;
                default -> System.out.println("Opção inválida");
            }
        } while (continuaSubMenu);
    }

    private void editarCliente(Cliente cliente) {
        input.nextLine();
        System.out.println("Digite o novo nome do cliente:");
        String novoNome = input.nextLine();

        System.out.println("Digite o novo endereço do cliente:");
        String novoEndereco = input.nextLine();

        System.out.println("Digite o novo CPF do cliente:");
        String novoCPF = input.nextLine();

        System.out.println("Digite a nova data de nascimento do cliente (dd/MM/yyyy):");
        Data novaData = Util.lerDataValida(input);

        System.out.println("Digite o novo código do cliente:");
        int novoCodigo = Util.leInteiroConsole(input);

        int resultado = controle.editarCliente(cliente, novoNome, novoEndereco, novoCPF, novaData, novoCodigo, cliente.getMulta());
        if (resultado == 1) {
            System.out.println("Cliente editado com sucesso.");
        } else {
            System.out.println("Erro ao editar cliente.");
        }

        System.out.println("""
                Deseja adicionar ou remover dependentes?
                \t1 - Adicionar dependente
                \t2 - Remover dependente
                \t3 - Não alterar dependentes
                """);
        int opcaoDependentes = Util.leInteiroConsole(input);
        switch (opcaoDependentes) {
            case 1 -> adicionarDependentes(cliente);
            case 2 -> removerDependente(cliente);
            case 3 -> System.out.println("Nenhuma alteração nos dependentes.");
            default -> System.out.println("Opção inválida");
        }
    }

    private void removerDependente(Cliente cliente) {
        if (cliente.getDependentes().isEmpty()) {
            System.out.println("O cliente não possui dependentes.");
            return;
        }
        System.out.println("Dependentes do cliente:");
        ArrayList<Dependente> dependentes = cliente.getDependentes();
        for (int i = 0; i < dependentes.size(); i++) {
            System.out.println((i + 1) + " - " + dependentes.get(i).getNome());
        }
        System.out.println("Selecione o dependente que deseja remover:");
        int indiceDependente = Util.leInteiroConsole(input);
        if (indiceDependente >= 1 && indiceDependente <= dependentes.size()) {
            Dependente depRemover = dependentes.get(indiceDependente - 1);
            cliente.removeDependentes(depRemover);
            System.out.println("Dependente removido com sucesso.");
        } else {
            System.out.println("Opção inválida.");
        }
    }

    private void excluirCliente(Cliente cliente) {
        controle.excluirCliente(cliente);
        System.out.println("Cliente excluído com sucesso.");
    }

    private void locarFilme(Cliente cliente) {
        System.out.println("Digite o nome do filme que deseja locar:");
        input.nextLine();
        String nomeFilme = input.nextLine();
        ArrayList<Filme> filmesEncontrados = controle.buscarFilmesPorNome(nomeFilme);

        if (filmesEncontrados == null || filmesEncontrados.isEmpty()) {
            System.out.println("Filme não encontrado.");
            return;
        }

        System.out.println("Filmes encontrados:");
        Util.imprimeArrayListFilme(filmesEncontrados);
        System.out.println("Selecione um dos filmes pelo número correspondente:");
        int indiceFilme = Util.leInteiroConsole(input);

        if (indiceFilme < 1 || indiceFilme > filmesEncontrados.size()) {
            System.out.println("Índice inválido.");
            return;
        }

        Filme filmeSelecionado = filmesEncontrados.get(indiceFilme - 1);

        Dependente dependenteSelecionado = null;
        if (!cliente.getDependentes().isEmpty()) {
            System.out.println("Deseja realizar a locação para um dependente?");
            System.out.println("1 - Sim");
            System.out.println("2 - Não");
            int opcaoDependente = Util.leInteiroConsole(input);
            if (opcaoDependente == 1) {
                System.out.println("Selecione o dependente:");
                ArrayList<Dependente> dependentes = cliente.getDependentes();
                for (int i = 0; i < dependentes.size(); i++) {
                    System.out.println((i + 1) + " - " + dependentes.get(i).getNome());
                }
                int indiceDependente = Util.leInteiroConsole(input);
                if (indiceDependente >= 1 && indiceDependente <= dependentes.size()) {
                    dependenteSelecionado = dependentes.get(indiceDependente - 1);
                } else {
                    System.out.println("Opção inválida. A locação será feita para o cliente principal.");
                }
            }
        }

        System.out.println("Selecione o formato de mídia:");
        System.out.println("1 - DVD");
        System.out.println("2 - Blu-ray");
        int opcaoMidia = Util.leInteiroConsole(input);
        String formatoMidia = opcaoMidia == 1 ? "DVD" : "Blu-ray";

        int resultado = controle.locarFilmeParaCliente(cliente, filmeSelecionado, formatoMidia, dependenteSelecionado);
        if (resultado == Constantes.RESULT_OK) {
            System.out.println("Filme locado com sucesso!");
        } else if (resultado == Constantes.RESULT_ERRO_EXCEDE_MAX_FILMES) {
            System.out.println("O cliente já atingiu o limite máximo de filmes locados.");
        } else {
            System.out.println("Erro ao locar filme.");
        }
    }

    private void devolverFilme(Cliente cliente) {
        if (cliente.getFilmesLocados().isEmpty()) {
            System.out.println("O cliente não possui filmes locados.");
            return;
        }

        System.out.println("Filmes locados:");
        ArrayList<Locacao> locacoes = cliente.getFilmesLocados();
        for (int i = 0; i < locacoes.size(); i++) {
            Locacao locacao = locacoes.get(i);
            System.out.println((i + 1) + " - " + locacao.getFilme().getNome() + " (" + locacao.getFormatoMidia() + ")");
        }
        System.out.println("Selecione o filme que deseja devolver:");
        int indiceLocacao = Util.leInteiroConsole(input);
        if (indiceLocacao >= 1 && indiceLocacao <= locacoes.size()) {
            Locacao locacaoParaDevolver = locacoes.get(indiceLocacao - 1);
            controle.devolverFilme(cliente, locacaoParaDevolver.getFilme(), locacaoParaDevolver.getFormatoMidia());
            System.out.println("Filme devolvido com sucesso.");

            // Verificar atraso e aplicar multa se necessário
            int diasAtraso = Util.calcularDiasAtraso(locacaoParaDevolver.getDataLocacao());
            if (diasAtraso > 0) {
                controle.multarCliente(cliente, diasAtraso);
                System.out.println("Cliente recebeu multa por atraso de " + diasAtraso + " dias.");
            }
        } else {
            System.out.println("Opção inválida.");
        }
    }
}
