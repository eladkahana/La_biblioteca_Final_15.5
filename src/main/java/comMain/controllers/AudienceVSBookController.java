package comMain.controllers;

import comMain.entities.AudienceVsBookEntity;
import comMain.services.AudienceVSBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Validated
@RestController
@RequestMapping("/audienceVSBook")
public class AudienceVSBookController {

    @Autowired
    private AudienceVSBookService audienceVSBookService;

    @PostMapping
    public String save(@Valid @RequestBody AudienceVsBookEntity vO) {
        return audienceVSBookService.save(vO).toString();
    }

    @DeleteMapping("/{id}")
    public void delete(@Valid @NotNull @PathVariable("id") Integer id) {
        audienceVSBookService.delete(id);
    }

    @PutMapping("/{id}")
    public void update(@Valid @NotNull @PathVariable("id") Integer id,
                       @Valid @RequestBody AudienceVsBookEntity vO) {
        audienceVSBookService.update(id, vO);
    }

    @GetMapping("/{id}")
    public AudienceVsBookEntity getById(@Valid @NotNull @PathVariable("id") Integer id) {
        return audienceVSBookService.getById(id);
    }


}
