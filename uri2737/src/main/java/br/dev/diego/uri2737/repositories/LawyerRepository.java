package br.dev.diego.uri2737.repositories;

import br.dev.diego.uri2737.entities.Lawyer;
import br.dev.diego.uri2737.projections.LawyerMinProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LawyerRepository extends JpaRepository<Lawyer, Long> {

    @Query(nativeQuery = true, value = "(select name, customers_number as customersNumber "
                                        + "from lawyers "
                                        + "WHERE customers_number = ("
                                         + "select MAX(customers_number) "
                                         + "from lawyers))"
                                    + "UNION ALL "
                                        + "(select name, customers_number "
                                        + "from lawyers "
                                        + "WHERE customers_number = ("
                                         + "select MIN(customers_number) "
                                         + "from lawyers))"
                                    + "UNION ALL "
                                        +"(select 'Average', ROUND(AVG(customers_number)) "
                                        + "from lawyers);")
    List<LawyerMinProjection> search();

}
