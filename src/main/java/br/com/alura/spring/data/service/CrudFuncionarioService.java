package br.com.alura.spring.data.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import br.com.alura.spring.data.orm.Cargo;
import br.com.alura.spring.data.orm.Funcionario;
import br.com.alura.spring.data.orm.UnidadeTrabalho;
import br.com.alura.spring.data.repository.CargoRepository;
import br.com.alura.spring.data.repository.FuncionarioRepository;
import br.com.alura.spring.data.repository.UnidadeTrabalhoRepository;

@Service
public class CrudFuncionarioService {
	
	private Boolean system = true;
	private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	
	private final FuncionarioRepository funcionarioRepository;
	private final CargoRepository cargoRepository;
	private final UnidadeTrabalhoRepository unidadeTrabalhoRepository;
	
	public CrudFuncionarioService(FuncionarioRepository funcionarioRepository,
			CargoRepository cargoRepository, UnidadeTrabalhoRepository unidadeTrabalhoRepository) {
		this.funcionarioRepository = funcionarioRepository;
		this.cargoRepository = cargoRepository;
		this.unidadeTrabalhoRepository = unidadeTrabalhoRepository;
	}
	
	public void initial(Scanner scanner) {
		while(system) {
			System.out.println(":: CADASTRO DE FUNCIONÁRIOS ::");
			System.out.println("");
			System.out.println("0 - sair");
			System.out.println("1 - salvar");
			System.out.println("2 - atualizar");
			System.out.println("3 - visualizar");
			System.out.println("4 - deletar");
			
			int action = scanner.nextInt();
			
			switch (action) {
			case 1:
				salvar(scanner);
				break;
			case 2:
				atualizar(scanner);
				break;
			case 3:
				visualizar(scanner);
			case 4:
				deletar(scanner);
			default:
				break;
			}
		}
	}
	
	private void salvar(Scanner scanner) {
		System.out.println("Nome: ");
		String nome = scanner.next();
		System.out.println("CPF: ");
		String cpf = scanner.next();
		System.out.println("Salario: ");
		double salario = scanner.nextDouble();
		System.out.println("Data de contratação: ");
		String dataContratacao = scanner.next();
		System.out.println("Digite o cargoId");
        Integer cargoId = scanner.nextInt();
        
        List<UnidadeTrabalho> unidades = unidade(scanner);
				
		Funcionario funcionario = new Funcionario();
		funcionario.setNome(nome);
		funcionario.setCpf(cpf);
		funcionario.setSalario(salario);
		funcionario.setDataContratacao(LocalDate.parse(dataContratacao, formatter));
		Optional<Cargo> cargo = cargoRepository.findById(cargoId);
		funcionario.setCargo(cargo.get());
        funcionario.setUnidadeTrabalhos(unidades);
		
		funcionarioRepository.save(funcionario);
		
		System.out.println("Funcionário " + nome + " cadastrado com sucesso!");
	}
	
	private List<UnidadeTrabalho> unidade(Scanner scanner){
		Boolean isTrue = true;
		List<UnidadeTrabalho> unidades = new ArrayList<>();
		
		while(isTrue) {
			System.out.println("Digite a unidadeID (Ou 0 para sair)");
			Integer unidadeID = scanner.nextInt();
			
			if(unidadeID != 0) {
				Optional<UnidadeTrabalho> unidade = unidadeTrabalhoRepository.findById(unidadeID);
				unidades.add(unidade.get());
			} else {
				isTrue = false;
			}
		}
		return unidades;
	}
	
	private void atualizar(Scanner scanner) {
		System.out.println("Id do funcionário: ");
		int id = scanner.nextInt();
		System.out.println("Nome: ");
		String nome = scanner.next();
		System.out.println("CPF: ");
		String cpf = scanner.next();
		System.out.println("Salario: ");
		double salario = scanner.nextDouble();
		System.out.println("Data de contratação: ");
		String dataContratacao = scanner.next();
		
		Funcionario funcionario = new Funcionario();
		funcionario.setId(id);
		funcionario.setNome(nome);
		funcionario.setCpf(cpf);
		funcionario.setSalario(salario);
		funcionario.setDataContratacao(LocalDate.parse(dataContratacao, formatter));
		
		funcionarioRepository.save(funcionario);
		
		System.out.println("Funcionário " + nome + " atualizado com sucesso!");
		
	}
	
	private void visualizar(Scanner scanner) {
		
		System.out.println("Página que quer visualizar: ");
		Integer page = scanner.nextInt();
		
		Pageable pageable = PageRequest.of(page, 5, Sort.by(Sort.Direction.ASC, "nome"));
		Page<Funcionario> funcionarios = funcionarioRepository.findAll(pageable);
		
		System.out.println(funcionarios);
		System.out.println("Página atual: " + funcionarios.getNumber());
		System.out.println("Total de páginas: " + funcionarios.getTotalPages());
		System.out.println("Elementos: " + funcionarios.getTotalElements());
		
		funcionarios.forEach(funcionario -> System.out.println(funcionario));
	}
	
	private void deletar(Scanner scanner) {
		System.out.println("Id: ");
		int id = scanner.nextInt();
		funcionarioRepository.deleteById(id);
		
		System.out.println("Funcionário número " + id + "deletado com sucesso");
	}

}
