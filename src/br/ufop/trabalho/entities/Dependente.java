package br.ufop.trabalho.entities;

public class Dependente extends Pessoa {
    private static final long serialVersionUID = 1L;
    private Cliente cliente;

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Dependente(String nome, String endereco, String cpf, Data dataNascimento, Cliente cliente) {
        super(nome, endereco, cpf, dataNascimento);
        setCliente(cliente);
    }
}
