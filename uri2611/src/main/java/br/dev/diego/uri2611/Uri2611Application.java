package br.dev.diego.uri2611;

import br.dev.diego.uri2611.dto.MovieMinDTO;
import br.dev.diego.uri2611.projections.MovieMinProjection;
import br.dev.diego.uri2611.repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.stream.Collectors;

@SpringBootApplication
public class Uri2611Application implements CommandLineRunner {

	@Autowired
	private MovieRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(Uri2611Application.class, args);
	}

	@Override
	public void run(String... args) {

		List<MovieMinProjection> list = repository.search("action");
		List<MovieMinDTO> listDto = list.stream().map(movieMinProjection -> new MovieMinDTO(movieMinProjection)).collect(Collectors.toList());

		System.out.println("SQL NATIVO");
		listDto.forEach(System.out::println);

		System.out.println("-----------------");

		List<MovieMinDTO> listJpql = repository.search1("action");

		System.out.println("CONSULTA COM JPQL");
		listJpql.forEach(System.out::println);

	}
}
