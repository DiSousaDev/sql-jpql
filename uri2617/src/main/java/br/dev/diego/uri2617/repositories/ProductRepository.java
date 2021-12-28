package br.dev.diego.uri2617.repositories;

import br.dev.diego.uri2617.dto.ProductMinDTO;
import br.dev.diego.uri2617.entities.Product;
import br.dev.diego.uri2617.projections.ProductMinProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query(nativeQuery = true, value = "select p.name as productName, pr.name as providerName "
                                        + "from products p "
                                        + "inner join providers pr on p.id_providers = pr.id "
                                        + "where pr.name = 'Ajax SA'")
    List<ProductMinProjection> search();

    @Query("select new br.dev.diego.uri2617.dto.ProductMinDTO(p.name, p.provider.name) "
            + "from Product p "
            + "where p.provider.name = 'Ajax SA'")
    List<ProductMinDTO> search1();

}
