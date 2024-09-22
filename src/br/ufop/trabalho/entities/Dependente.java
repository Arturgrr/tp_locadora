package br.ufop.trabalho.entities;

/**
 * @author Artur Guerra
 */
public class Dependente extends Pessoa {

    private Cliente cliente;

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    /**
     * @author Artur Guerra
     */
    public Dependente(String nome, String endereco, String cpf, Data dataNascimento, Cliente cliente) {
        super(nome, endereco, cpf, dataNascimento);
        setCliente(cliente);
    }
}
