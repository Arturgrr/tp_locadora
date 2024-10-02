package br.ufop.trabalho.entities;
import java.io.Serializable;
/**
 * @author Artur Guerra
 */
public class Dependente extends Pessoa {
    private static final long serialVersionUID = 1L;
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
