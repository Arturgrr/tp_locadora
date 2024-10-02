package br.ufop.trabalho.entities;

import java.util.ArrayList;

/**
 * @author Artur Guerra
 * @author Iaggo Rauta
 */
public class Cliente extends Pessoa {

    private int codigo;
    private double multa;
    private final ArrayList<Filme> filmes;
    private final ArrayList<Dependente> dependentes;

    /**
     * Construtor
     *
     * @param nome     String com o Nome do usuário
     * @param endereco String com o Endereço do usuário
     * @param codigo   Inteiro com o código do usuário
     * @author Artur Guerra
     */
    public Cliente(String nome, String endereco, String cpf, Data dataDeNascimento, int codigo) {
        super(nome, endereco, cpf, dataDeNascimento);
        setCodigo(codigo);
        this.multa = 0.0;
        this.filmes = new ArrayList<Filme>();
        this.dependentes = new ArrayList<Dependente>(3);
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public ArrayList<Filme> getFilmes() {
        return filmes;
    }

    public void addFilme(Filme filme) {
        filmes.add(filme);
    }

    public void removeFilme(Filme filme) {
        filmes.remove(filme);
    }

    public ArrayList<Dependente> getDependentes() {
        return dependentes;
    }

    /**
     * @author Artur Guerra
     */
    public void addDependentes(Dependente dependente) {
        if (this.dependentes.size() >= 3) {
            throw new IllegalArgumentException(
                    "O cliente já possui 3 dependentes cadastrados. Não é possível adicionar: " + dependente.getNome());
        }
        this.dependentes.add(dependente);
    }

    /**
     * @author Artur Guerra
     */
    public void removeDependentes(Dependente dependente) {
        if (!this.dependentes.contains(dependente)) {
            throw new IllegalStateException("Dependente " + dependente.getNome() + " não encontrado para remoção.");
        }
        this.dependentes.remove(dependente);
    }

    public double getMulta() {
        return multa;
    }

    /**
     * @author Artur Guerra
     * @author Iaggo Rauta
     */
    public void setMulta(double multa) {
        if (multa < 0) {
            throw new IllegalArgumentException("Multa não pode ser negativa.");
        }
        this.multa = multa;
    }

    public void addMulta(double multa) {
        this.multa += multa;
    }

    /**
     * Método para tornar o nome da pessoa acrescido de seu código de cliente
     *
     * @return String no padrão: {Nome} - {Código}
     * @author Artur Guerra
     */
    @Override
    public String toString() {
        return getNome() + " - " + getCodigo();
    }
}
