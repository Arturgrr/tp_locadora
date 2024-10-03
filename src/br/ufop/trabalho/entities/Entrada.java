package br.ufop.trabalho.entities;

import java.io.Serial;

public class Entrada extends Movimentacao{
    @Serial
    private static final long serialVersionUID = 1L;
  
    public Entrada(String nome, String descricao, Double valor, Data dataDaMovimentacao, int tipo) {
      
        super(nome,descricao,valor,dataDaMovimentacao,tipo); 

    }

  


}