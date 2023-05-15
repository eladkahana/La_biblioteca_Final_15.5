package comMain.controllers;

import comMain.entities.BookVsAuthorEntity;
import comMain.services.BookVSAuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Validated
@RestController
@RequestMapping("/bookVSAuthor")
public class BookVSAuthorController {

    @Autowired
    private BookVSAuthorService bookVSAuthorService;

    @PostMapping
    public String save(@Valid @RequestBody BookVsAuthorEntity vO) {
        return bookVSAuthorService.save(vO).toString();
    }

    @DeleteMapping("/{id}")
    public void delete(@Valid @NotNull @PathVariable("id") Integer id) {
        bookVSAuthorService.delete(id);
    }

    @PutMapping("/{id}")
    public void update(@Valid @NotNull @PathVariable("id") Integer id,
                       @Valid @RequestBody BookVsAuthorEntity vO) {
        bookVSAuthorService.update(id, vO);
    }

    @GetMapping("/{id}")
    public BookVsAuthorEntity getById(@Valid @NotNull @PathVariable("id") Integer id) {
        return bookVSAuthorService.getById(id);
    }


}
