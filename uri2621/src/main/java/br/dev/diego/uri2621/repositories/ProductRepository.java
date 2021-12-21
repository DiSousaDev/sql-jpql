package br.dev.diego.uri2621.repositories;

import br.dev.diego.uri2621.Projection.ProductMinProjection;
import br.dev.diego.uri2621.dto.ProductMinDTO;
import br.dev.diego.uri2621.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query(nativeQuery = true, value = "SELECT p.name "
                                    + "FROM products p "
                                    + "INNER JOIN providers ON p.id_providers = providers.id "
                                    + "WHERE p.amount BETWEEN :min AND :max AND lower(providers.name) LIKE lower(concat(:provider, '%'))")
    List<ProductMinProjection> search(int min, int max,@Param("provider") String provider);

    @Query("SELECT new br.dev.diego.uri2621.dto.ProductMinDTO(p.name) "
            + "FROM Product p "
            + "WHERE p.amount BETWEEN :min AND :max AND lower(p.provider.name) LIKE lower(concat(:provider, '%'))")
    List<ProductMinDTO> search1(int min, int max,@Param("provider") String provider);

}
