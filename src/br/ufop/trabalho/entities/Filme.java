package br.ufop.trabalho.entities;

import java.io.Serial;
import java.io.Serializable;

public class Filme implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    public static final int TIPO_LANCAMENTO = 1;
    public static final int TIPO_NOVO = 2;
    public static final int TIPO_ANTIGO = 3;

    private String nome;
    private Data anoDeLancamento;
    private String genero;
    private int quantDvd, quantBlueRay;
    private int tipoDeFilme;

    public Filme(String nome, Data anoDeLancamento, String genero, int quantDvd, int quantBlueRay, int tipoDeFilme) {
        setNome(nome);
        setAnoDeLancamento(anoDeLancamento);
        setGenero(genero);
        setQuantDvd(quantDvd);
        setQuantBlueRay(quantBlueRay);
        setTipoDeFilme(tipoDeFilme);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        if (nome == null || nome.isEmpty()) {
            throw new IllegalArgumentException("Nome do filme não pode ser vazio.");
        }
        this.nome = nome;
    }

    public Data getAnoDeLancamento() {
        return anoDeLancamento;
    }

    public void setAnoDeLancamento(Data anoDeLancamento) {
        this.anoDeLancamento = anoDeLancamento;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        if (genero == null || genero.isEmpty()) {
            throw new IllegalArgumentException("Gênero do filme não pode ser vazio.");
        }
        this.genero = genero;
    }

    public int getQuantDvd() {
        return quantDvd;
    }

    public void addQuantDvd(int quantDvd) {
        if (quantDvd < 0) {
            throw new IllegalArgumentException("Quantidade de DVDs a adicionar não pode ser negativa.");
        }
        this.quantDvd += quantDvd;
    }

    public void setQuantDvd(int quantDvd) {
        if (quantDvd < 0) {
            throw new IllegalArgumentException("Quantidade de DVDs não pode ser negativa.");
        }
        this.quantDvd = quantDvd;
    }

    public int getQuantBlueRay() {
        return quantBlueRay;
    }

    public void addQuantBlueRay(int quantBlueRay) {
        if (quantBlueRay < 0) {
            throw new IllegalArgumentException("Quantidade de Blu-rays a adicionar não pode ser negativa.");
        }
        this.quantBlueRay += quantBlueRay;
    }

    public void setQuantBlueRay(int quantBlueRay) {
        if (quantBlueRay < 0) {
            throw new IllegalArgumentException("Quantidade de Blu-rays não pode ser negativa.");
        }
        this.quantBlueRay = quantBlueRay;
    }

    public int getTipoDeFilme() {
        return tipoDeFilme;
    }

    public void setTipoDeFilme(int tipoDeFilme) {
        if (tipoDeFilme != TIPO_LANCAMENTO && tipoDeFilme != TIPO_NOVO && tipoDeFilme != TIPO_ANTIGO) {
            throw new IllegalArgumentException("Tipo de filme inválido.");
        }
        this.tipoDeFilme = tipoDeFilme;
    }

    public void decrementarQtdDvd() {
        if (quantDvd > 0) {
            quantDvd--;
        }
    }

    public void decrementarQtdBlueRay() {
        if (quantBlueRay > 0) {
            quantBlueRay--;
        }
    }

    @Override
    public String toString() {
        return String.format("%s (%d/%d/%d) - DVDs: %d, Blu-rays: %d", nome, anoDeLancamento.getDia(),
                anoDeLancamento.getMes(), anoDeLancamento.getAno(), quantDvd, quantBlueRay);
    }
}
