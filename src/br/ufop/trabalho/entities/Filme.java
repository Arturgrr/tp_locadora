package br.ufop.trabalho.entities;

public class Filme {

    private String nome;
    private Data anoDeLancamento;
    private String genero;
    private int quantDvd, quantBlueRay;

    public Filme(String nome, Data anoDeLancamento, String genero, int quantDvd, int quantBlueRay) {
        setNome(nome);
        setAnoDeLancamento(anoDeLancamento);
        setGenero(genero);
        setQuantDvd(quantDvd);
        setQuantBlueRay(quantBlueRay);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
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
        this.genero = genero;
    }

    public int getQuantDvd() {
        return quantDvd;
    }

    public void setQuantDvd(int quantDvd) {
        this.quantDvd = quantDvd;
    }

    public int getQuantBlueRay() {
        return quantBlueRay;
    }

    public void setQuantBlueRay(int quantBlueRay) {
        this.quantBlueRay = quantBlueRay;
    }

}
