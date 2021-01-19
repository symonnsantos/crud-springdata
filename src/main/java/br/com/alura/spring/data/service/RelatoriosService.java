package br.com.alura.spring.data.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

import org.springframework.stereotype.Service;

import br.com.alura.spring.data.orm.Funcionario;
import br.com.alura.spring.data.orm.FuncionarioProjecao;
import br.com.alura.spring.data.repository.FuncionarioRepository;

@Service
public class RelatoriosService {
	
	private Boolean system = true;
	
	private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("mm/DD/yyyy");
	
	private final FuncionarioRepository funcionarioRepository;
	
	public RelatoriosService(FuncionarioRepository funcionarioRepository) {
		this.funcionarioRepository = funcionarioRepository;
	}
	
	public void inicial(Scanner scanner) {
		while(system) {
			System.out.println(":: AÇÕES DE CARGOS ::");
			System.out.println("");
			System.out.println("0 - sair");
			System.out.println("1 - Pesquisar funcionário por nome");
			System.out.println("2 - Pesquisar funcionário por nome, salário e data");
			System.out.println("3 - Pesquisar funcionário por data de contratação");
			System.out.println("4 - Pesquisar funcionário por maior salário");
			
			int action = scanner.nextInt();
			
			switch(action) {
			case 1:
				buscaFuncionarioNome(scanner);
				break;
			case 2:
				FuncionarioNomeSalarioMaiorData(scanner);
				break;
			case 3:
				buscaFuncionarioDataContratacao(scanner);
				break;
			case 4:
				buscaFuncionarioSalario();
				break;
			default:
				system = false;
				break;
			}
		}
	}
	
	private void buscaFuncionarioNome(Scanner scanner) {
		System.out.println("Nome: ");
		String nome = scanner.next();
		List<Funcionario> list = funcionarioRepository.findByNome(nome);
		list.forEach(System.out::println);		
	}
	
	private void FuncionarioNomeSalarioMaiorData(Scanner scanner) {
		System.out.println("Nome: ");
		String nome = scanner.next();
		
		System.out.println("Data de contratação: ");
		String data = scanner.next();
		LocalDate localDate = LocalDate.parse(data, formatter);
		
		System.out.println("Salário: ");
		double salario = scanner.nextDouble();
		
		List<Funcionario> list = funcionarioRepository.findNomeSalarioMaiorDataContratacao(nome, salario, localDate);
		list.forEach(System.out::println);
	}
	
	private void buscaFuncionarioDataContratacao(Scanner scanner) {
		
		System.out.println("Data de contratação: ");
		String data = scanner.next();
		LocalDate localDate = LocalDate.parse(data, formatter);
		
		List<Funcionario> list = funcionarioRepository.findDataContratacaoMaior(localDate);
		list.forEach(System.out::println);
	}
	
	private void buscaFuncionarioSalario() {
		List<FuncionarioProjecao> list = funcionarioRepository.findFuncionarioSalario();
		list.forEach(f -> System.out.println("Funcionario: id: " + f.getId()
		+ " | Nome: " + f.getNome() + " | salario" + f.getSalario()));
	}

}
