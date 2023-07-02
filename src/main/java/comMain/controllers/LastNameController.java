package comMain.controllers;

import comMain.entities.LanguageEntity;
import comMain.entities.LastNameEntity;
import comMain.services.LastNameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
@CrossOrigin
@Validated
@RestController
@RequestMapping("/lastName")
public class LastNameController {

    @Autowired
    private LastNameService lastNameService;

    @PostMapping
    public String save(@Valid @RequestBody LastNameEntity vO) {
        return lastNameService.save(vO).toString();
    }

    @DeleteMapping("/{id}")
    public void delete(@Valid @NotNull @PathVariable("id") Integer id) {
        lastNameService.delete(id);
    }

    @PutMapping("/{id}")
    public void update(@Valid @NotNull @PathVariable("id") Integer id,
                       @Valid @RequestBody LastNameEntity vO) {
        lastNameService.update(id, vO);
    }

    @GetMapping("/{id}")
    public LastNameEntity getById(@Valid @NotNull @PathVariable("id") Integer id) {
        return lastNameService.getById(id);
    }

    @GetMapping
    public List<LastNameEntity> getAllLastName(){
        return lastNameService.getAllLastName();
    }
}
