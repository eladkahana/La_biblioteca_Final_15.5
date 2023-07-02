package comMain.controllers;

import comMain.entities.AudienceVsBookEntity;
import comMain.entities.BookVsCategoryEntity;
import comMain.services.BookVSCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
@CrossOrigin
@Validated
@RestController
@RequestMapping("/bookVSCategory")
public class BookVSCategoryController {

    @Autowired
    private BookVSCategoryService bookVSCategoryService;

    @PostMapping
    public String save(@Valid @RequestBody BookVsCategoryEntity vO) {
        return bookVSCategoryService.save(vO).toString();
    }

    @DeleteMapping("/{id}")
    public void delete(@Valid @NotNull @PathVariable("id") Integer id) {
        bookVSCategoryService.delete(id);
    }

    @PutMapping("/{id}")
    public void update(@Valid @NotNull @PathVariable("id") Integer id,
                       @Valid @RequestBody BookVsCategoryEntity vO) {
        bookVSCategoryService.update(id, vO);
    }

    @GetMapping("/{id}")
    public BookVsCategoryEntity getById(@Valid @NotNull @PathVariable("id") Integer id) {
        return bookVSCategoryService.getById(id);
    }

    @PutMapping ("/setCategoryToBook")
    public void setCategoryToBook(@RequestParam String Category, @RequestParam String ISBN){
        bookVSCategoryService.setCategoryToBook(Category,ISBN);
    };


    @DeleteMapping ("/deleteCategoryFromBook")
    public void deleteCategoryFromBook(@RequestParam int bookID){
        bookVSCategoryService.deleteCategoryFromBook(bookID);
    };


    @GetMapping ("/getAllCategoriesByBook")
    public List<BookVsCategoryEntity> getAllCategoriesByBook(@RequestParam int bookID){
        return bookVSCategoryService.getAllCategoriesByBook(bookID);
    };


}
