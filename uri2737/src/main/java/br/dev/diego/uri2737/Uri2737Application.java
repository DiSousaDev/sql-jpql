package br.dev.diego.uri2737;

import br.dev.diego.uri2737.dto.LawyerMinDTO;
import br.dev.diego.uri2737.projections.LawyerMinProjection;
import br.dev.diego.uri2737.repositories.LawyerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.stream.Collectors;

@SpringBootApplication
public class Uri2737Application implements CommandLineRunner {

	@Autowired
	private LawyerRepository repository;
	
	public static void main(String[] args) {
		SpringApplication.run(Uri2737Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		List<LawyerMinProjection> list = repository.search();
		List<LawyerMinDTO> listDTO = list.stream().map(lawyerMinProjection -> new LawyerMinDTO(lawyerMinProjection)).collect(Collectors.toList());

		System.out.println("NATIVE QUERY");
		listDTO.forEach(lawyerMinDTO -> System.out.println(lawyerMinDTO));

	}
}
