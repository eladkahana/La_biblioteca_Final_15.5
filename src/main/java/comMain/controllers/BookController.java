package comMain.controllers;

import comMain.entities.BookEntity;
import comMain.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@Validated
@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    private BookService bookService;

    @PostMapping
    public String save(@Valid @RequestBody BookEntity vO) {
        return bookService.save(vO).toString();
    }

    @DeleteMapping("/{id}")
    public void delete(@Valid @NotNull @PathVariable("id") Integer id) {
        bookService.delete(id);
    }

    @PutMapping("/{id}")
    public void update(@Valid @NotNull @PathVariable("id") Integer id,
                       @Valid @RequestBody BookEntity vO) {
        bookService.update(id, vO);
    }

    @GetMapping("/{id}")
    public BookEntity getById(@Valid @NotNull @PathVariable("id") Integer id) {
        return bookService.getById(id);
    }

    @GetMapping
    public List<BookEntity> getAllBooks() {
        return bookService.getAllBooks();
    }


    @PostMapping("/addCompleteBook")
    public List<Object[]> addCompleteBook(@RequestParam String ISBN,
                                          @RequestParam String title,
                                          @RequestParam String edition,
                                          @RequestParam String shelfmark,
                                          @RequestParam int numberOfPages,
                                          @RequestParam int publishYear,
                                          @RequestParam byte[] coverImage,
                                          @RequestParam String language,
                                          @RequestParam String publisher,
                                          @RequestParam String note) {
        List<Object[]> result = bookService.addCompleteBook(
            ISBN,title,edition,shelfmark,numberOfPages,publishYear,coverImage,language,publisher,note
        );
        return result;
    }


    @GetMapping("/MostReservedBook")
    public List<Object[]> MostReservedBook(){
        return bookService.MostReservedBook();
    }

}
