package br.dev.diego.uri2617;

import br.dev.diego.uri2617.dto.ProductMinDTO;
import br.dev.diego.uri2617.projections.ProductMinProjection;
import br.dev.diego.uri2617.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.stream.Collectors;

@SpringBootApplication
public class Uri2617Application implements CommandLineRunner {

	@Autowired
	private ProductRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(Uri2617Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception{

		List<ProductMinProjection> list = repository.search();
		List<ProductMinDTO> listDTO = list.stream().map(productMinProjection -> new ProductMinDTO(productMinProjection)).collect(Collectors.toList());
		System.out.println("NATIVE QUERY");
		listDTO.forEach(System.out::println);

		List<ProductMinDTO> listDTO1 = repository.search1();
		System.out.println("JPQL QUERY");
		listDTO1.forEach(System.out::println);

	}
}
