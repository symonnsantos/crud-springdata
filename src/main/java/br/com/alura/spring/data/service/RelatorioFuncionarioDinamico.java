package br.com.alura.spring.data.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import br.com.alura.spring.data.orm.Funcionario;
import br.com.alura.spring.data.repository.FuncionarioRepository;
import br.com.alura.spring.data.specification.SpecificationFuncionario;


@Service
public class RelatorioFuncionarioDinamico {
	
	private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	
	private final FuncionarioRepository funcionarioRepository;
	
	public RelatorioFuncionarioDinamico(FuncionarioRepository funcionarioRepository) {
		this.funcionarioRepository = funcionarioRepository;
	}
	
	public void initial(Scanner scanner) {
		System.out.println("Nome: ");
		String nome = scanner.next();
		//Se o valor estiver nulo, a consulta não será feita
		if(nome.equalsIgnoreCase("NULL")) {
			nome = null;
		}
		
		System.out.println("CPF: ");
		String cpf = scanner.next();
		if(cpf.equalsIgnoreCase("NULL")) {
			cpf = null;
		}
		
		System.out.println("Salário: ");
		Double salario = scanner.nextDouble();
		if(salario == 0) {
			salario = null;
		}
		
		System.out.println("Data de contratação: ");
		String data = scanner.next();
		LocalDate dataContratacao;
		
		if(data.equalsIgnoreCase("NULL")) {
			dataContratacao = null;
		} else {
			dataContratacao = LocalDate.parse(data, formatter);
		}
		
		
		List<Funcionario> funcionarios = funcionarioRepository.findAll(Specification
				.where(
						SpecificationFuncionario.nome(nome))
					.or(SpecificationFuncionario.cpf(cpf))
					.or(SpecificationFuncionario.salario(salario))
					.or(SpecificationFuncionario.dataContratacao(dataContratacao))
		);
		funcionarios.forEach(System.out::println);
	}

}
