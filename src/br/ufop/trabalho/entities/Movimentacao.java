package br.ufop.trabalho.entities;
import java.io.Serial;
import java.io.Serializable;

public abstract class Movimentacao implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private String nome;
    private String descricao;
    double valor;
    Data dataDaMovimentacao = null;
    int tipoDeMovimentacao;

    public Movimentacao(String nome, String descricao, Double valor, Data dataDaMovimentacao, int tipoDeMovimentacao) {
        setNome(nome);
        setDescricao(descricao);
        setValor(valor);
        setDataDaMovimentacao(dataDaMovimentacao);
        setTipoDaMovimentacao(tipoDeMovimentacao);
    }

    public void setTipoDaMovimentacao(int tipoDeMovimentacao){
        this.tipoDeMovimentacao = tipoDeMovimentacao;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public void setDataDaMovimentacao(Data dataDaMovimentacao) {
        this.dataDaMovimentacao = dataDaMovimentacao;
    }

    public int getTipoDaMovimentacao() {
        return tipoDeMovimentacao;
    }

    public String getNome() {
        return nome;
    }

    public Double getValor() {
        return valor;
    }

    public Data getDataDaMovimentacao() {
        return dataDaMovimentacao;
    }

    public String toString() {

        return "Movimentacao{\t" +
               "\tnome='" + nome + '\'' +
               ", \tdescricao='" + descricao + '\'' +
               ", \tvalor=" + valor +
               ", \tdata=" + dataDaMovimentacao +
               '}';
    }
}
