package br.ufop.trabalho.entities;

import br.ufop.trabalho.Util;

/**
 * Classe para armazenar os dados de uma Pessoa. Como o sistema deverá controlar clientes e funcionários os dados comuns serão
 * armazenadona superClassePessoa.
 *
 * @author Filipe
 */
public class Pessoa {
	
	private String nome;
	private String endereco;
	private String cpf;
	private Data dataNascimento;

	public Pessoa(String nome, String endereco, String cpf, Data dataNascimento) {
		setNome(nome);
		setEndereco(endereco);
		setCpf(cpf);
		setDataNascimento(dataNascimento);
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		if (!Util.verificaStringPreenchida(nome)) {
			throw new IllegalArgumentException("Nome não pode ser vazio ou nulo.");
		}
		this.nome = nome;	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		if (!Util.verificaStringPreenchida(endereco)) {
			throw new IllegalArgumentException("Endereço não pode ser vazio ou nulo.");
		}
		this.endereco = endereco;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		if (!Util.validarCpf(Util.limparCpf(cpf))) {
			throw new IllegalArgumentException("O CPF informado é inválido.");
		}
		this.cpf = Util.limparCpf(cpf);
	}

	public Data getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Data dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
}
