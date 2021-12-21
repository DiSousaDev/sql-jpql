package br.dev.diego.uri2605.repository;

import br.dev.diego.uri2605.entities.Product;
import br.dev.diego.uri2605.projection.ProductMinProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query(nativeQuery = true, value = "SELECT products.name, providers.name "
                                        + "FROM products "
                                        + "INNER JOIN providers ON products.id_providers = providers.id "
                                        + "WHERE products.id_categories = :id")
    List<ProductMinProjection> search(Long id);

}
