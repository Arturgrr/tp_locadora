package br.ufop.trabalho.entities;

import java.util.ArrayList;

public class Cliente extends Pessoa {

    private int codigo;
    private double multa;
    private ArrayList<Filme> filmes;
    private ArrayList<Dependente> dependentes;

    /**
     * Construtor
     *
     * @param nome String com o Nome do usuário
     * @param endereco String com o Endereço do usuário
     * @param codigo Inteiro com o código do usuário
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

    public void setFilmes(ArrayList<Filme> filmes) {
        this.filmes = filmes;
    }

    public void addFilme(Filme filme) {
        filmes.add(filme);
    }

    public void removeFilme(Filme filme) {
        filmes.remove(filme);
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

    public ArrayList<Dependente> getDependentes() {
        return dependentes;
    }

    public void addDependentes(Dependente dependente) {
        if (this.dependentes.size() < 3) {
            this.dependentes.add(dependente);
        } else {
            System.out.println("Erro: Limite de 3 dependentes atingido, nenhum dependente foi adicionado.");
        }
    }

    public void removeDependentes(Dependente dependente) {
        this.dependentes.remove(dependente);
    }

    public double getMulta() {
        return multa;
    }

    public void setMulta(double multa) {
        this.multa = multa;
    }

    public void addMulta(double multa) {
        this.multa += multa;
    }
}
