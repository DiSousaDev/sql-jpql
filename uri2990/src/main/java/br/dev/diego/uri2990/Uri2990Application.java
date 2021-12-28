package br.dev.diego.uri2990;

import br.dev.diego.uri2990.dto.EmpregadoDeptDTO;
import br.dev.diego.uri2990.projections.EmpregadoDeptProjection;
import br.dev.diego.uri2990.repositories.EmpregadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.stream.Collectors;


@SpringBootApplication
public class Uri2990Application implements CommandLineRunner {

	@Autowired
	private EmpregadoRepository repository;
	
	public static void main(String[] args) {
		SpringApplication.run(Uri2990Application.class, args);
	}

	@Override
	public void run(String... args) {

		List<EmpregadoDeptProjection> list = repository.search();
		List<EmpregadoDeptDTO> listDTO = list.stream().map(empregadoDeptProjection -> new EmpregadoDeptDTO(empregadoDeptProjection)).collect(Collectors.toList());
		System.out.println("NATIVE QUERY USANDO LEFT JOIN");
		listDTO.forEach(System.out::println);

		List<EmpregadoDeptProjection> list1 = repository.search1();
		List<EmpregadoDeptDTO> listDTO1 = list1.stream().map(empregadoDeptProjection -> new EmpregadoDeptDTO(empregadoDeptProjection)).collect(Collectors.toList());
		System.out.println("NATIVE QUERY USANDO CONSULTA ANINHADA (NOT IN)");
		listDTO1.forEach(System.out::println);

		List<EmpregadoDeptDTO> list2 = repository.search2();
		System.out.println("QUERY USANDO JPA (NOT IN)");
		list2.forEach(System.out::println);

	}
}
