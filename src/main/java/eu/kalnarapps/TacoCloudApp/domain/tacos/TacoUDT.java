package eu.kalnarapps.TacoCloudApp.domain.tacos;

import org.springframework.data.cassandra.core.mapping.UserDefinedType;
import lombok.Data;

import java.util.List;

@Data
@UserDefinedType("taco")
public class TacoUDT {
 
  private final String name;
  private final List<IngredientUDT> ingredients;
 
}