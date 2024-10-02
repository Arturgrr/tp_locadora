package br.ufop.trabalho.entities;

import java.io.Serializable;

public class Locacao implements Serializable {
    private static final long serialVersionUID = 1L;
    private Filme filme;
    private Data dataLocacao;
    private Data dataDevolucao;
    private String formatoMidia;
    private Dependente dependente;

    public Locacao(Filme filme, Data dataLocacao, Data dataDevolucao, String formatoMidia, Dependente dependente) {
        this.filme = filme;
        this.dataLocacao = dataLocacao;
        this.dataDevolucao = dataDevolucao;
        this.formatoMidia = formatoMidia;
        this.dependente = dependente;
    }

    public Filme getFilme() {
        return filme;
    }

    public Data getDataLocacao() {
        return dataLocacao;
    }

    public Data getdataDevolucao() {
        return dataDevolucao;
    }

    public String getFormatoMidia() {
        return formatoMidia;
    }

    public Dependente getDependente() {
        return dependente;
    }
}
