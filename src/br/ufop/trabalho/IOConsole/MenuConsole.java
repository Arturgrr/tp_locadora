package br.ufop.trabalho.IOConsole;

import java.util.Scanner;

import br.ufop.trabalho.Util;
import br.ufop.trabalho.controle.Controle;

public class MenuConsole {

	/**
	 * Controle - Objeto que será utilizado para acesso aos dados do sistema (classes Entidade que armazenam os dados)
	 */
	private final Controle controle;

	/**
	 * Objeto responsável por fazer a leitura dos dados (Scanner). Deverá ser instanciado apenas uma vez e passado como parâmetro
	 * para as demais classes.
	 */
	private final Scanner input;

	private final MenuClienteConsole menuCliente;
	private final MenuFilmeConsole menuFilme;

	public MenuConsole(){
		controle = new Controle();
		input = new Scanner(System.in);

		menuCliente = new MenuClienteConsole(controle, input);
		menuFilme = new MenuFilmeConsole(controle, input);
	}
	
	public void inicioExecucao() {
		boolean continua = true;
		do{
			continua = exibeMenuPrincipal();
		}while(continua);
		System.out.println("Obrigado por usar o sistema!");
	}
	
	private boolean exibeMenuPrincipal(){
		System.out.println("""
				Digite a opcao de Acesso:
				
				\t1 - Filme
				\t2 - Clientes
				\t3 - Relatorios
				\t4 - Configuracoes
				\t5 - Balancete
				\t0 - Sair
				""");
		int op = Util.leInteiroConsole(input);
		switch (op) {
			case 1 -> menuFilme.exibeMenuFilmes();
			case 2 -> menuCliente.exibeMenuClientes();
			case 3 -> System.out.println("Falta implementar!");
			case 4 -> System.out.println("Falta implementar!");
			case 5 ->
			case 0 -> {
				return false;
			}
			default -> System.out.println("Opção Invalida");
		}
		return true;
	}
}
