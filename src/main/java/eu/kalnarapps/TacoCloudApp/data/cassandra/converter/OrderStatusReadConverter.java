package eu.kalnarapps.TacoCloudApp.data.cassandra.converter;

import eu.kalnarapps.TacoCloudApp.domain.tacos.Ingredient;
import jakarta.validation.constraints.NotNull;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;

@ReadingConverter
public class OrderStatusReadConverter implements Converter<String, Ingredient.Type> {
    @Override
    public Ingredient.Type convert(@NotNull String type) {
        return Ingredient.Type.from(type);
    }
}