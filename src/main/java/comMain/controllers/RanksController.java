package comMain.controllers;

import comMain.entities.RanksEntity;
import comMain.services.RanksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Validated
@RestController
@RequestMapping("/ranks")
public class RanksController {

    @Autowired
    private RanksService ranksService;

    @PostMapping
    public String save(@Valid @RequestBody RanksEntity vO) {
        return ranksService.save(vO).toString();
    }

    @DeleteMapping("/{id}")
    public void delete(@Valid @NotNull @PathVariable("id") Integer id) {
        ranksService.delete(id);
    }

    @PutMapping("/{id}")
    public void update(@Valid @NotNull @PathVariable("id") Integer id,
                       @Valid @RequestBody RanksEntity vO) {
        ranksService.update(id, vO);
    }

    @GetMapping("/{id}")
    public RanksEntity getById(@Valid @NotNull @PathVariable("id") Integer id) {
        return ranksService.getById(id);
    }


}
