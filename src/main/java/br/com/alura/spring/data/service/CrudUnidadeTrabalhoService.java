package br.com.alura.spring.data.service;

import java.util.Scanner;

import org.springframework.stereotype.Service;

import br.com.alura.spring.data.orm.UnidadeTrabalho;
import br.com.alura.spring.data.repository.UnidadeTrabalhoRepository;

@Service
public class CrudUnidadeTrabalhoService {
	
	public Boolean system = true;
	
	private final UnidadeTrabalhoRepository unidadeTrabalhoRepository;
	
	public CrudUnidadeTrabalhoService(UnidadeTrabalhoRepository unidadeTrabalhoRepository) {
		this.unidadeTrabalhoRepository = unidadeTrabalhoRepository;
	}
	
	public void initial(Scanner scanner) {
		while(system) {
			System.out.println(":: CADASTRO DE UNIDADE DE TRABALHO ::");
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
				break;
			case 4:
				deletar(scanner);
				break;
			default:
				break;
			}
		}
	}
	
	public void salvar(Scanner scanner) {
		System.out.println("Descrição: ");
		String descricao = scanner.next();
		System.out.println("Endereço: ");
		String endereco = scanner.next();
		
		UnidadeTrabalho unidadeTrabalho = new UnidadeTrabalho();
		unidadeTrabalho.setDescricao(descricao);
		unidadeTrabalho.setEndereco(endereco);
		unidadeTrabalhoRepository.save(unidadeTrabalho);
				
		System.out.println("Unidade de trabalho cadastrada com sucesso!");
	}
	
	public void atualizar(Scanner scanner) {
		System.out.println("Id: ");
		int id = scanner.nextInt();
		System.out.println("Descrição: ");
		String descricao = scanner.next();
		System.out.println("Endereço: ");
		String endereco = scanner.next();
		
		UnidadeTrabalho unidadeTrabalho = new UnidadeTrabalho();
		unidadeTrabalho.setId(id);
		unidadeTrabalho.setDescricao(descricao);
		unidadeTrabalho.setEndereco(endereco);
		
		unidadeTrabalhoRepository.save(unidadeTrabalho);		
	}
	
	public void visualizar(Scanner scanner) {
		Iterable<UnidadeTrabalho> unidadesTrabalho = unidadeTrabalhoRepository.findAll();
		unidadesTrabalho.forEach(unidadeTrabalho -> System.out.println(unidadeTrabalho));
		
	}
	
	public void deletar(Scanner scanner) {
		System.out.println("Id: ");
		int id = scanner.nextInt();
		unidadeTrabalhoRepository.deleteById(id);
		
		System.out.println("Unidade de trabalho deletada com sucesso!");
		
		
	}

}
