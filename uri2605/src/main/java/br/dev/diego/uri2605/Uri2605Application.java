package br.dev.diego.uri2605;

import br.dev.diego.uri2605.dto.ProductMinDto;
import br.dev.diego.uri2605.projection.ProductMinProjection;
import br.dev.diego.uri2605.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.stream.Collectors;

@SpringBootApplication
public class Uri2605Application implements CommandLineRunner {

	@Autowired
	private ProductRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(Uri2605Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception{

		List<ProductMinProjection> list = repository.search(6L);
		List<ProductMinDto> listDto = list.stream().map(projection -> new ProductMinDto(projection)).collect(Collectors.toList());
		System.out.println("CONSULTA NATIVA");
		listDto.forEach(System.out::println);

		List<ProductMinDto> listDto2 = repository.search1(6L);
		System.out.println("CONSULTA JPQL");
		listDto2.forEach(System.out::println);

	}
}
