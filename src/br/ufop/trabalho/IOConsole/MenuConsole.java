package br.ufop.trabalho.IOConsole;

import br.ufop.trabalho.Util;
import br.ufop.trabalho.controle.Controle;

import java.util.Scanner;

public class MenuConsole {

	private final Controle controle;
	private final Scanner input;

	private final MenuClienteConsole menuCliente;
	private final MenuFilmeConsole menuFilme;
	private final MenuBalanceteConsole menuBalancete;
	private final MenuRelatorioConsole menuRelatorio;
	private final MenuConfiguracoesConsole menuConfiguracoes;

	public MenuConsole() {
		controle = new Controle();
		input = new Scanner(System.in);

		menuCliente = new MenuClienteConsole(controle, input);
		menuFilme = new MenuFilmeConsole(controle, input);
		menuBalancete = new MenuBalanceteConsole(controle, input);
		menuRelatorio = new MenuRelatorioConsole(controle, input);
		menuConfiguracoes = new MenuConfiguracoesConsole(controle, input);
	}

	public void inicioExecucao() {
		boolean continua;
		do {
			continua = exibeMenuPrincipal();
		} while (continua);
		System.out.println("Obrigado por usar o sistema!");
	}

	private boolean exibeMenuPrincipal() {
		System.out.println("""
                Digite a opção de Acesso:

                \t1 - Gestão de Filmes
                \t2 - Gestão de Clientes
                \t3 - Relatórios
                \t4 - Configurações
                \t5 - Balancete
                \t0 - Sair
                """);
		int op = Util.leInteiroConsole(input);
		switch (op) {
			case 1 -> menuFilme.exibeMenuFilmes();
			case 2 -> menuCliente.exibeMenuCliente();
			case 3 -> menuRelatorio.exibeMenuRelatorio();
			case 4 -> menuConfiguracoes.exibeMenuConfiguracoes();
			case 5 -> menuBalancete.exibeMenuBalancete();
			case 0 -> {
				return false;
			}
			default -> System.out.println("Opção Inválida");
		}
		return true;
	}
}
