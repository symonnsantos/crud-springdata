package br.com.alura.spring.data;

import java.util.Scanner;

import br.com.alura.spring.data.service.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringDataApplication implements CommandLineRunner {
	
	private final CrudCargoService cargoService;
	private final CrudFuncionarioService crudFuncionarioService;
	private final CrudUnidadeTrabalhoService crudUnidadeTrabalhoService;
	private final RelatoriosService relatorioService;
	private final RelatorioFuncionarioDinamico relatorioFuncionarioDinamico;
	
	private boolean system = true;
	
	public SpringDataApplication(CrudCargoService cargoService,
								 CrudFuncionarioService crudFuncionarioService,
								 CrudUnidadeTrabalhoService crudUnidadeTrabalhoService,
								 RelatoriosService relatorioService, RelatorioFuncionarioDinamico relatorioFuncionarioDinamico, RelatorioFuncionarioDinamico relatorioFuncionarioDinamico1) {
		this.cargoService = cargoService;
		this.crudFuncionarioService = crudFuncionarioService;
		this.crudUnidadeTrabalhoService = crudUnidadeTrabalhoService;
		this.relatorioService = relatorioService;
		this.relatorioFuncionarioDinamico = relatorioFuncionarioDinamico1;
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringDataApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Scanner scanner = new Scanner(System.in);
		
		while(system) {
			System.out.println("Qual ação você quer executar?");
			System.out.println("0 - Sair");
			System.out.println("1 - Cargo");
			System.out.println("2 - Funcionario");
			System.out.println("3 - Unidade de Trabalho");
			System.out.println("4 - Relatórios");
			System.out.println("5 - Relatório funcionário dinâmico");
			
			int action = scanner.nextInt();
			
			switch (action) {
			case 1:
				cargoService.inicial(scanner);
				break;
			case 2:
				crudFuncionarioService.initial(scanner);
				break;
			case 3:
				crudUnidadeTrabalhoService.initial(scanner);
				break;
			case 4:
				relatorioService.inicial(scanner);
				break;
			case 5:
				relatorioFuncionarioDinamico.initial(scanner);
				break;
			default:
				break;
			}
			
		}		
	}

}
