package br.ufop.trabalho.entities;

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

	public Pessoa(String nome, String endereco) {
		setNome(nome);
		setEndereco(endereco);
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getCpf() {
		return cpf;
	}

	public Boolean setCpf(String cpf) {
		if(validarCpf(limparCpf(cpf))){
			this.cpf = cpf;
		} else {
			return false;
		}
		return true;
	}

	public static boolean validarCpf(String cpf) {
		return cpf != null && cpf.length() == 11;
    }

	public static String limparCpf(String cpf) {
		return cpf.replaceAll("\\D", "");
	}

	public Data getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Data dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

}
