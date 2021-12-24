package br.dev.diego.uri2609;

import br.dev.diego.uri2609.dto.CategorySumDTO;
import br.dev.diego.uri2609.projections.CategorySumProjection;
import br.dev.diego.uri2609.repositories.CategoryRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Uri2609Application implements CommandLineRunner {

	@Autowired
	private CategoryRepository repository;
	
	public static void main(String[] args) {
		SpringApplication.run(Uri2609Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		List<CategorySumProjection> list = repository.search();
		List<CategorySumDTO> listDTO = list.stream().map(categorySumProjection -> new CategorySumDTO(categorySumProjection)).collect(Collectors.toList());
		System.out.println("NATIVE QUERY");
		listDTO.forEach(System.out::println);

		List<CategorySumDTO> listDTO1 = repository.search1();
		System.out.println("JPQL QUERY");
		listDTO1.forEach(System.out::println);

	}
}
