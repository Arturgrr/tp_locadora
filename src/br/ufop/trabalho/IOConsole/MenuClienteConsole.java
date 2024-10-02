package br.ufop.trabalho.IOConsole;

import java.util.ArrayList;
import java.util.Scanner;

import br.ufop.trabalho.Util;
import br.ufop.trabalho.controle.Constantes;
import br.ufop.trabalho.controle.Controle;
import br.ufop.trabalho.entities.Cliente;
import br.ufop.trabalho.entities.Data;
import br.ufop.trabalho.entities.Dependente;
import br.ufop.trabalho.entities.Filme;

// @author Iaggo Rauta
public class MenuClienteConsole {

    private final Controle controle;
    private final Scanner input;

    public MenuClienteConsole(Controle controle, Scanner input) {
        this.controle = controle;
        this.input = input;
    }

    public void exibeMenuClientes() {
        boolean continua = true;
        do {
            System.out.println("""
                    Digite a opcao:\

                    \t1 - Cadastrar Cliente\

                    \t2 - Buscar clientes\

                    \t5 - imprime Lista de Clientes\

                    \t0 - Voltar
                    """);
            int op = Util.leInteiroConsole(input);
            switch (op) {
                case 1 -> leDadosCliente();
                case 2 -> buscarCliente();
                case 5 -> imprimeListaClientes();
                case 0 -> continua = false;
                default -> System.out.println("Opcao Invalida!");
            }
        } while (continua);
    }

    // @author Iaggo Rauta
    private void buscarCliente() {
        System.out.println("""
                Selecione o tipo de busca:

                \t1 - Buscar pelo nome
                \t2 - Buscar pelo código
                \t3 - Buscar pelo nome do dependente
                \t0 - Voltar
                """);
        int op = Util.leInteiroConsole(input);
        ArrayList<Cliente> clientesEncontrados;
        boolean flagSub = true;
        switch (op) {

            case 1 -> {
                do {
                    System.out.println("Digite o nome do cliente que deseja buscar");
                    input.nextLine();
                    String nome = input.nextLine();
                    clientesEncontrados = controle.buscarClientePorNome(nome); // feito não testado
                    if (clientesEncontrados != null && !clientesEncontrados.isEmpty()) {
                        System.out.println("Clientes encontrados:");
                        Util.imprimeArrayListCliente(clientesEncontrados);
                        System.out.println("Selecione um dos clientes encontrados:");
                        int indiceCliente = input.nextInt();
                        if (indiceCliente < 1 || indiceCliente > clientesEncontrados.size()) {
                            System.out.println("ERRO! Indice invalido");
                            break;
                        }
                        exibeSubMenuBuscaCliente(clientesEncontrados, indiceCliente);
                        break;
                    } else {
                        System.out.println("ERRO! nenhum cliente encontrado");
                        flagSub = true;
                    }

                } while (flagSub);

            }

            case 2 -> {
                System.out.println("Digite o codigo do cliente que deseja buscar");
                int codigo = input.nextInt();
                clientesEncontrados = controle.buscarClientePorCodigo(codigo); // feito não testado
                Util.imprimeArrayListCliente(clientesEncontrados);
            }
            case 3 -> {
                System.out.println("Digite o nome do dependente que deseja buscar");
                input.nextLine();
                String nome = input.nextLine();
                clientesEncontrados = controle.buscarClientePeloNomeDoDepentende(nome);
                Util.imprimeArrayListCliente(clientesEncontrados);
            }
            case 0 -> System.out.println("Voltando...");
            default -> System.out.println("Opcao Invalida!");
        }
    }

    private void leDadosCliente() {
        input.nextLine();
        System.out.println("Digite o nome do cliente");
        String nome = input.nextLine();

        System.out.println("Digite o endereco do cliente");
        String endereco = input.nextLine();

        System.out.println("Digite o cpf do cliente");
        String cpf = Util.lerCpfValido(input);

        System.out.println("Digite a data de nascimento do cliente no padrão dd/mm/aaaa");
        Data dataDN = Util.lerDataValida(input);

        System.out.println("Digite o codigo do cliente");
        int codigo = Util.leInteiroConsole(input);

        input.nextLine();
        int retorno = controle.addCliente(nome, endereco, cpf, dataDN, codigo);
        String msg = switch (retorno) {
            case Constantes.ERRO_CAMPO_VAZIO -> "Todos os campos devem ser preenchidos!";
            case Constantes.RESULT_OK -> "Cliente cadastrado com sucesso!";
            default -> "";
        };
        System.out.println(msg);
    }

    private void imprimeListaClientes() {
        System.out.println("******** LISTA DE CLIENTES CADASTRADOS *********");
        for (int i = 0; i < controle.getQtdClientes(); i++) {
            System.out.println(controle.getClienteNaPosicao(i).toString());
        }
        System.out.println("******** FIM DA LISTA DE CLIENTES  *********");
    }

    public void locarfilme() {
        System.out.println("Digite o código do cliente que deseja locar um filme:");
        int codigoCliente = Util.leInteiroConsole(input);

        Cliente cliente = controle.buscarClientePorCodigo(codigoCliente);
        if (escolhaCliente == 1) {
            System.out.println("Selecione o Cliente:");
            for (int i = 0; i < cliente.getDependentes().size(); i++) {
                System.out.println((i + 1) + " - " + cliente.getDependentes().get(i).getNome());
            }
            int opcaoDependente = Util.leInteiroConsole(input) - 1;

            if (opcaoDependente >= 0 && opcaoDependente < cliente.getDependentes().size()) {
                dependente = controle.getClienteNaPosicao(opcaoDependente);
                System.out.println("Dependente selecionado: " + dependente.getNome());
            } else {
                System.out.println("Opção inválida. Voltando ao cliente principal.");
            }
        }

        if (cliente == null) {
            System.out.println("Cliente não encontrado.");
            return;
        }

        Cliente dependente = null;
        if (cliente.temDependentes()) {
            System.out.println("Esse cliente possui dependentes. Deseja locar o filme em nome de algum dependente?");
            System.out.println("1 - Sim\n2 - Não, locar para o cliente principal.");
            int escolhaDependente = Util.leInteiroConsole(input);

            if (escolhaDependente == 1) {
                System.out.println("Selecione o dependente:");
                for (int i = 0; i < cliente.getDependentes().size(); i++) {
                    System.out.println((i + 1) + " - " + cliente.getDependentes().get(i).getNome());
                }
                int opcaoDependente = Util.leInteiroConsole(input) - 1;

                if (opcaoDependente >= 0 && opcaoDependente < cliente.getDependentes().size()) {
                    dependente = controle.getClienteNaPosicao(opcaoDependente);
                    System.out.println("Dependente selecionado: " + dependente.getNome());
                } else {
                    System.out.println("Opção inválida. Voltando ao cliente principal.");
                }
            }
        }

        System.out.println("Digite o nome do filme que deseja locar:");
        String nomeFilme = input.nextLine();

        if (!controle.filmeDisponivel(nomeFilme)) {
            System.out.println("Filme não disponível para locação.");
            return;
        }

        System.out.println("""
                Selecione o formato da mídia:

                \t1 - DVD
                \t2 - Blu-ray
                """);
        int opcaoMidia = Util.leInteiroConsole(input);
        String formatoMidia;
        switch (opcaoMidia) {
            case 1 -> formatoMidia = "DVD";
            case 2 -> formatoMidia = "Blu-ray";
            default -> {
                System.out.println("Opção inválida!");
                return;
            }
        }

        boolean sucessoLocacao = controle.locarFilmeParaCliente(cliente, nomeFilme, formatoMidia, dependente);
        if (sucessoLocacao) {
            System.out.println("Filme " + nomeFilme + " locado com sucesso no formato " + formatoMidia + "!");
        } else {
            System.out.println("Não há " + formatoMidia + " disponível para o filme " + nomeFilme + ".");
        }
    }

    /**
     * SUB MENU DE BUSCA CLIENTES (EDITAR, LOCAR, DEVOLVER FILME, PAGAR MULTA)
     * 
     * @author João Teixeira
     */
    public void exibeSubMenuBuscaCliente(ArrayList<Cliente> clientesEnc, int indiceEnc) {
        boolean flagSubMenu = true;
        do {
            System.out.println("""
                    Você deseja realizar alguma ação com o cliente selecionado?

                    \t1 - Editar
                    \t2 - Locar
                    \t3 - Devolver filme
                    \t4 - Pagar multa
                    \t0 - Voltar
                    """);

            int opcaoAcao = Util.leInteiroConsole(input);
            switch (opcaoAcao) {
                case 1 -> {
                    input.nextLine();
                    System.out.println("Digite as novas informacoes do cliente:");
                    System.out.println("Digite o novo nome:");
                    String novoNome = input.nextLine();

                    System.out.println("Digite o novo endereço:");
                    String novoEndereco = input.nextLine();

                    System.out.println("Digite o novo CPF:");
                    String novoCPF = input.nextLine();

                    System.out.println("Digite a nova data de nascimento no formato dd/mm/aaaa:");
                    Data novaData = Util.lerDataValida(input);

                    System.out.println("Digite o novo codigo:");
                    int novoCodigo = input.nextInt();

                    System.out.println("Digite o novo valor de multa:");
                    Double novaMulta = input.nextDouble();

                    int flagOp = controle.editarCliente(clientesEnc, indiceEnc, novoNome, novoEndereco, novoCPF,
                            novaData, novoCodigo, novaMulta);
                    if (flagOp == 1) {
                        System.out.println("Cliente editado com sucesso");
                    } else {
                        System.out.println("ERRO Cliente nao editado");
                    }

                }
                case 2 -> {
                    Cliente clienteParaLocar = clientesEnc.get(indiceEnc - 1);
                    input.nextLine();
                    System.out.println("Digite o nome do filme para locar:");
                    String nomeDoFilme = input.nextLine();
                    Filme filmeEncontrado=controle.buscarFilmePorNome(nomeDoFilme);
                    if(filmeEncontrado == null){
                        System.out.println("ERRO Filme nao encontrado");
                        break;
                    }else{
                        int flagOp = controle.locarFilme(clienteParaLocar, filmeEncontrado);
                        if (flagOp == 1) {
                            System.out.println("Filme locado com sucesso");
                            break;
                        } else {
                            System.out.println("ERRO Filme nao locado");
                            break;
                        }
                       
                    }
                    
                    
                }
                case 3 -> {
                    System.out.println("Houve atraso para entregar esse filme? Digite: 1 para sim, 0 para nao");
                    int flagAtraso = input.nextInt();
                    if (flagAtraso == 1) {
                        System.out.println("Digite a quantidade de dias de atraso para a entrega:");
                        int diasAtraso = input.nextInt();
                        controle.multarCliente(clientesEnc.get(indiceEnc - 1), diasAtraso);
                    }
                    input.nextLine();
                    System.out.println("Digite o nome do filme para devolucao:");
                    String nomeDoFilme = input.nextLine();
                    Filme filmeEncontrado=controle.buscarFilmePorNome(nomeDoFilme);
                    if(filmeEncontrado == null){
                        System.out.println("ERRO Filme nao encontrado");
                        break;
                    }
                    System.out.println("Digite o tipo do filme para devolucao (Digite 1 para DVD e 2 para BlueRay)");
                    int tipoDoFilme = input.nextInt();
                    controle.devolverFilme(filmeEncontrado, tipoDoFilme);
                    controle.cadastrarEntrada("Locação de Filme", "Devolução", controle.VALOR_LOC_DIA, null);
                }
                case 4->{
                    controle.pagarMulta(clientesEnc.get(indiceEnc - 1));
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
