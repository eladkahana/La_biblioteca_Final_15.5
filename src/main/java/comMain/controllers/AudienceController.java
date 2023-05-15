package comMain.controllers;

import comMain.entities.AudienceEntity;
import comMain.entities.AuthorEntity;
import comMain.services.AudienceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@Validated
@RestController
@RequestMapping("/audience")
public class AudienceController {

    @Autowired
    private AudienceService audienceService;

    @PostMapping
    public String save(@Valid @RequestBody AudienceEntity vO) {
        return audienceService.save(vO).toString();
    }

    @DeleteMapping("/{id}")
    public void delete(@Valid @NotNull @PathVariable("id") Integer id) {
        audienceService.delete(id);
    }

    @PutMapping("/{id}")
    public void update(@Valid @NotNull @PathVariable("id") Integer id,
                       @Valid @RequestBody AudienceEntity vO) {
        audienceService.update(id, vO);
    }

    @GetMapping("/{id}")
    public AudienceEntity getById(@Valid @NotNull @PathVariable("id") Integer id) {
        return audienceService.getById(id);
    }

    @GetMapping
    public List<AudienceEntity> getAllAudiences(){
        return audienceService.getAllAudiences();
    }

}
