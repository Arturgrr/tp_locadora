package br.ufop.trabalho.entities;

import java.util.ArrayList;

public class Cliente extends Pessoa {

    private int codigo;
    private ArrayList<Filme> filmes;


    /**
     * Construtor
     *
     * @param nome String com o Nome do usuário
     * @param endereco String com o Endereço do usuário
     * @param codigo Inteiro com o código do usuário
     */
    public Cliente(String nome, String endereco, String cpf, Data dataDeNascimento, int codigo) {
        super(nome, endereco, cpf, dataDeNascimento);
        this.codigo = codigo;
        filmes = new ArrayList<Filme>();
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
