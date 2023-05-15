package comMain.controllers;

import comMain.entities.AuthorEntity;
import comMain.entities.ReserveEntity;
import comMain.services.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@Validated
@RestController
@RequestMapping("/author")
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    @PostMapping
    public String save(@Valid @RequestBody AuthorEntity vO) {
        return authorService.save(vO).toString();
    }

    @DeleteMapping("/{id}")
    public void delete(@Valid @NotNull @PathVariable("id") Integer id) {
        authorService.delete(id);
    }

    @PutMapping("/{id}")
    public void update(@Valid @NotNull @PathVariable("id") Integer id,
                       @Valid @RequestBody AuthorEntity vO) {
        authorService.update(id, vO);
    }

    @GetMapping("/{id}")
    public AuthorEntity getById(@Valid @NotNull @PathVariable("id") Integer id) {
        return authorService.getById(id);
    }

    @GetMapping
    public List<AuthorEntity> getAllAuthors(){
        return authorService.getAllAuthors();
    }

}
