package tacos.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import tacos.Ingredient;
import tacos.Ingredient.Type;
import tacos.Taco;
import tacos.TacoOrder;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Controller
@RequestMapping("/design")
@SessionAttributes("tacoOrder")
public class DesignTacoController {

    @ModelAttribute
    public void addIngredientsToModel(Model model){
        List<Ingredient> ingredients = Arrays.asList(
                new Ingredient("FLTO", "Flour Tortilla", Type.Wrap),
                new Ingredient("COTO", "Corn Tortilla", Type.Wrap),
                new Ingredient("GRBF", "Ground Beef", Type.Protein),
                new Ingredient("CARN", "Carnitas", Type.Protein),
                new Ingredient("TMTO", "Diced Tomatoes", Type.Veggies),
                new Ingredient("LETC", "Lettuce", Type.Veggies),
                new Ingredient("CHED", "Cheddar", Type.Cheese),
                new Ingredient("JACK", "Monterrey Jack", Type.Cheese),
                new Ingredient("SLSA", "Salsa", Type.Sauce),
                new Ingredient("SRCR", "Sour Cream", Type.Sauce)
        );

        Type[] types = Ingredient.Type.values();
        for (Type type : types) {
            model.addAttribute(type.toString().toLowerCase());
            filterByType(ingredients, type);
        }
    }

    @ModelAttribute(name = "tacoOrder")
    public TacoOrder order(){
        return new TacoOrder();
    }

    @ModelAttribute(name = "taco")
    public Taco taco(){
        return new Taco();
    }

    @GetMapping
    public String showDesignForm(){
        return "design";
    }

    private Iterable<Ingredient> filterByType(List<Ingredient> ingredients, Type type) {
        return ingredients
                .stream()
                .filter(x -> x.getType().equals(type))
                .collect(Collectors.toList());
    }
}
