package comMain.controllers;


import comMain.entities.BookEntity;
import comMain.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.enterprise.inject.Produces;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

    @GetMapping(value = "/getBooksForWeb", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Object[]>> getBooksForWeb() {
        List<Object[]> books = bookService.getBooksForWeb();
        HttpHeaders headers = new HttpHeaders();
        headers.setAccessControlAllowOrigin("*"); // Allow requests from any domain
        return ResponseEntity.ok().headers(headers).body(books);
    }


    @PutMapping("/EditBook")
    public void EditBook(@RequestParam String ISBN,
                                          @RequestParam String title,
                                          @RequestParam String edition,
                                          @RequestParam String shelfmark,
                                          @RequestParam int numberOfPages,
                                          @RequestParam int publishYear,
                                          @RequestParam byte[] coverImage,
                                          @RequestParam String language,
                                          @RequestParam String publisher,
                                          @RequestParam String note,
                                   @RequestParam int ID) {
         bookService.EditBook(
                ISBN,title,edition,shelfmark,numberOfPages,publishYear,coverImage,language,publisher,note,ID
        );
    }


    @GetMapping("/SuggestBooks")
    public List<Object[]> SuggestBooks(int readerID){
        return bookService.SuggestBooks(readerID);
    }


    @PutMapping("/createCopy")
    public void createCopy(int bookID){
         bookService.createCopy(bookID);
    }

}
