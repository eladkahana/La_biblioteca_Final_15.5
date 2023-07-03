package comMain.controllers;


import comMain.entities.BookEntity;
import comMain.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.util.List;

@CrossOrigin
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
                                          @RequestParam MultipartFile coverImage,
                                          @RequestParam String language,
                                          @RequestParam String publisher,
                                          @RequestParam String note) throws IOException {
        List<Object[]> result = bookService.addCompleteBook(
            ISBN,title,edition,shelfmark,numberOfPages,publishYear,coverImage.getBytes(),language,publisher,note
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
        return ResponseEntity.ok().headers(headers).body(books);
    }


    @PutMapping("/EditBook")
    public void EditBook(@RequestParam String ISBN,
                                          @RequestParam String title,
                                          @RequestParam String edition,
                                          @RequestParam String shelfmark,
                                          @RequestParam int numberOfPages,
                                          @RequestParam int publishYear,
                                          @RequestParam MultipartFile coverImage,
                                          @RequestParam String language,
                                          @RequestParam String publisher,
                                          @RequestParam String note,
                                   @RequestParam int ID) throws IOException {
         bookService.EditBook(
                ISBN,title,edition,shelfmark,numberOfPages,publishYear, coverImage.getBytes(),language,publisher,note,ID
        );
    }


    @GetMapping(value = "/SuggestBooksWeb", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Object[]>> SuggestBooksWeb(@RequestParam int readerID){

        List<Object[]> book = bookService.SuggestBooks(readerID);
        HttpHeaders headers = new HttpHeaders();
        headers.setAccessControlAllowOrigin("*"); // Allow requests from any domain
        return ResponseEntity.ok().headers(headers).body(book);
    }

    @GetMapping("/SuggestBooks")
    public List<Object[]> SuggestBooks(int readerID){

        return bookService.SuggestBooks(readerID);
    }


    @PostMapping("/createCopy")
    public List<Object[]> createCopy(int bookID){
         return bookService.createCopy(bookID);
    }


    @PutMapping("/DeleteBook")
    public void DeleteBook(int copyID){
         bookService.DeleteBook(copyID);
    }


    @GetMapping("/getCopyHistory")
    public List<Object[]> getCopyHistory(@RequestParam int copyId){
        return bookService.getCopyHistory(copyId);
    }

    @GetMapping("/getAllCopiesByBook")
    public List<Object[]> getAllCopiesByBook(@RequestParam int BookId){
        return bookService.getAllCopiesByBook(BookId);
    }

    @GetMapping("/getBookRating")
    public List<Object[]> getBookRating(@RequestParam int BookId){
        return bookService.getBookRating(BookId);
    }


}
