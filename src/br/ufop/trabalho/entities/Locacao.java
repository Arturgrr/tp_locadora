package br.ufop.trabalho.entities;

import java.io.Serial;
import java.io.Serializable;

public class Locacao implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private final Filme filme;
    private final Data dataLocacao;
    private final Data dataDevolucaoPrevista;
    private Data dataDevolucaoReal;
    private final String formatoMidia;
    private final Dependente dependente;

    public Locacao(Filme filme, Data dataLocacao, Data dataDevolucaoPrevista, String formatoMidia, Dependente dependente) {
        this.filme = filme;
        this.dataLocacao = dataLocacao;
        this.dataDevolucaoPrevista = dataDevolucaoPrevista;
        this.formatoMidia = formatoMidia;
        this.dependente = dependente;
    }

    public Filme getFilme() {
        return filme;
    }

    public Data getDataLocacao() {
        return dataLocacao;
    }

    public Data getDataDevolucaoPrevista() {
        return dataDevolucaoPrevista;
    }

    public Data getDataDevolucaoReal() {
        return dataDevolucaoReal;
    }

    public void setDataDevolucaoReal(Data dataDevolucaoReal) {
        this.dataDevolucaoReal = dataDevolucaoReal;
    }

    public String getFormatoMidia() {
        return formatoMidia;
    }

    public Dependente getDependente() {
        return dependente;
    }
}
