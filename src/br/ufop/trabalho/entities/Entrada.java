package br.ufop.trabalho.entities;
import java.io.Serializable;

/**
 * @author João Teixeira
 */
public class Entrada extends Movimentacao{
    private static final long serialVersionUID = 1L;
  
    public Entrada(String nome, String descricao, Double valor, Data dataDaMovimentacao, int tipo) {
      
        super(nome,descricao,valor,dataDaMovimentacao,tipo); 

    }

  


}