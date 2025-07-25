package eu.kalnarapps.TacoCloudApp.repositories;

import java.util.UUID;

import eu.kalnarapps.TacoCloudApp.domain.tacos.TacoOrder;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository
        extends CrudRepository<TacoOrder, UUID> {

}
