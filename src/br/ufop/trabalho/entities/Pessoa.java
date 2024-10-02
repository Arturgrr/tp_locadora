package br.ufop.trabalho.entities;

import java.io.Serializable;
import br.ufop.trabalho.Util;

public class Pessoa implements Serializable {
	private static final long serialVersionUID = 1L;
	private String nome;
	private String endereco;
	private String cpf;
	private Data dataNascimento;

	public Pessoa(String nome, String endereco, String cpf, Data dataNascimento) {
		setNome(nome);
		setEndereco(endereco);
		if (!setCpf(cpf)) {
			throw new IllegalArgumentException("O CPF informado é inválido.");
		}
		setDataNascimento(dataNascimento);
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		if (!Util.verificaStringPreenchida(nome)) {
			throw new IllegalArgumentException("Nome não pode ser vazio ou nulo.");
		}
		this.nome = nome;
	}

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

	public boolean setCpf(String cpf) {
		cpf = Util.limparCpf(cpf);
		if (Util.validarCpf(cpf)) {
			this.cpf = cpf;
			return true;
		} else {
			return false;
		}
	}

	public Data getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Data dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
}
