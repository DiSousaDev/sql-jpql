package br.dev.diego.uri2621;

import br.dev.diego.uri2621.Projection.ProductMinProjection;
import br.dev.diego.uri2621.dto.ProductMinDTO;
import br.dev.diego.uri2621.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.stream.Collectors;

@SpringBootApplication
public class Uri2621Application implements CommandLineRunner {

	@Autowired
	private ProductRepository repository;
	
	public static void main(String[] args) {
		SpringApplication.run(Uri2621Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		System.out.println("NATIVE QUERY");
		List<ProductMinProjection> list = repository.search(10, 20, "P");
		List<ProductMinDTO> listDTO = list.stream().map(projection -> new ProductMinDTO(projection)).collect(Collectors.toList());

		listDTO.forEach(System.out::println);

		System.out.println("\n\n\nJPQL QUERY");
		List<ProductMinDTO> listDTO2 = repository.search1(10, 20, "P");
		listDTO2.forEach(System.out::println);

	}
}
