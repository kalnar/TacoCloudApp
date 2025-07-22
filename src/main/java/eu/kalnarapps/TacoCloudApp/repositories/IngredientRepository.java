package eu.kalnarapps.TacoCloudApp.repositories;

import eu.kalnarapps.TacoCloudApp.domain.tacos.Ingredient;
import org.springframework.data.repository.CrudRepository;
 
public interface IngredientRepository extends CrudRepository<Ingredient, String> {
 
}