package eu.kalnarapps.TacoCloudApp.converters;

import eu.kalnarapps.TacoCloudApp.domain.tacos.Ingredient;
import eu.kalnarapps.TacoCloudApp.repositories.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class IngredientByIdConverter implements Converter<String, Ingredient> {

    private final IngredientRepository ingredientRepo;

    // Inject the repository using constructor injection
    @Autowired
    public IngredientByIdConverter(IngredientRepository ingredientRepo) {
        this.ingredientRepo = ingredientRepo;
    }

    @Override
    public Ingredient convert(String id) {
        // Use the repository to find the ingredient.
        // This returns an Optional, so we handle the case where an
        // ingredient might not be found for a given ID.
        return ingredientRepo.findById(id).orElse(null);
    }
}