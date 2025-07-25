package eu.kalnarapps.TacoCloudApp.domain.tacos;


import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Table("ingredients")
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
public class Ingredient {

    @PrimaryKey
    private String id;

    private String name;

    private Type type;

    public enum Type {
        WRAP, PROTEIN, VEGGIES, CHEESE, SAUCE;

        public static Type from(String typeString) {
            for (Type type : Type.values()) {
                if (type.name().equals(typeString)) {
                    return type;
                }
            }
            throw new IllegalArgumentException("Unknown Type name: " + typeString);
        }
    }

}
