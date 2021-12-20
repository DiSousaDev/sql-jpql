package br.dev.diego.uri2602;

import br.dev.diego.uri2602.dto.CustomerMinDTO;
import br.dev.diego.uri2602.projections.CustomerMinProjection;
import br.dev.diego.uri2602.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.stream.Collectors;

@SpringBootApplication
public class Uri2602Application implements CommandLineRunner {

	@Autowired
	CustomerRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(Uri2602Application.class, args);
	}

	@Override
	public void run(String... args) {

		List<CustomerMinProjection> list = repository.search("rs");
		List<CustomerMinDTO> listDTO = list.stream().map(customerMinProjection -> new CustomerMinDTO(customerMinProjection)).collect(Collectors.toList());

		System.out.println("*** RESULTADO SQL RAIZ ***");
		listDTO.forEach(customerMinProjection -> System.out.println(customerMinProjection));
		System.out.println("\n\n");

		List<CustomerMinDTO> listDTO2 = repository.search1("rs");

		System.out.println("*** RESULTADO JPQL ***");
		listDTO2.forEach(customerMinDTO -> System.out.println(customerMinDTO));
	}
}
