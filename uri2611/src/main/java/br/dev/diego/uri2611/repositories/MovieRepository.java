package br.dev.diego.uri2611.repositories;

import br.dev.diego.uri2611.dto.MovieMinDTO;
import br.dev.diego.uri2611.entities.Movie;
import br.dev.diego.uri2611.projections.MovieMinProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movie, Long> {

    @Query(nativeQuery = true, value = "SELECT m.id, m.name "
                                        + "FROM movies m "
                                        + "INNER JOIN genres g ON m.id_genres = g.id "
                                        + "WHERE UPPER(g.description) = upper(:genreName)")
    List<MovieMinProjection> search(String genreName);

    @Query("SELECT new br.dev.diego.uri2611.dto.MovieMinDTO(m.id, m.name) "
            + "FROM Movie m "
            + "WHERE upper(m.genre.description) = upper(:genreName)")
    List<MovieMinDTO> search1(String genreName);

}
