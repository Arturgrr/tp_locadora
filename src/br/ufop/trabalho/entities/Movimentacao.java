package br.ufop.trabalho.entities;

public class Movimentacao {
    private String nome;
    private String descricao;
    double valor;
    Data dataDaMovimentacao = null;

    public Movimentacao(String nome, String descricao, Double valor, Data dataDaMovimentacao) {
        setNome(nome);
        setDescricao(descricao);
        setValor(valor);
        setDataDaMovimentacao(dataDaMovimentacao);
    }
    


// sets
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

// gets
public String getNome() {
    return nome;
}

public String getDescricao() {
    return descricao;
}

public Double getValor() {
    return valor;
}

public Data getDataDaMovimentacao() {
    return dataDaMovimentacao;
}

}
