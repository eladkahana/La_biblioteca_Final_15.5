package comMain.controllers;

import comMain.entities.ShelfmarkEntity;
import comMain.services.ShelfmarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Validated
@RestController
@RequestMapping("/shelfmark")
public class ShelfmarkController {

    @Autowired
    private ShelfmarkService shelfmarkService;

    @PostMapping
    public String save(@Valid @RequestBody ShelfmarkEntity vO) {
        return shelfmarkService.save(vO).toString();
    }

    @DeleteMapping("/{id}")
    public void delete(@Valid @NotNull @PathVariable("id") Integer id) {
        shelfmarkService.delete(id);
    }

    @PutMapping("/{id}")
    public void update(@Valid @NotNull @PathVariable("id") Integer id,
                       @Valid @RequestBody ShelfmarkEntity vO) {
        shelfmarkService.update(id, vO);
    }

    @GetMapping("/{id}")
    public ShelfmarkEntity getById(@Valid @NotNull @PathVariable("id") Integer id) {
        return shelfmarkService.getById(id);
    }


}
