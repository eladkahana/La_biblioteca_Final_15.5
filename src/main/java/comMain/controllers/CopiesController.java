package comMain.controllers;

import comMain.entities.CopiesEntity;
import comMain.services.CopiesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Validated
@RestController
@RequestMapping("/copies")
public class CopiesController {

    @Autowired
    private CopiesService copiesService;

    @PostMapping
    public String save(@Valid @RequestBody CopiesEntity vO) {
        return copiesService.save(vO).toString();
    }

    @DeleteMapping("/{id}")
    public void delete(@Valid @NotNull @PathVariable("id") Integer id) {
        copiesService.delete(id);
    }

    @PutMapping("/{id}")
    public void update(@Valid @NotNull @PathVariable("id") Integer id,
                       @Valid @RequestBody CopiesEntity vO) {
        copiesService.update(id, vO);
    }

    @GetMapping("/{id}")
    public CopiesEntity getById(@Valid @NotNull @PathVariable("id") Integer id) {
        return copiesService.getById(id);
    }


}
