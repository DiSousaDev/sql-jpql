package br.dev.diego.uri2605.repository;

import br.dev.diego.uri2605.dto.ProductMinDto;
import br.dev.diego.uri2605.entities.Product;
import br.dev.diego.uri2605.projection.ProductMinProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query(nativeQuery = true, value = "SELECT products.name as productName, providers.name as providerName "
                                        + "FROM products "
                                        + "INNER JOIN providers ON products.id_providers = providers.id "
                                        + "WHERE products.id_categories = :id")
    List<ProductMinProjection> search(Long id);

    @Query("SELECT new br.dev.diego.uri2605.dto.ProductMinDto(obj.name, obj.provider.name) "
            + "FROM Product obj "
            + "WHERE obj.category.id = :id")
    List<ProductMinDto> search1(Long id);

}
