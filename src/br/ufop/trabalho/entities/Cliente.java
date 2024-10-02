package br.ufop.trabalho.entities;

import java.util.ArrayList;

public class Cliente extends Pessoa {
    private static final long serialVersionUID = 1L;

    private int codigo;
    private double multa;
    private ArrayList<Locacao> filmesLocados;
    private ArrayList<Dependente> dependentes;

    public Cliente(String nome, String endereco, String cpf, Data dataDeNascimento, int codigo) {
        super(nome, endereco, cpf, dataDeNascimento);
        setCodigo(codigo);
        this.multa = 0.0;
        this.filmesLocados = new ArrayList<>();
        this.dependentes = new ArrayList<>(3);
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public ArrayList<Dependente> getDependentes() {
        return dependentes;
    }

    public void addDependentes(Dependente dependente) {
        if (this.dependentes.size() >= 3) {
            throw new IllegalArgumentException("O cliente já possui 3 dependentes.");
        }
        this.dependentes.add(dependente);
    }

    public void removeDependentes(Dependente dependente) {
        if (!this.dependentes.contains(dependente)) {
            throw new IllegalStateException("Dependente não encontrado.");
        }
        this.dependentes.remove(dependente);
    }

    public double getMulta() {
        return multa;
    }

    public void setMulta(double multa) {
        if (multa < 0) {
            throw new IllegalArgumentException("Multa não pode ser negativa.");
        }
        this.multa = multa;
    }

    public void addMulta(double multa) {
        this.multa += multa;
    }

    @Override
    public String toString() {
        return getNome() + " - Código: " + getCodigo();
    }

    public ArrayList<Locacao> getFilmesLocados() {
        return filmesLocados;
    }
}
