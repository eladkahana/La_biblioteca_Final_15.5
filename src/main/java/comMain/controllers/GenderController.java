package comMain.controllers;

import comMain.entities.GenderEntity;
import comMain.services.GenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Validated
@RestController
@RequestMapping("/gender")
public class GenderController {

    @Autowired
    private GenderService genderService;

    @PostMapping
    public String save(@Valid @RequestBody GenderEntity vO) {
        return genderService.save(vO).toString();
    }

    @DeleteMapping("/{id}")
    public void delete(@Valid @NotNull @PathVariable("id") Integer id) {
        genderService.delete(id);
    }

    @PutMapping("/{id}")
    public void update(@Valid @NotNull @PathVariable("id") Integer id,
                       @Valid @RequestBody GenderEntity vO) {
        genderService.update(id, vO);
    }

    @GetMapping("/{id}")
    public GenderEntity getById(@Valid @NotNull @PathVariable("id") Integer id) {
        return genderService.getById(id);
    }


}
