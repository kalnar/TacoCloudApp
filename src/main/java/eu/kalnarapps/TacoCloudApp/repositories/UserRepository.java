package eu.kalnarapps.TacoCloudApp.repositories;

import eu.kalnarapps.TacoCloudApp.domain.user.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
 
  User findByUsername(String username);
}