package br.dev.diego.uri2609.repositories;

import br.dev.diego.uri2609.dto.CategorySumDTO;
import br.dev.diego.uri2609.entities.Category;
import br.dev.diego.uri2609.projections.CategorySumProjection;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CategoryRepository extends JpaRepository<Category, Long> {

  @Query(nativeQuery = true, value = "select c.name, sum(p.amount) "
                                      + "from categories c "
                                      + "inner join products p ON c.id = p.id_categories "
                                      + "group by c.name")
  List<CategorySumProjection> search();

  @Query("select new br.dev.diego.uri2609.dto.CategorySumDTO(p.category.name, sum(p.amount)) "
      + "from Product p "
      + "group by p.category.name")
  List<CategorySumDTO> search1();

}
