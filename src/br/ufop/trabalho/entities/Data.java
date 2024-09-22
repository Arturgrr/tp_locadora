package br.ufop.trabalho.entities;

public class Data {
	private int mes;
	private int dia;
	private int ano;

	public Data(int d, int m, int a) {
		setAno(a);
		setMes(m);
		setDia(d);
	}

	/**
	 * Método para validar o mês
	 *
	 * @param mesTeste Inteiro do mês a ser verificado.
	 * @return Inteiro com o mês validado ou corrigido para janeiro.
	 */
	private int checarMes(int mesTeste) {
		if (mesTeste > 0 && mesTeste <= 12)
			return mesTeste;
		else {
			System.out.printf("Mes invalido (%d) alterado para Janeiro.", mesTeste);
			return 1; 
		} 
	}

	/**
	 * Método para validar o dia
	 *
	 * @param diaTeste Inteiro do dia a ser verificado.
	 * @return Inteiro com o mês validado ou corrigido para dia primeiro.
	 */
	private int checarDia(int diaTeste) {
		int[] diasNoMes = { 0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
		if (diaTeste > 0 && diaTeste <= diasNoMes[mes])
			return diaTeste;
		if (mes == 2 && diaTeste == 29
				&& (ano % 400 == 0 || (ano % 4 == 0 && ano % 100 != 0)))
			return diaTeste;
		System.out.printf("Dia invalido (%d) alterado para dia primeiro.", diaTeste);
		return 1; 
	}

	public String toString() {
		return String.format("%d/%d/%d", mes, dia, ano);
	}
	
	public int getMes() {
		return mes;
	}

	public void setMes(int mes) {
		this.mes = checarMes(mes);
	}

	public int getDia() {
		return dia;
	}

	public void setDia(int dia) {
		this.dia = checarDia(dia);
	}

	public int getAno() {
		return ano;
	}

	public void setAno(int ano) {
		this.ano = ano;
	}

} 
