package br.ufop.trabalho.entities;

import java.util.ArrayList;

public class Cliente extends Pessoa {

    private int codigo;
    // Um cliente terá um array de filmes que armazenarão os filmes por ele locados
    private ArrayList<Filme> filmes;

    //Um cliente também terá um array de dependentes
	// Private ArrayList<Dependente> dependentes;

    /**
     * Construtor
     *
     * @param nome
     * @param endereco
     * @param codigo
     */
    public Cliente(String nome, String endereco, int codigo) {
        super(nome, endereco);
        this.codigo = codigo;
        //Instanciação do Array List de filmes
        filmes = new ArrayList<Filme>();
    }

    /**
     * Construtor vazio criado apenas para a geração do código inicial DE EXEMPLO pelo professor.
     * Pode ser utilizado, mas é importante lembrar de preencher os campos do objeto para não ocorrer
     * o NullPointerException
     */
    public Cliente() {
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

    @Override
    public String toString() {
        return getNome();
    }


}
