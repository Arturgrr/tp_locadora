package br.ufop.trabalho.entities;
import br.ufop.trabalho.Util;
import java.io.Serializable;

/**
 * Classe para armazenar os dados de uma Pessoa. Como o sistema deverá controlar clientes e funcionários os dados comuns serão
 * armazenadona superClassePessoa.
 *
 * @author Filipe
 */
public class Pessoa implements Serializable{
	private static final long serialVersionUID = 1L;
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

	/**
	 * @author Artur Guerra
	 */
	public void setNome(String nome) {
		if (!Util.verificaStringPreenchida(nome)) {
			throw new IllegalArgumentException("Nome não pode ser vazio ou nulo.");
		}
		this.nome = nome;	}

	public String getEndereco() {
		return endereco;
	}

	/**
	 * @author Artur Guerra
	 */
	public void setEndereco(String endereco) {
		if (!Util.verificaStringPreenchida(endereco)) {
			throw new IllegalArgumentException("Endereço não pode ser vazio ou nulo.");
		}
		this.endereco = endereco;
	}

	public String getCpf() {
		return cpf;
	}

	/**
	 * @author Artur Guerra
	 */
	public void setCpf(String cpf) {
		if (!Util.validarCpf(Util.limparCpf(cpf))) {
			System.out.println("O CPF informado é inválido.");
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
