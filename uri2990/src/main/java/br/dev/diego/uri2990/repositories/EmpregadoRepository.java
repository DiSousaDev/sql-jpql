package br.dev.diego.uri2990.repositories;

import br.dev.diego.uri2990.dto.EmpregadoDeptDTO;
import br.dev.diego.uri2990.entities.Empregado;
import br.dev.diego.uri2990.projections.EmpregadoDeptProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EmpregadoRepository extends JpaRepository<Empregado, Long> {

    @Query(nativeQuery = true, value = "select e.cpf, e.enome, d.dnome "
                                        + "from empregados e "
                                        + "inner join departamentos d ON e.dnumero = d.dnumero "
                                        + "left join trabalha t ON t.cpf_emp = e.cpf "
                                        + "where t.cpf_emp IS null "
                                        + "ORDER by e.cpf")
    List<EmpregadoDeptProjection> search();

    @Query(nativeQuery = true, value = "SELECT e.cpf, e.enome, d.dnome "
                                        + "FROM empregados e "
                                        + "INNER JOIN departamentos d ON d.dnumero = e.dnumero "
                                        + "WHERE e.cpf NOT IN ( "
                                        + "    SELECT empregados.cpf "
                                        + "    FROM empregados "
                                        + "    INNER JOIN trabalha ON empregados.cpf = trabalha.cpf_emp "
                                        + ") "
                                        + "ORDER BY e.cpf")
    List<EmpregadoDeptProjection> search1();

    @Query("SELECT new br.dev.diego.uri2990.dto.EmpregadoDeptDTO(e.cpf, e.enome, e.departamento.dnome) "
            + "FROM Empregado e "
            + "WHERE e.cpf NOT IN ( "
            + "    SELECT e.cpf "
            + "    FROM Empregado e "
            + "    INNER JOIN e.projetosOndeTrabalha "
            + ") "
            + "ORDER BY e.cpf")
    List<EmpregadoDeptDTO> search2();

}
