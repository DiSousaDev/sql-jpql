package br.dev.diego.uri2602.repositories;

import br.dev.diego.uri2602.dto.CustomerMinDTO;
import br.dev.diego.uri2602.entities.Customer;
import br.dev.diego.uri2602.projections.CustomerMinProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Long>{

    @Query(nativeQuery = true, value = "SELECT name FROM customers WHERE UPPER(state) = UPPER(:state)")
	List<CustomerMinProjection> search(String state);

    // JPQL NÃO PRECISA PROJECTION, PODEMOS RETORNAR O DTO DIRETO
    @Query("SELECT new br.dev.diego.uri2602.dto.CustomerMinDTO(obj.name) "
            + "FROM Customer obj "
            + "WHERE UPPER(obj.state) = UPPER(:state)")
    List<CustomerMinDTO> search1(String state);

}
