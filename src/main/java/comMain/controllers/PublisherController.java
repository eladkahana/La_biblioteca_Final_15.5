package comMain.controllers;

import comMain.entities.LastNameEntity;
import comMain.entities.PublisherEntity;
import comMain.services.PublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@Validated
@RestController
@RequestMapping("/publisher")
public class PublisherController {

    @Autowired
    private PublisherService publisherService;

    @PostMapping
    public String save(@Valid @RequestBody PublisherEntity vO) {
        return publisherService.save(vO).toString();
    }

    @DeleteMapping("/{id}")
    public void delete(@Valid @NotNull @PathVariable("id") Integer id) {
        publisherService.delete(id);
    }

    @PutMapping("/{id}")
    public void update(@Valid @NotNull @PathVariable("id") Integer id,
                       @Valid @RequestBody PublisherEntity vO) {
        publisherService.update(id, vO);
    }

    @GetMapping("/{id}")
    public PublisherEntity getById(@Valid @NotNull @PathVariable("id") Integer id) {
        return publisherService.getById(id);
    }

    @GetMapping
    public List<PublisherEntity> getAllPublishers(){
        return publisherService.getAllPublishers();
    }


}
