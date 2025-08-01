package eu.kalnarapps.TacoCloudApp.controllers.design;

import eu.kalnarapps.TacoCloudApp.domain.tacos.Ingredient;
import eu.kalnarapps.TacoCloudApp.domain.tacos.Ingredient.Type;
import eu.kalnarapps.TacoCloudApp.domain.tacos.Taco;
import eu.kalnarapps.TacoCloudApp.domain.tacos.TacoOrder;
import eu.kalnarapps.TacoCloudApp.repositories.IngredientRepository;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Slf4j
@Controller
@RequestMapping("/design")
@SessionAttributes("tacoOrder")
public class DesignTacoController {

    private final IngredientRepository ingredientRepository;

    public DesignTacoController(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    @ModelAttribute
    public void addIngredientsToModel(Model model) {
        Iterable<Ingredient> ingredients = ingredientRepository.findAll();

        var ingredientsByType = StreamSupport.stream(ingredients.spliterator(), false)
                .collect(Collectors.groupingBy(Ingredient::getType));

        // Add each group to the model
        for (var entry : ingredientsByType.entrySet()) {
            model.addAttribute(entry.getKey().toString().toLowerCase(), entry.getValue());
        }
    }


    @ModelAttribute(name = "tacoOrder")
    public TacoOrder order() {
        return new TacoOrder();
    }

    @ModelAttribute(name = "taco")
    public Taco taco() {
        return new Taco();
    }

    @GetMapping
    public String showDesignForm(@AuthenticationPrincipal OAuth2User oauth2User) {
        return "design";
    }

    @PostMapping
    public String processTaco(
            @Valid Taco taco,
            Errors errors,
            @ModelAttribute TacoOrder tacoOrder
    ) {
        if (errors.hasErrors()) {
            return "design";
        }

        tacoOrder.addTaco(taco);
        log.info("Processing taco: {}", taco);

        return "redirect:/orders/current";
    }
}