package br.ufop.trabalho.controle;

import java.util.ArrayList;

import br.ufop.trabalho.Util;
import br.ufop.trabalho.entities.Cliente;


/***
 * Classe de regra de negócios da aplicação. Esta classe deverá controlar todos os dados que serão armazenados no sistema (clientes, filmes e a parte financeira).
 *  Ela é a porta de entrada para acesso a todos os dados e deverá também fazer as verificações necessárias no momento dos cadastros. 
 * É necessário perceber a importância dessa classe que implementa todas as regras de negócio da aplicação. A classe Controle poderá
 * ser utilizada para qualquer tipo de interface gráfica inclusive com janelas.
 * IMPORTANTE: ESTA CLASSE NAO DEVE TER ENTRADA E SAÍDA DE DADOS PARA O USUÁRIO
 *
 * @author Filipe
 */
public class Controle {
	private final ArrayList <Cliente> clientes;

	public Controle(){
		clientes = new ArrayList<Cliente>();
	}
	
	public int addCliente(String nome, String end, int codigo){
		/*
		  Para cada uma das verificações abaixo o método irá retornar um inteiro indicando um erro
		  caso ele aconteça. Caso nenhum dado inválido seja digitado o cadastro será realizado
		  e será retornado um inteiro indicando que o cadastro foi realizado corretamente
		 */

		if(!Util.verificaListaStringPreenchida(nome, end)){
			return Constantes.ERRO_CAMPO_VAZIO;
		}

		Cliente cliente = new Cliente(nome, end, codigo);
		this.clientes.add(cliente);
		
		return Constantes.RESULT_OK;
	}

	/**
	 * Método que retornar a quantidade de clientes cadastrados
	 */
	public int getQtdClientes(){
		return clientes.size();
	}

	/**
	 * Método para retornar um cliente em uma determinada posição. É importante que as classes de interface gráfica não tenham
	 * acesso a uma referencia do array utilizado para armazenar todos os clientes
	 * @param pos Inteiro que indica qual posição deseja retornar o cliente
	 * @return Dados do cliente ou null caso não exista
	 */
	public Cliente getClienteNaPosicao(int pos){
		if(pos >=0 && pos < getQtdClientes()){
			return clientes.get(pos);
		}
		return null;
	}
	
	
}
