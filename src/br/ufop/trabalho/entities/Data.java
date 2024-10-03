package br.ufop.trabalho.entities;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Data implements Serializable {
	@Serial
	private static final long serialVersionUID = 1L;
	private int dia, mes, ano;

	public Data(int dia, int mes, int ano) {
		setDia(dia);
		setMes(mes);
		setAno(ano);
	}

	public int getDia() {
		return dia;
	}

	public void setDia(int dia) {
		this.dia = dia;
	}

	public int getMes() {
		return mes;
	}

	public void setMes(int mes) {
		this.mes = mes;
	}

	public int getAno() {
		return ano;
	}

	public void setAno(int ano) {
		this.ano = ano;
	}

	public Data addDias(int dias) {
		LocalDate date = LocalDate.of(ano, mes, dia).plusDays(dias);
		return new Data(date.getDayOfMonth(), date.getMonthValue(), date.getYear());
	}

	public int diferencaEmDias(Data outraData) {
		LocalDate dataAtual = LocalDate.of(ano, mes, dia);
		LocalDate dataComparar = LocalDate.of(outraData.getAno(), outraData.getMes(), outraData.getDia());
		return (int) ChronoUnit.DAYS.between(dataComparar, dataAtual);
	}

	public boolean isAfter(Data outraData) {
		LocalDate dataAtual = LocalDate.of(ano, mes, dia);
		LocalDate dataComparar = LocalDate.of(outraData.getAno(), outraData.getMes(), outraData.getDia());
		return dataAtual.isAfter(dataComparar);
	}

	@Override
	public String toString() {
		return String.format("%02d/%02d/%04d", dia, mes, ano);
	}

}
