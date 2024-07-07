package micro.service.products.Controllers;

import lombok.AllArgsConstructor;
import micro.service.products.Models.Category;
import micro.service.products.Services.CategoryService;
import micro.service.products.TDOs.CategoryDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/category")
@AllArgsConstructor
public class CategoryController {

    private CategoryService service;

    @GetMapping
    public List<CategoryDTO> getCategories(@ModelAttribute CategoryDTO filter) {
        return service.getCategories(filter);
    }
}
