package br.ufop.trabalho.controle;

import java.util.ArrayList;
import java.util.Comparator;

import br.ufop.trabalho.Util;
import br.ufop.trabalho.entities.*;

/***
 * Classe de regra de negócios da aplicação. Esta classe deverá controlar todos
 * os dados que serão armazenados no sistema (clientes, filmes e a parte
 * financeira).
 * Ela é a porta de entrada para acesso a todos os dados e deverá também fazer
 * as verificações necessárias no momento dos cadastros.
 * É necessário perceber a importância dessa classe que implementa todas as
 * regras de negócio da aplicação. A classe Controle poderá
 * ser utilizada para qualquer tipo de interface gráfica inclusive com janelas.
 * IMPORTANTE: ESTA CLASSE NAO DEVE TER ENTRADA E SAÍDA DE DADOS PARA O USUÁRIO
 */
public class Controle {

	private final ArrayList<Cliente> clientes;
	private final ArrayList<Filme> filmes;
	private final ArrayList<Movimentacao> movimentacoes;

	public Controle() {
		clientes = new ArrayList<Cliente>();
		filmes = new ArrayList<Filme>();
		movimentacoes = new ArrayList<Movimentacao>();

	}

	/**
	 * Função que adiciona clientes no controle.
	 *
	 * @param nome   Nome do cliente a ser adicionado
	 * @param end    Endereço do cliente
	 * @param cpf    String contendo apenas números do cpf do cliente
	 * @param dataDN Data de nascimento do cliente
	 * @param codigo codigo do cliente
	 * @return inteiro referenciado na constantes
	 */
	public int addCliente(String nome, String end, String cpf, Data dataDN, int codigo) {
		if (!Util.verificaListaStringPreenchida(nome, end, cpf)) {
			return Constantes.ERRO_CAMPO_VAZIO;
		}

		Cliente cliente = new Cliente(nome, end, cpf, dataDN, codigo);
		this.clientes.add(cliente);

		return Constantes.RESULT_OK;
	}

	// @author iaggo rauta
	// metodos para a classe relatorio
	public ArrayList<Cliente> getClientes() {
		return clientes;
	}

	public ArrayList<Filme> getFilmes() {
		return filmes;
	}

	// Relatório de todos os clientes cadastrados
	public ArrayList<Cliente> relatorioClientes() {
		return new ArrayList<>(clientes); // Retorna uma cópia da lista de clientes
	}

	// Relatório de filmes por gênero
	public ArrayList<Filme> relatorioFilmesPorGenero(String genero) {
		return buscarFilmesPorGenero(genero); // Usa o método existente para buscar por gênero
	}

	// Relatório de filmes por ano de lançamento
	public ArrayList<Filme> relatorioFilmesPorAno(int ano) {
		ArrayList<Filme> filmesPorAno = new ArrayList<>();
		for (Filme filme : filmes) {
			if (filme.getDataDaMovimentacao.getAno() == ano) { // Supondo que Data tenha um método getAno()
				filmesPorAno.add(filme);
			}
		}
		return filmesPorAno;
	}

	// Relatório de filmes por nome (ordem alfabética)
	public ArrayList<Filme> relatorioFilmesPorNome() {
		ArrayList<Filme> filmesOrdenados = new ArrayList<>(filmes);
		filmesOrdenados.sort(Comparator.comparing(Filme::getNome)); // Ordena pela comparação de nomes
		return filmesOrdenados;
	}

	/**
	 * Método que retornar a quantidade de clientes cadastrados.
	 *
	 * @return Inteiro com o número de clientes
	 */
	public int getQtdClientes() {
		return clientes.size();
	}

	/**
	 * Método para retornar um cliente em uma determinada posição. É importante que
	 * as classes de interface gráfica não tenham
	 * acesso a uma referência do array utilizado para armazenar todos os clientes.
	 *
	 * @param pos Inteiro que indica qual posição deseja retornar o cliente
	 * @return Dados do cliente ou null caso não exista
	 * @author Iaggo Rauta
	 */
	public Cliente getClienteNaPosicao(int pos) {
		if (pos < 0 || pos >= getQtdClientes()) {
			throw new IndexOutOfBoundsException("Posição inválida: " + pos);
		}
		return clientes.get(pos);
	}

	/**
	 * Função que adiciona filme no controle.
	 *
	 * @param nome             Nome do filme a ser adicionado
	 * @param dataDeLancamento Data de lançamento do filme
	 * @param genero           Gênero do filme
	 * @param quantDvd         Quantidade de dvd disponíveis desse filme
	 * @param quantBR          Quantidade de BlueRay disponíveis desse filme
	 * @return inteiro referenciado na constantes
	 * @author Artur Guerra
	 */
	public int addFilme(String nome, Data dataDeLancamento, String genero, int quantDvd, int quantBR) {
		if (!Util.verificaListaStringPreenchida(nome, genero)) {
			return Constantes.ERRO_CAMPO_VAZIO;
		}

		Filme filme = new Filme(nome, dataDeLancamento, genero, quantDvd, quantBR);
		this.filmes.add(filme);
		return Constantes.RESULT_OK;
	}

	/**
	 * Método que retorna a quantidade de filmes cadastrados.
	 *
	 * @return Inteiro com o número de filmes
	 * @author Artur Guerra
	 */
	public int getQtdFilmes() {
		return filmes.size();
	}

	/**
	 * Método para retornar um filme em uma determinada posição. É importante que as
	 * classes de interface gráfica não tenham
	 * acesso a uma referência do array utilizado para armazenar todos os clientes.
	 *
	 * @param pos Inteiro que indica qual posição deseja retornar o filme
	 * @return Dados do filme ou null caso não exista
	 * @author Artur Guerra
	 */
	public Filme getFilmeNaPosicao(int pos) {
		if (pos < 0 || pos >= getQtdFilmes()) {
			throw new IndexOutOfBoundsException("Posição inválida: " + pos);
		}
		return filmes.get(pos);
	}

	/**
	 * Método para buscar nome do filme e encontrar dados
	 *
	 * @param nome Nome a ser procurado pelo controle
	 * @return Array com todos os filmes encontrados no controle
	 * @author Artur Guerra
	 */
	public ArrayList<Filme> buscarFilmesPorNome(String nome) {
		ArrayList<Filme> filmesEncontrados = new ArrayList<>();
		for (Filme filme : filmes) {
			if (filme.getNome().equalsIgnoreCase(nome)) {
				filmesEncontrados.add(filme);
			}
		}
		return filmesEncontrados;
	}

	/**
	 * Método para buscar filmes com base no seu gênero
	 *
	 * @param genero Genero a ser pesquisado pelo controle
	 * @return Array com todos os filmes encontrados no controle
	 * @author Artur Guerra
	 */
	public ArrayList<Filme> buscarFilmesPorGenero(String genero) {
		ArrayList<Filme> filmesEncontrados = new ArrayList<>();
		for (Filme filme : filmes) {
			if (filme.getGenero().equalsIgnoreCase(genero)) {
				filmesEncontrados.add(filme);
			}
		}
		return filmesEncontrados;
	}

	/**
	 * Método para buscar filmes através de sua disponibilidade
	 *
	 * @return Array com todos os filmes que possuem o genêro
	 * @author Artur Guerra
	 */
	public ArrayList<Filme> buscarFilmesDisponiveis() {
		ArrayList<Filme> filmesDisponiveis = new ArrayList<>();
		for (Filme filme : filmes) {
			if (filme.getQuantDvd() > 0 || filme.getQuantBlueRay() > 0) {
				filmesDisponiveis.add(filme);
			}
		}
		return filmesDisponiveis;
	}

	/**
	 * @author Artur Guerra
	 */
	public void excluirFilme(Filme filme) {
		filmes.remove(filme);
	}

	/**
	 * Método que busca todos os clientes dentro do controle e retorna o próprio
	 * cliente
	 *
	 * @param nome String contendo o nome do cliente
	 * @return Array com todos os clientes encontrados
	 * @author Artur Guerra
	 * @author Iaggo Rauta
	 */
	public ArrayList<Cliente> buscarClientePorNome(String nome) {
		ArrayList<Cliente> clientesPeloNome = new ArrayList<>();
		for (Cliente cliente : clientes) {
			if (cliente.getNome().equalsIgnoreCase(nome)) {
				clientesPeloNome.add(cliente);
			}
		}
		return clientesPeloNome;
	}

	public ArrayList<Cliente> buscarClientePorCodigo(int codigo) {
		ArrayList<Cliente> clientesPeloCodigo = new ArrayList<>();
		for (Cliente cliente : clientes) {
			if (cliente.getCodigo() == codigo) {
				clientesPeloCodigo.add(cliente);
			}
		}
		return clientesPeloCodigo;
	}

	/**
	 * Método para buscar dependentes dentro do controle pelo nome
	 *
	 * @param nome String contendo o nome dos dependentes
	 * @return Array com todos os dependentes encontrados
	 * @author Artur Guerra
	 */
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

	/**
	 * Método para buscar Clientes e Dependentes pelo seu nome
	 *
	 * @param nome String contendo o nome da pessoa
	 * @return Array com todas as pessoas (Clientes e Dependentes)
	 * @author Artur Guerra
	 */
	public ArrayList<Pessoa> buscarPessoaPorNome(String nome) {
		ArrayList<Cliente> clientesPeloNome = buscarClientePorNome(nome);
		ArrayList<Dependente> dependentesPeloNome = buscarDependentePorNome(nome);

		ArrayList<Pessoa> pessoasPeloNome = new ArrayList<>();

		pessoasPeloNome.addAll(clientesPeloNome);
		pessoasPeloNome.addAll(dependentesPeloNome);

		return pessoasPeloNome;
	}

	/**
	 * Método para buscar cliente pelo nome de seu dependente
	 *
	 * @param nome Srting contendo o nome do dependente
	 * @return Array com todos os dependentes encontrados
	 * @author Artur Guerra
	 */
	public ArrayList<Cliente> buscarClientePeloNomeDoDepentende(String nome) {
		ArrayList<Cliente> clientesEncontrados = new ArrayList<>();
		for (Cliente cliente : clientes) {
			for (Dependente dep : cliente.getDependentes()) {
				if (dep.getNome().equalsIgnoreCase(nome)) {
					clientesEncontrados.add(cliente);
				}
			}
		}

		return clientesEncontrados;
	}

	/**
	 * PARTE 2
	 * Método cadastrar entradas do balancete
	 * 
	 * @author João Teixeira
	 */

	public void cadastrarEntrada(String nome, String descricao, Double valor, Data data) {
		int tipo = 1;
		Entrada entrada = new Entrada(nome, descricao, valor, data, tipo);
		this.movimentacoes.add(entrada);

	}

	/**
	 * PARTE 2
	 * Método cadastrar saidas do balancete
	 * 
	 * @author João Teixeira
	 */
	public void cadastrarSaida(String nome, String descricao, Double valor, Data data) {
		int tipo = 0;
		Saida saida = new Saida(nome, descricao, valor, data, tipo);
		this.movimentacoes.add(saida);

	}

	/**
	 * PARTE 2
	 * Método buscar movimentacoes por nome
	 * 
	 * @author João Teixeira
	 */
	public ArrayList<Movimentacao> buscarMovimentacoes(String nome) {
		ArrayList<Movimentacao> movimentacoesPeloNome = new ArrayList<>();
		for (Movimentacao movimentacao : movimentacoes) {
			if (movimentacao.getNome().equalsIgnoreCase(nome)) {
				movimentacoesPeloNome.add(movimentacao);
			}
		}
		return movimentacoesPeloNome;

	}

	/**
	 * PARTE 2
	 * Método buscar movimentacoes por mes
	 * 
	 * @author João Teixeira
	 */
	public ArrayList<Movimentacao> buscarMovimentacoesPorMes(int mes) {
		ArrayList<Movimentacao> movimentacoesPeloMes = new ArrayList<>();
		for (Movimentacao movimentacao : movimentacoes) {

			if ((movimentacao.getDataDaMovimentacao().getMes()) == mes) {
				movimentacoesPeloMes.add(movimentacao);
			}
		}
		return movimentacoesPeloMes;

	}

	/**
	 * PARTE 2
	 * Método buscar movimentacoes por Ano
	 * 
	 * @author João Teixeira
	 */
	public ArrayList<Movimentacao> buscarMovimentacoesPorAno(int ano) {
		ArrayList<Movimentacao> movimentacoesPeloAno = new ArrayList<>();
		for (Movimentacao movimentacao : movimentacoes) {

			if ((movimentacao.getDataDaMovimentacao().getAno()) == ano) {
				movimentacoesPeloAno.add(movimentacao);
			}
		}
		return movimentacoesPeloAno;

	}

	/**
	 * PARTE 2
	 * Método editar movimentacao
	 * 
	 * @author João Teixeira
	 */
	public int editarMovimentacao(ArrayList<Movimentacao> encontrados, int indice, String novoNome,
			String novaDescricao, Double novoValor, Data novaData) {
		int flag = 0;

		Movimentacao movimentacaoParaEditar = encontrados.get(indice - 1);

		// Procurar na lista original de movimentações
		for (Movimentacao movimentacao : movimentacoes) {
			if (movimentacao.equals(movimentacaoParaEditar)) {
				// edição dos atributos da movi
				movimentacao.setNome(novoNome);

				movimentacao.setDescricao(novaDescricao);

				movimentacao.setValor(novoValor);

				movimentacao.setDataDaMovimentacao(novaData);
				flag = 1;
			} else {
				flag = 0;
			}

		}

		return flag;
	}

	/**
	 * PARTE 2
	 * Método excluir movimentacao
	 * 
	 * @author João Teixeira
	 */
	public int excluirMovimentacao(ArrayList<Movimentacao> encontrados, int indice) {
		int flag = 0;

		Movimentacao movimentacaoParaExcluir = encontrados.get(indice - 1);

		if (movimentacoes.remove(movimentacaoParaExcluir)) {
			flag = 1;
		} else {
			flag = 0;
		}

		return flag;
	}

	/**
	 * PARTE 2
	 * Método editar filme
	 * 
	 * @author João Teixeira
	 */
	public int editarFilme(ArrayList<Filme> encontrados, int indice, String novoNome, Data novaData, String novoGenero, int novaQuantDvd, int novaQuantBlue) {
		int flag = 0;

		Filme filmeParaEditar = encontrados.get(indice - 1);

		// Procurar na lista original
		for (Filme filme : filmes) {
			if (filme.equals(filmeParaEditar)) {
				// edição dos atributos da movi
				filme.setNome(novoNome);
				filme.setAnoDeLancamento(novaData);
				filme.setGenero(novoGenero);
				filme.setQuantDvd(novaQuantDvd);
				filme.setQuantBlueRay(novaQuantBlue);
				flag = 1;
			} else {
				flag = 0;
			}

		}

		return flag;
	}
	/**
	 * PARTE 2
	 * Método excluir Filme
	 * 
	 * @author João Teixeira
	 */
	public int excluirFilme(ArrayList<Filme> encontrados, int indice) {
		int flag = 0;

		Filme filmeParaExcluir = encontrados.get(indice - 1);

		if (filmes.remove(filmeParaExcluir)) {
			flag = 1;
		} else {
			flag = 0;
		}

		return flag;
	}





	/**
	 * 
	 * 
	 * @author Iaggo Rauta
	 */
	// metodos para locarfilme
	public boolean filmeDisponivel(String nomeFilme) {
		Filme filme = buscarFilmePorNome(nomeFilme);
		return filme != null && filme.getDisponibilidade() > 0;
	}

	// Método para locar o filme para o cliente ou dependente
	public boolean locarFilmeParaCliente(Cliente cliente, String nomeFilme, String formatoMidia, Cliente dependente) {
		Filme filme = buscarFilmePorNome(nomeFilme);
		if (filme == null)
			return false; // Se o filme não for encontrado, retorna falso

		// Verificar disponibilidade no formato escolhido
		if (formatoMidia.equalsIgnoreCase("DVD") && filme.getQuantDvd() > 0) {
			filme.decrementarQtdDvd(); // Decrementa a quantidade de DVDs disponíveis
		} else if (formatoMidia.equalsIgnoreCase("Blu-ray") && filme.getQuantBlueRay() > 0) {
			filme.decrementarQtdBlueRay(); // Decrementa a quantidade de Blu-rays disponíveis
		} else {
			return false; // Formato não disponível
		}

		// Registrar a locação na conta do cliente ou dependente
		if (dependente != null) {
			cliente.adicionarFilmeLocado(filme, dependente); // Locação feita pelo dependente
		} else {
			cliente.adicionarFilmeLocado(filme, cliente); // Locação feita pelo cliente principal
		}
		return true; // Filme locado com sucesso
	}

	public Filme buscarFilmePorNome(String nomeFilme) {
		for (Filme filme : filmes) {
			if (filme.getNome().equalsIgnoreCase(nomeFilme)) {
				return filme;
			}
		}
		return null; // Se não encontrar o filme, retorna null
	}



	public int locarFilme(Cliente cliente, Filme filme) {
        if (filme.getQuantDvd() > 0) {
            filme.setQuantDvd(filme.getQuantDvd() - 1);
            cliente.addFilme(filme);
            return Constantes.RESULT_OK;
        } else if (filme.getQuantBlueRay() > 0) {
            filme.setQuantBlueRay((filme.getQuantBlueRay() - 1));
            cliente.addFilme(filme);
            return Constantes.RESULT_OK;
        } else {
            return Constantes.RESULT_ERRO;
        }
    }

}