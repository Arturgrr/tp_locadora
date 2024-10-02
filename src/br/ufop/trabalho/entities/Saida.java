package br.ufop.trabalho.entities;

public class Saida extends Movimentacao {
    private static final long serialVersionUID = 1L;

    public Saida(String nome, String descricao, Double valor, Data dataDaMovimentacao, int tipo) {
        super(nome,descricao,valor,dataDaMovimentacao, tipo); 

    }

   

}
