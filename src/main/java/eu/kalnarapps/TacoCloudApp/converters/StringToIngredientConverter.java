package eu.kalnarapps.TacoCloudApp.converters;

import java.util.Optional;

import eu.kalnarapps.TacoCloudApp.domain.tacos.Ingredient;
import eu.kalnarapps.TacoCloudApp.domain.tacos.IngredientUDT;
import eu.kalnarapps.TacoCloudApp.repositories.IngredientRepository;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StringToIngredientConverter implements Converter<String, IngredientUDT> {

    private IngredientRepository ingredientRepository;

    public StringToIngredientConverter(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    @Override
    public IngredientUDT convert(String id) {
        Optional<Ingredient> ingredient = ingredientRepository.findById(id);
        if (ingredient.isEmpty()) {
            return null;
        }

        return ingredient.map(i -> {
            return new IngredientUDT(i.getName(), i.getType());
        }).get();
    }

}