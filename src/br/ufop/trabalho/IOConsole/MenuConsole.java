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
	private MenuClienteConsole menuCliente;

	//private MenuFilmesConsole menuFilmes;
	//pode-se criar outras classes para a exibição dos outros submenus
	/**
	 * Objeto responsável por fazer a leitura dos dados (Scanner). Deverá ser instanciado apenas uma vez e passado como parâmetro
	 * para as demais classes.
	 */
	private Scanner input;

	public MenuConsole(){
		/**
		 * Inicialização do objeto de controle. Esta variável deve ser inicializada apenas uma vez pois haverá apenas uma objeto controle
		 * responsável por acessar os dados (filmes, clientes, movimentações).
		 * As demais classes de exibição de menus deverão ser criadas e inicializadas
		 */
		controle = new Controle();
		input = new Scanner(System.in);
		
		//Inicialização da classe de acessoFuncionario. Deverá ser passado como parâmetro o objeto de controle e o objeto de entrada de dados
		menuCliente = new MenuClienteConsole(controle, input);	
	}
	
	public void inicioExecucao() {
		boolean continua = true;
		do{
			continua = exibeMenuPrincipal();
		}while(continua == true);
		System.out.println("Obrigado por usar o sistema!");
	}
	
	private boolean exibeMenuPrincipal(){
		System.out.println("Digite a opção de Aceso:\n\t1 - Filme:\n\t2 - Clientes\n\t3 - Relatórios\n\t4 - Sair ");
		// int op = input.nextInt();
		// Toda a vez que for ler um inteiro devo chamar o método leInteiroConsole que é estático da classe Util.
		// Dessa forma, a entrada inválida será sempre tratada e não irá quebrar a aplicação
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
