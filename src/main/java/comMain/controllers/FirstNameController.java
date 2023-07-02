package comMain.controllers;

import comMain.entities.CategoriesEntity;
import comMain.entities.FirstNameEntity;
import comMain.services.FirstNameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@CrossOrigin
@Validated
@RestController
@RequestMapping("/firstName")
public class FirstNameController {

    @Autowired
    private FirstNameService firstNameService;

    @PostMapping
    public String save(@Valid @RequestBody FirstNameEntity vO) {
        return firstNameService.save(vO).toString();
    }

    @DeleteMapping("/{id}")
    public void delete(@Valid @NotNull @PathVariable("id") Integer id) {
        firstNameService.delete(id);
    }

    @PutMapping("/{id}")
    public void update(@Valid @NotNull @PathVariable("id") Integer id,
                       @Valid @RequestBody FirstNameEntity vO) {
        firstNameService.update(id, vO);
    }

    @GetMapping("/{id}")
    public FirstNameEntity getById(@Valid @NotNull @PathVariable("id") Integer id) {
        return firstNameService.getById(id);
    }

    @GetMapping
    public List<FirstNameEntity> getAllFirstName(){
        return firstNameService.getAllFirstName();
    }

}
