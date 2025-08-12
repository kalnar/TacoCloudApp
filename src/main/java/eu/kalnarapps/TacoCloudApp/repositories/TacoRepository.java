package eu.kalnarapps.TacoCloudApp.repositories;

import eu.kalnarapps.TacoCloudApp.domain.tacos.Taco;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;
import java.util.Optional;


public interface TacoRepository extends PagingAndSortingRepository<Taco, Long> {

    Optional<Taco> findById(Long id);
}