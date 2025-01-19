package tacos;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;

@Data
public class Taco {

    @NotNull
    @Size(min = 3, message = "Имя должно быть как минимум из 3 букв")
    private String name;

    @NotNull
    @Size(min = 1, message = "Выберите как минимум 1 ингредиент")
    private List<Ingredient> ingredients;
}
