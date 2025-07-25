package eu.kalnarapps.TacoCloudApp.domain.mapper;

import eu.kalnarapps.TacoCloudApp.domain.tacos.Ingredient;
import eu.kalnarapps.TacoCloudApp.domain.tacos.IngredientUDT;
import eu.kalnarapps.TacoCloudApp.domain.tacos.Taco;
import eu.kalnarapps.TacoCloudApp.domain.tacos.TacoUDT;

public class TacoUDRUtils {

    public static IngredientUDT toIngredientUDT(Ingredient ingredient) {
        return new IngredientUDT(
                ingredient.getName(),
                ingredient.getType()
        );
    }

    public static TacoUDT toTacoUDT(Taco taco) {
        return new TacoUDT(
                taco.getName(),
                taco.getIngredients()
        );
    }
}
