package br.ufop.trabalho.controle;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;

import br.ufop.trabalho.Util;
import br.ufop.trabalho.entities.Cliente;
import br.ufop.trabalho.entities.Data;
import br.ufop.trabalho.entities.Dependente;
import br.ufop.trabalho.entities.Entrada;
import br.ufop.trabalho.entities.Filme;
import br.ufop.trabalho.entities.Locacao;
import br.ufop.trabalho.entities.Movimentacao;
import br.ufop.trabalho.entities.Pessoa;
import br.ufop.trabalho.entities.Saida;

public class Controle implements Serializable {
	@Serial
	private static final long serialVersionUID = 1L;

	private static final String FILE_PATH = "dados.dat";

	private final ArrayList<Cliente> clientes;
	private final ArrayList<Filme> filmes;
	private final ArrayList<Movimentacao> movimentacoes;

	public double VALOR_MULTA_DIA = 10.0;
	public int QUANT_MAX_FILME_CLIENTE = 5;
	public double VALOR_LOC_DIA = 5.0;
	public double VALOR_LOCACAO_LANCAMENTO = 10.0;
	public double VALOR_LOCACAO_NOVO = 7.0;
	public double VALOR_LOCACAO_ANTIGO = 5.0;
	public int PRAZO_DEVOLUCAO = 7;

	public Controle() {
		clientes = new ArrayList<>();
		filmes = new ArrayList<>();
		movimentacoes = new ArrayList<>();
		carregarDados();
	}

	public void salvarDados() {
		try (FileOutputStream arquivoGrav = new FileOutputStream(FILE_PATH);
			 ObjectOutputStream objGravar = new ObjectOutputStream(arquivoGrav)) {

			objGravar.writeObject(clientes);
			objGravar.writeObject(filmes);
			objGravar.writeObject(movimentacoes);

		} catch (IOException e) {
			System.err.println("Erro ao salvar dados: " + e.getMessage());
		}
	}

	@SuppressWarnings("unchecked")
	private void carregarDados() {
		File arquivo = new File(FILE_PATH);
		if (!arquivo.exists()) {
			System.out.println("Arquivo de dados não encontrado. Iniciando com dados vazios.");
			return;
		}

		try (FileInputStream arquivoLeitura = new FileInputStream(FILE_PATH);
			 ObjectInputStream objLeitura = new ObjectInputStream(arquivoLeitura)) {

			clientes.addAll((ArrayList<Cliente>) objLeitura.readObject());
			filmes.addAll((ArrayList<Filme>) objLeitura.readObject());
			movimentacoes.addAll((ArrayList<Movimentacao>) objLeitura.readObject());

		} catch (IOException | ClassNotFoundException e) {
			System.err.println("Erro ao carregar dados: " + e.getMessage());
		}
	}

	public int addCliente(String nome, String end, String cpf, Data dataDN, int codigo) {
		if (!Util.verificaListaStringPreenchida(nome, end, cpf)) {
			return Constantes.ERRO_CAMPO_VAZIO;
		}

		for (Cliente c : clientes) {
			if (c.getCodigo() == codigo) {
				return Constantes.ERRO_CLIENTE_EXISTENTE_CODIGO;
			}
			if (c.getCpf().equalsIgnoreCase(cpf)) {
				return Constantes.ERRO_CLIENTE_EXISTENTE_CPF;
			}
		}

		Cliente cliente = new Cliente(nome, end, cpf, dataDN, codigo);
		clientes.add(cliente);
		salvarDados();
		return Constantes.RESULT_OK;
	}

	public void excluirCliente(Cliente cliente) {
		clientes.remove(cliente);
		salvarDados();
	}

	public ArrayList<Cliente> getClientes() {
		return clientes;
	}

	public ArrayList<Filme> getFilmes() {
		return filmes;
	}

	public ArrayList<Cliente> relatorioClientes() {
		return new ArrayList<>(clientes);
	}

	public ArrayList<Filme> relatorioFilmesPorGenero(String genero) {
		return buscarFilmesPorGenero(genero);
	}

	public ArrayList<Filme> relatorioFilmesPorAno(int ano) {
		ArrayList<Filme> filmesPorAno = new ArrayList<>();
		for (Filme filme : filmes) {
			if (filme.getAnoDeLancamento().getAno() == ano) {
				filmesPorAno.add(filme);
			}
		}
		return filmesPorAno;
	}

	public ArrayList<Filme> relatorioFilmesPorNome() {
		ArrayList<Filme> filmesOrdenados = new ArrayList<>(filmes);
		filmesOrdenados.sort(Comparator.comparing(Filme::getNome));
		return filmesOrdenados;
	}

	public int addFilme(String nome, Data dataDeLancamento, String genero, int quantDvd, int quantBR, int tipoDeFilme) {
		if (!Util.verificaListaStringPreenchida(nome, genero)) {
			return Constantes.ERRO_CAMPO_VAZIO;
		}

		for (Filme f : filmes) {
			if (f.getNome().equalsIgnoreCase(nome)) {
				return Constantes.ERRO_FILME_EXISTENTE;
			}
		}

		Filme filme = new Filme(nome, dataDeLancamento, genero, quantDvd, quantBR, tipoDeFilme);
		filmes.add(filme);
		salvarDados();
		return Constantes.RESULT_OK;
	}

	public ArrayList<Filme> buscarFilmesPorNome(String nome) {
		ArrayList<Filme> filmesEncontrados = new ArrayList<>();
		for (Filme filme : filmes) {
			if (filme.getNome().equalsIgnoreCase(nome)) {
				filmesEncontrados.add(filme);
			}
		}
		return filmesEncontrados;
	}

	public ArrayList<Filme> buscarFilmesPorGenero(String genero) {
		ArrayList<Filme> filmesEncontrados = new ArrayList<>();
		for (Filme filme : filmes) {
			if (filme.getGenero().equalsIgnoreCase(genero)) {
				filmesEncontrados.add(filme);
			}
		}
		return filmesEncontrados;
	}

	public ArrayList<Filme> buscarFilmesDisponiveis() {
		ArrayList<Filme> filmesDisponiveis = new ArrayList<>();
		for (Filme filme : filmes) {
			if (filme.getQuantDvd() > 0 || filme.getQuantBlueRay() > 0) {
				filmesDisponiveis.add(filme);
			}
		}
		return filmesDisponiveis;
	}

	public void excluirFilme(Filme filme) {
		filmes.remove(filme);
		salvarDados();
	}

	public ArrayList<Cliente> buscarClientePorNome(String nome) {
		ArrayList<Cliente> clientesPeloNome = new ArrayList<>();
		for (Cliente cliente : clientes) {
			if (cliente.getNome().equalsIgnoreCase(nome)) {
				clientesPeloNome.add(cliente);
			}
		}
		return clientesPeloNome;
	}

	public Cliente buscarClientePorCodigo(int codigo) {
		for (Cliente cliente : clientes) {
			if (cliente.getCodigo() == codigo) {
				return cliente;
			}
		}
		return null;
	}

	public ArrayList<Dependente> buscarDependentePorNome(String nome) {
		ArrayList<Dependente> dependentes = new ArrayList<>();
		for (Cliente cliente : clientes) {
			for (Dependente dep : cliente.getDependentes()) {
				if (dep.getNome().equalsIgnoreCase(nome)) {
					dependentes.add(dep);
				}
			}
		}
		return dependentes;
	}

	public ArrayList<Pessoa> buscarPessoaPorNome(String nome) {
		ArrayList<Pessoa> pessoasPeloNome = new ArrayList<>();
		pessoasPeloNome.addAll(buscarClientePorNome(nome));
		pessoasPeloNome.addAll(buscarDependentePorNome(nome));
		return pessoasPeloNome;
	}

	public ArrayList<Cliente> buscarClientePeloNomeDoDependente(String nome) {
		ArrayList<Cliente> clientesEncontrados = new ArrayList<>();
		for (Cliente cliente : clientes) {
			for (Dependente dep : cliente.getDependentes()) {
				if (dep.getNome().equalsIgnoreCase(nome)) {
					clientesEncontrados.add(cliente);
					break;
				}
			}
		}
		return clientesEncontrados;
	}

	public void cadastrarEntrada(String nome, String descricao, Double valor, Data data) {
		Entrada entrada = new Entrada(nome, descricao, valor, data, 1);
		movimentacoes.add(entrada);
		salvarDados();
	}

	public void cadastrarSaida(String nome, String descricao, Double valor, Data data) {
		Saida saida = new Saida(nome, descricao, valor, data, 0);
		movimentacoes.add(saida);
		salvarDados();
	}

	public ArrayList<Movimentacao> buscarMovimentacoes(String nome) {
		ArrayList<Movimentacao> movimentacoesPeloNome = new ArrayList<>();
		for (Movimentacao movimentacao : movimentacoes) {
			if (movimentacao.getNome().equalsIgnoreCase(nome)) {
				movimentacoesPeloNome.add(movimentacao);
			}
		}
		return movimentacoesPeloNome;
	}

	public ArrayList<Movimentacao> buscarMovimentacoesPorMes(int mes) {
		ArrayList<Movimentacao> movimentacoesPeloMes = new ArrayList<>();
		for (Movimentacao movimentacao : movimentacoes) {
			if (movimentacao.getDataDaMovimentacao().getMes() == mes) {
				movimentacoesPeloMes.add(movimentacao);
			}
		}
		return movimentacoesPeloMes;
	}

	public ArrayList<Movimentacao> buscarMovimentacoesPorAno(int ano) {
		ArrayList<Movimentacao> movimentacoesPeloAno = new ArrayList<>();
		for (Movimentacao movimentacao : movimentacoes) {
			if (movimentacao.getDataDaMovimentacao().getAno() == ano) {
				movimentacoesPeloAno.add(movimentacao);
			}
		}
		return movimentacoesPeloAno;
	}

	public int editarMovimentacao(ArrayList<Movimentacao> encontrados, int indice, String novoNome, String novaDescricao, Double novoValor, Data novaData) {
		Movimentacao movimentacaoParaEditar = encontrados.get(indice - 1);
		if (movimentacoes.contains(movimentacaoParaEditar)) {
			movimentacaoParaEditar.setNome(novoNome);
			movimentacaoParaEditar.setDescricao(novaDescricao);
			movimentacaoParaEditar.setValor(novoValor);
			movimentacaoParaEditar.setDataDaMovimentacao(novaData);
			salvarDados();
			return 1;
		}
		return 0;
	}

	public int excluirMovimentacao(ArrayList<Movimentacao> encontrados, int indice) {
		Movimentacao movimentacaoParaExcluir = encontrados.get(indice - 1);
		if (movimentacoes.remove(movimentacaoParaExcluir)) {
			salvarDados();
			return 1;
		}
		return 0;
	}

	public int editarFilme(
			ArrayList<Filme> encontrados,
			int indice,
			String novoNome,
			Data novaData,
			String novoGenero,
			int novaQuantDvd,
			int novaQuantBlue,
			int novoTipoFilme
	) {
		Filme filmeParaEditar = encontrados.get(indice - 1);
		if (filmes.contains(filmeParaEditar)) {
			filmeParaEditar.setNome(novoNome);
			filmeParaEditar.setAnoDeLancamento(novaData);
			filmeParaEditar.setGenero(novoGenero);
			filmeParaEditar.setQuantDvd(novaQuantDvd);
			filmeParaEditar.setQuantBlueRay(novaQuantBlue);
			filmeParaEditar.setTipoDeFilme(novoTipoFilme);
			salvarDados();
			return 1;
		}
		return 0;
	}

	public int excluirFilme(ArrayList<Filme> encontrados, int indice) {
		Filme filmeParaExcluir = encontrados.get(indice - 1);
		if (filmes.remove(filmeParaExcluir)) {
			salvarDados();
			return 1;
		}
		return 0;
	}

	public int locarFilmeParaCliente(Cliente cliente, Filme filme, String formatoMidia, Dependente dependente) {
		if (cliente.getFilmesLocados().size() >= QUANT_MAX_FILME_CLIENTE) {
			return Constantes.RESULT_ERRO_EXCEDE_MAX_FILMES;
		}

		if (cliente.getMulta()>0){
			return Constantes.RESULT_ERRO_CLIENTE_COM_MULTA;
		}

		if (formatoMidia.equalsIgnoreCase("DVD") && filme.getQuantDvd() > 0) {
			filme.decrementarQtdDvd();
		} else if (formatoMidia.equalsIgnoreCase("Blu-ray") && filme.getQuantBlueRay() > 0) {
			filme.decrementarQtdBlueRay();
		} else {
			return Constantes.RESULT_ERRO;
		}

		Data dataAtual = obterDataAtual();
		Data dataDevolucao = Util.calcularDataDevolucao(dataAtual, PRAZO_DEVOLUCAO);
		Locacao locacao = new Locacao(filme, dataAtual, dataDevolucao, formatoMidia, dependente);
		cliente.getFilmesLocados().add(locacao);
		salvarDados();

		double valorLocacao = calcularValorLocacao(filme);
		cadastrarEntrada("Locação de Filme", "Locação do filme " + filme.getNome(), valorLocacao, dataAtual);

		return Constantes.RESULT_OK;
	}

	private double calcularValorLocacao(Filme filme) {
		return switch (filme.getTipoDeFilme()) {
			case Filme.TIPO_LANCAMENTO -> VALOR_LOCACAO_LANCAMENTO;
			case Filme.TIPO_NOVO -> VALOR_LOCACAO_NOVO;
			case Filme.TIPO_ANTIGO -> VALOR_LOCACAO_ANTIGO;
			default -> VALOR_LOC_DIA;
		};
	}

	public int editarCliente(Cliente clienteParaEditar, String novoNome, String novoEndereco, String novoCPF, Data novaData, int novoCodigo, Double novaMulta) {
		if (clientes.contains(clienteParaEditar)) {
			clienteParaEditar.setNome(novoNome);
			clienteParaEditar.setEndereco(novoEndereco);
			clienteParaEditar.setCpf(novoCPF);
			clienteParaEditar.setDataNascimento(novaData);
			clienteParaEditar.setCodigo(novoCodigo);
			clienteParaEditar.setMulta(novaMulta);
			salvarDados();
			return 1;
		}
		return 0;
	}

	public void devolverFilme(Cliente cliente, Filme filme, String formatoMidia) {
		if (formatoMidia.equalsIgnoreCase("DVD")) {
			filme.addQuantDvd(1);
		} else if (formatoMidia.equalsIgnoreCase("Blu-ray")) {
			filme.addQuantBlueRay(1);
		}

		Locacao locacaoToRemove = null;
		for (Locacao loc : cliente.getFilmesLocados()) {
			if (loc.getFilme().equals(filme) && loc.getFormatoMidia().equalsIgnoreCase(formatoMidia)) {
				locacaoToRemove = loc;
				break;
			}
		}
		if (locacaoToRemove != null) {
			cliente.getFilmesLocados().remove(locacaoToRemove);
		}
		salvarDados();
	}

	public void multarCliente(Cliente cliente, int diasAtraso) {
		double multaTotal = diasAtraso * VALOR_MULTA_DIA;
		cliente.addMulta(multaTotal);
		salvarDados();

		Data dataAtual = Util.obterDataAtual();
		cadastrarEntrada("Multa por Atraso", "Multa aplicada ao cliente " + cliente.getNome(), multaTotal, dataAtual);
	}

	public void pagarMulta(Cliente cliente) {
		double valorMulta = cliente.getMulta();
		if (valorMulta > 0) {
			cliente.setMulta(0.0);
			salvarDados();
			// Registrar a entrada financeira pelo pagamento da multa com descrição "Multa Paga"
			Data dataAtual = Util.obterDataAtual();
			cadastrarEntrada("Multa Paga", "Multa paga pelo cliente " + cliente.getNome(), valorMulta, dataAtual);
		}
	}


	public static Data obterDataAtual() {
		LocalDate hoje = LocalDate.now();
		return new Data(hoje.getDayOfMonth(), hoje.getMonthValue(), hoje.getYear());
	}
}
