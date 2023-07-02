package comMain.controllers;

import comMain.entities.FirstNameEntity;
import comMain.entities.LanguageEntity;
import comMain.services.LanguageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
@CrossOrigin
@Validated
@RestController
@RequestMapping("/language")
public class LanguageController {

    @Autowired
    private LanguageService languageService;

    @PostMapping
    public String save(@Valid @RequestBody LanguageEntity vO) {
        return languageService.save(vO).toString();
    }

    @DeleteMapping("/{id}")
    public void delete(@Valid @NotNull @PathVariable("id") Integer id) {
        languageService.delete(id);
    }

    @PutMapping("/{id}")
    public void update(@Valid @NotNull @PathVariable("id") Integer id,
                       @Valid @RequestBody LanguageEntity vO) {
        languageService.update(id, vO);
    }

    @GetMapping("/{id}")
    public LanguageEntity getById(@Valid @NotNull @PathVariable("id") Integer id) {
        return languageService.getById(id);
    }

    @GetMapping
    public List<LanguageEntity> getAllLanguages(){
        return languageService.getAllLanguages();
    }

}
