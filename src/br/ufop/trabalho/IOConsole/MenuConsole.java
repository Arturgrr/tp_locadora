package br.ufop.trabalho.IOConsole;

import java.util.Scanner;

import br.ufop.trabalho.Util;
import br.ufop.trabalho.controle.Controle;

public class MenuConsole {

	/**
	 * Controle - Objeto que será utilizado para acesso aos dados do sistema (classes Entidade que armazenam os dados)
	 */
	private Controle controle;

	/**
	 * Objetos responsáveis por exibir menus de cada um dos acessos
	 */
	private final MenuClienteConsole menuCliente;

	/**
	 * Objeto responsável por fazer a leitura dos dados (Scanner). Deverá ser instanciado apenas uma vez e passado como parâmetro
	 * para as demais classes.
	 */
	private final Scanner input;

	public MenuConsole(){
		controle = new Controle();
		input = new Scanner(System.in);

		menuCliente = new MenuClienteConsole(controle, input);	
	}
	
	public void inicioExecucao() {
		boolean continua = true;
		do{
			continua = exibeMenuPrincipal();
		}while(continua);
		System.out.println("Obrigado por usar o sistema!");
	}
	
	private boolean exibeMenuPrincipal(){
		System.out.println("Digite a opção de Aceso:\n\t1 - Filme:\n\t2 - Clientes\n\t3 - Relatórios\n\t4 - Sair ");
		int op = Util.leInteiroConsole(input);
		switch (op) {
		case 1:
			System.out.println("Falta implementar!");
			break;	
		case 2:
			menuCliente.exibeMenuClientes();		
			break;
		case 3:	
			System.out.println("Falta implementar!");
			break;		
		case 4:
			return false;		
		default:
				System.out.println("Opção Inválida");
		}
		return true;
	}
}
