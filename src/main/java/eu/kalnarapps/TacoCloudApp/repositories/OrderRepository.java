package eu.kalnarapps.TacoCloudApp.repositories;

import eu.kalnarapps.TacoCloudApp.domain.tacos.TacoOrder;
import eu.kalnarapps.TacoCloudApp.domain.user.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface OrderRepository extends CrudRepository<TacoOrder, Long> {

    List<TacoOrder> findByUserOrderByPlacedAtDesc(User user, Pageable pageable);
}