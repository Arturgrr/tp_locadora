package br.ufop.trabalho.IOConsole;

import java.util.Scanner;

import br.ufop.trabalho.Util;
import br.ufop.trabalho.controle.Constantes;
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
	private final MenuBalanceteConsole menuBalancete;
	private final MenuRelatorioConsole menuRelatorio;

	public MenuConsole(){
		controle = new Controle();
		input = new Scanner(System.in);

		menuCliente = new MenuClienteConsole(controle, input);
		menuFilme = new MenuFilmeConsole(controle, input);
		menuBalancete = new MenuBalanceteConsole (controle, input);
		menuRelatorio = new MenuRelatorioConsole(controle, input);
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
				
				\t1 - Gestao de Filmes
				\t2 - Gestao de Clientes
				\t3 - Relatorios
				\t4 - Configuracoes
				\t5 - Balancete
				\t0 - Sair
				""");
		int op = Util.leInteiroConsole(input);
		switch (op) {
			case 1 -> menuFilme.exibeMenuFilmes();
			case 2 -> menuCliente.exibeMenuCliente();
			case 3 -> menuRelatorio.exibeMenuRelatorio();
			case 4 -> {
				System.out.println("Multa atual por dia: ");
				System.out.println(controle.VALOR_MULTA_DIA);
				System.out.println("Quantidade maxima de filmes por Cliente atual: ");
				System.out.println(controle.QUANT_MAX_FILME_CLIENTE);
				System.out.println("Deseja alterar os valores atuais? Digite 1 para sim, 0 para voltar: ");
				int escolhaConf = input.nextInt();
				if (escolhaConf==1) {
					System.out.println("Defina o valor da multa: ");
					controle.VALOR_MULTA_DIA = input.nextDouble();
					System.out.println("Defina o valor da quantidade max de filmes: ");
					controle.QUANT_MAX_FILME_CLIENTE = input.nextInt();
				}
			}
			case 5 -> menuBalancete.exibeMenuBalancete();
			case 0 -> {
				System.exit(0);
				return false;
			}
			default -> System.out.println("Opção Invalida");
		}
		return true;
	}
}
