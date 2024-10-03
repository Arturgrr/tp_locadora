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
        System.out.println("Digite o nome do cliente:");
        String nome = input.nextLine();

        System.out.println("Digite o endereço do cliente:");
        String endereco = input.nextLine();

        System.out.println("Digite o CPF do cliente:");
        String cpf = Util.lerCpfValido(input);

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
            System.out.println("Digite o nome do dependente:");
            String nomeDep = input.nextLine();

            System.out.println("Digite o endereço do dependente:");
            String enderecoDep = input.nextLine();

            System.out.println("Digite o CPF do dependente:");
            String cpfDep = Util.lerCpfValido(input);

            System.out.println("Digite a data de nascimento do dependente (dd/MM/yyyy):");
            Data dataNascimentoDep = Util.lerDataValida(input);

            try {
                Dependente dependente = new Dependente(nomeDep, enderecoDep, cpfDep, dataNascimentoDep, cliente);
                cliente.addDependentes(dependente);
                System.out.println("Dependente adicionado com sucesso!");
            } catch (IllegalArgumentException e) {
                System.out.println("Erro ao adicionar dependente: " + e.getMessage());
            }

            if (cliente.getDependentes().size() >= 3) {
                System.out.println("O cliente atingiu o número máximo de dependentes.");
                break;
            }

            System.out.println("Deseja adicionar outro dependente? (1 - Sim, 2 - Não)");
            int opcao = Util.leInteiroConsole(input);
            if (opcao != 1) {
                break;
            }
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
                \t1 - Editar Cliente
                \t2 - Excluir Cliente
                \t3 - Locar Filme
                \t4 - Devolver Filme
                \t5 - Pagar Multa
                \t6 - Consultar Multa
                \t7 - Listar Filmes Locados
                \t8 - Gerenciar Dependentes
                \t0 - Voltar
                """);
            int opcaoAcao = Util.leInteiroConsole(input);
            switch (opcaoAcao) {
                case 1 -> editarCliente(cliente);
                case 2 -> excluirCliente(cliente);
                case 3 -> locarFilme(cliente);
                case 4 -> devolverFilme(cliente);
                case 5 -> pagarMulta(cliente);
                case 6 -> consultarMulta(cliente);
                case 7 -> listarFilmesLocados(cliente);
                case 8 -> gerenciarDependentes(cliente);
                case 0 -> continuaSubMenu = false;
                default -> System.out.println("Opção inválida");
            }
        } while (continuaSubMenu);
    }

    private void gerenciarDependentes(Cliente cliente) {
        boolean continua = true;
        while (continua) {
            ArrayList<Dependente> dependentes = cliente.getDependentes();
            if (dependentes.isEmpty()) {
                System.out.println("O cliente não possui dependentes.");
                System.out.println("Deseja adicionar um dependente? (1 - Sim, 2 - Não)");
                int opcao = Util.leInteiroConsole(input);
                if (opcao == 1) {
                    adicionarDependentes(cliente);
                } else {
                    continua = false;
                }
            } else {
                System.out.println("Dependentes do cliente:");
                for (int i = 0; i < dependentes.size(); i++) {
                    System.out.println((i + 1) + " - " + dependentes.get(i).getNome());
                }
                System.out.println("""
                    Escolha uma opção:
                    \t1 - Editar Dependente
                    \t2 - Excluir Dependente
                    \t3 - Adicionar Dependente
                    \t0 - Voltar
                    """);
                int opcao = Util.leInteiroConsole(input);
                switch (opcao) {
                    case 1 -> editarDependente(cliente);
                    case 2 -> excluirDependente(cliente);
                    case 3 -> adicionarDependentes(cliente);
                    case 0 -> continua = false;
                    default -> System.out.println("Opção inválida.");
                }
            }
        }
    }

    private void editarDependente(Cliente cliente) {
        ArrayList<Dependente> dependentes = cliente.getDependentes();
        System.out.println("Selecione o dependente que deseja editar:");
        for (int i = 0; i < dependentes.size(); i++) {
            System.out.println((i + 1) + " - " + dependentes.get(i).getNome());
        }
        int indice = Util.leInteiroConsole(input) - 1;
        if (indice >= 0 && indice < dependentes.size()) {
            Dependente dependente = dependentes.get(indice);
            System.out.println("Digite o novo nome do dependente:");
            String novoNome = input.nextLine();

            System.out.println("Digite o novo endereço do dependente:");
            String novoEndereco = input.nextLine();

            System.out.println("Digite o novo CPF do dependente:");
            String novoCPF = Util.lerCpfValido(input);

            System.out.println("Digite a nova data de nascimento do dependente (dd/MM/yyyy):");
            Data novaData = Util.lerDataValida(input);

            try {
                dependente.setNome(novoNome);
                dependente.setEndereco(novoEndereco);
                dependente.setCpf(novoCPF);
                dependente.setDataNascimento(novaData);
                System.out.println("Dependente editado com sucesso.");
            } catch (IllegalArgumentException e) {
                System.out.println("Erro ao editar dependente: " + e.getMessage());
            }
        } else {
            System.out.println("Índice inválido.");
        }
    }

    private void excluirDependente(Cliente cliente) {
        ArrayList<Dependente> dependentes = cliente.getDependentes();
        System.out.println("Selecione o dependente que deseja excluir:");
        for (int i = 0; i < dependentes.size(); i++) {
            System.out.println((i + 1) + " - " + dependentes.get(i).getNome());
        }
        int indice = Util.leInteiroConsole(input) - 1;
        if (indice >= 0 && indice < dependentes.size()) {
            Dependente dependente = dependentes.get(indice);
            cliente.removeDependentes(dependente);
            System.out.println("Dependente excluído com sucesso.");
        } else {
            System.out.println("Índice inválido.");
        }
    }

    private void editarCliente(Cliente cliente) {
        System.out.println("Digite o novo nome do cliente:");
        String novoNome = input.nextLine();

        System.out.println("Digite o novo endereço do cliente:");
        String novoEndereco = input.nextLine();

        System.out.println("Digite o novo CPF do cliente:");
        String novoCPF = Util.lerCpfValido(input);

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
        } else if (resultado == Constantes.RESULT_ERRO_CLIENTE_COM_MULTA) {
            System.out.println("O cliente possui multa em aberto. Não é possível locar filmes até que a multa seja paga.");
        } else {
            System.out.println("Erro ao locar filme. Verifique a disponibilidade do filme no formato selecionado.");
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

            // Solicitar a data real de devolução
            System.out.println("Digite a data de devolução (dd/MM/yyyy):");
            Data dataDevolucaoReal = Util.lerDataValida(input);

            // Calcular dias de atraso
            int diasAtraso = Util.calcularDiasAtraso(locacaoParaDevolver.getDataDevolucaoPrevista(), dataDevolucaoReal);

            // Atualizar estoque do filme
            controle.devolverFilme(cliente, locacaoParaDevolver.getFilme(), locacaoParaDevolver.getFormatoMidia());

            // Aplicar multa se houver atraso
            if (diasAtraso > 0) {
                double multaTotal = diasAtraso * controle.VALOR_MULTA_DIA;
                cliente.addMulta(multaTotal);
                // Registrar entrada financeira pela multa
                controle.cadastrarEntrada("Multa por Atraso", "Multa aplicada ao cliente " + cliente.getNome(), multaTotal, dataDevolucaoReal);
                System.out.println("Cliente recebeu multa por atraso de " + diasAtraso + " dias. Multa total: R$ " + multaTotal);
            } else {
                System.out.println("Devolução dentro do prazo. Nenhuma multa aplicada.");
            }

            // Remover locação da lista
            cliente.getFilmesLocados().remove(locacaoParaDevolver);
            controle.salvarDados();

            System.out.println("Filme devolvido com sucesso.");
        } else {
            System.out.println("Opção inválida.");
        }
    }

    private void pagarMulta(Cliente cliente) {
        double multa = cliente.getMulta();
        if (multa > 0) {
            System.out.printf("O cliente possui multa de R$ %.2f\n", multa);
            System.out.println("Deseja pagar a multa? (1 - Sim, 2 - Não)");
            int opcao = Util.leInteiroConsole(input);
            if (opcao == 1) {
                controle.pagarMulta(cliente);
                System.out.println("Multa paga com sucesso. O saldo de multas agora é R$ 0.00");
            } else {
                System.out.println("Multa não foi paga.");
            }
        } else {
            System.out.println("O cliente não possui multas pendentes.");
        }
    }


    private void consultarMulta(Cliente cliente) {
        double multa = cliente.getMulta();
        if (multa > 0) {
            System.out.printf("O cliente possui multa de R$ %.2f\n", multa);
        } else {
            System.out.println("O cliente não possui multas em aberto.");
        }
    }

    private void listarFilmesLocados(Cliente cliente) {
        if (cliente.getFilmesLocados().isEmpty()) {
            System.out.println("O cliente e seus dependentes não possuem filmes locados.");
            return;
        }

        System.out.println("Filmes locados pelo cliente e seus dependentes:");
        ArrayList<Locacao> locacoes = cliente.getFilmesLocados();
        for (Locacao locacao : locacoes) {
            String locatario = locacao.getDependente() != null ? locacao.getDependente().getNome() : cliente.getNome();
            System.out.printf("Filme: %s | Formato: %s | Locatário: %s | Data de Locação: %s\n",
                    locacao.getFilme().getNome(),
                    locacao.getFormatoMidia(),
                    locatario,
                    locacao.getDataLocacao().toString());
        }
    }


}
