package br.ufop.trabalho.entities;
import java.io.Serializable;

import java.util.ArrayList;

/**
 * @author Artur Guerra
 * @author Iaggo Rauta
 */
public class Cliente extends Pessoa {
    private static final long serialVersionUID = 1L;
    
    private int codigo;
    private double multa;
    private final ArrayList<Filme> filmes;
    public ArrayList<Dependente> dependentes;
    private boolean temdependente = false;
    private ArrayList<Filme> filmesLocados; // Lista para armazenar os filmes locados

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
        this.filmesLocados = new ArrayList<>(); // Inicializa a lista
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
        this.temdependente = true;
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
    public boolean temDependentes(){
        return temdependente;
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

    public ArrayList<Filme> getFilmesLocados() {
        return filmesLocados; // Retorna a lista de filmes locados
    }

    public void adicionarFilmeLocado(Filme filme, Cliente cliente) {
        this.filmesLocados.add(filme); // Adiciona o filme à lista de filmes locados
    }

}
