package comMain.controllers;

import comMain.entities.CategoriesEntity;
import comMain.entities.ReadersEntity;
import comMain.services.CategoriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@CrossOrigin
@Validated
@RestController
@RequestMapping("/categories")
public class CategoriesController {

    @Autowired
    private CategoriesService categoriesService;

    @PostMapping
    public String save(@Valid @RequestBody CategoriesEntity vO) {
        return categoriesService.save(vO).toString();
    }

    @DeleteMapping("/{id}")
    public void delete(@Valid @NotNull @PathVariable("id") Integer id) {
        categoriesService.delete(id);
    }

    @PutMapping("/{id}")
    public void update(@Valid @NotNull @PathVariable("id") Integer id,
                       @Valid @RequestBody CategoriesEntity vO) {
        categoriesService.update(id, vO);
    }

    @GetMapping("/{id}")
    public CategoriesEntity getById(@Valid @NotNull @PathVariable("id") Integer id) {
        return categoriesService.getById(id);
    }

    @GetMapping
    public List<CategoriesEntity> getAllCategories(){
        return categoriesService.getAllCategories();
    }

}
