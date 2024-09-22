package br.ufop.trabalho.controle;

import java.util.ArrayList;

import br.ufop.trabalho.Util;
import br.ufop.trabalho.entities.Cliente;
import br.ufop.trabalho.entities.Data;
import br.ufop.trabalho.entities.Filme;


/***
 * Classe de regra de negócios da aplicação. Esta classe deverá controlar todos os dados que serão armazenados no sistema (clientes, filmes e a parte financeira).
 *  Ela é a porta de entrada para acesso a todos os dados e deverá também fazer as verificações necessárias no momento dos cadastros. 
 * É necessário perceber a importância dessa classe que implementa todas as regras de negócio da aplicação. A classe Controle poderá
 * ser utilizada para qualquer tipo de interface gráfica inclusive com janelas.
 * IMPORTANTE: ESTA CLASSE NAO DEVE TER ENTRADA E SAÍDA DE DADOS PARA O USUÁRIO
 */
public class Controle {

	private final ArrayList <Cliente> clientes;
	private final ArrayList <Filme> filmes;

	public Controle(){
		clientes = new ArrayList<Cliente>();
		filmes = new ArrayList<Filme>();
	}

	/**
	 * Função que adiciona clientes no controle.
	 *
	 * @param nome Nome do cliente a ser adicionado
	 * @param end Endereço do cliente
	 * @param cpf String contendo apenas números do cpf do cliente
	 * @param dataDN Data de nascimento do cliente
	 * @param codigo codigo do cliente
	 * @return inteiro referenciado na constantes
	 */
	public int addCliente(String nome, String end, String cpf, Data dataDN, int codigo){
		if(!Util.verificaListaStringPreenchida(nome, end, cpf)){
			return Constantes.ERRO_CAMPO_VAZIO;
		}

		Cliente cliente = new Cliente(nome, end, cpf, dataDN, codigo);
		this.clientes.add(cliente);

		return Constantes.RESULT_OK;
	}

	/**
	 * Método que retornar a quantidade de clientes cadastrados.
	 *
	 * @return Inteiro com o número de clientes
	 */
	public int getQtdClientes(){
		return clientes.size();
	}

	/**
	 * Método para retornar um cliente em uma determinada posição. É importante que as classes de interface gráfica não tenham
	 * acesso a uma referência do array utilizado para armazenar todos os clientes.
	 *
	 * @param pos Inteiro que indica qual posição deseja retornar o cliente
	 * @return Dados do cliente ou null caso não exista
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
	 * @param nome Nome do filme a ser adicionado
	 * @param dataDeLancamento Data de lançamento do filme
	 * @param genero Gênero do filme
	 * @param quantDvd Quantidade de dvd disponíveis desse filme
	 * @param quantBR Quantidade de BlueRay disponíveis desse filme
	 * @return inteiro referenciado na constantes
	 * @author Artur Guerra
	 */
	public int addFilme(String nome, Data dataDeLancamento, String genero, int quantDvd, int quantBR){
		if(!Util.verificaListaStringPreenchida(nome, genero)){
			return Constantes.ERRO_CAMPO_VAZIO;
		}

		Filme filme = new Filme(nome, dataDeLancamento, genero, quantDvd, quantBR);
		this.filmes.add(filme);
		return Constantes.RESULT_OK;
	}

	/**
	 * Método que retornar a quantidade de filmes cadastrados.
	 *
	 * @return Inteiro com o número de filmes
	 * @author Artur Guerra
	 */
	public int getQtdFilmes() {
		return filmes.size();
	}

	/**
	 * Método para retornar um filme em uma determinada posição. É importante que as classes de interface gráfica não tenham
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
	public void excluirFilme (Filme filme) {
		filmes.remove(filme);
	}
}
