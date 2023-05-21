package comMain.services;

import comMain.entities.BookEntity;
import comMain.repositories.BookRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public Integer save(BookEntity vO) {
        BookEntity bean = new BookEntity();
        BeanUtils.copyProperties(vO, bean);
        bean = bookRepository.save(bean);
        return bean.getId();
    }

    public void delete(Integer id) {
        bookRepository.deleteById(id);
    }

    public void update(Integer id, BookEntity vO) {
        BookEntity bean = requireOne(id);
        BeanUtils.copyProperties(vO, bean);
        bookRepository.save(bean);
    }

    public BookEntity getById(Integer id) {
        BookEntity original = requireOne(id);
        return toDTO(original);
    }


    private BookEntity toDTO(BookEntity original) {
        BookEntity bean = new BookEntity();
        BeanUtils.copyProperties(original, bean);
        return bean;
    }

    private BookEntity requireOne(Integer id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Resource not found: " + id));
    }


    public List<BookEntity> getAllBooks() {
        return bookRepository.findAll();
    }

    @Transactional(readOnly = true)
    public List<Object[]> addCompleteBook(
            String ISBN,
            String title,
            String edition,
            String shelfmark,
            int numberOfPages,
            int publishYear,
            byte[] coverImage,
            String language,
            String publisher,
            String note
    ) {
        return bookRepository.addCompleteBook(
                ISBN,
                title,
                edition,
                shelfmark,
                numberOfPages,
                publishYear,
                coverImage,
                language,
                publisher,
                note
        );
    }

    @Transactional(readOnly = true)
    public List<Object[]> MostReservedBook(){
        return bookRepository.MostReservedBook();
    }


    @Transactional(readOnly = true)
    public List<Object[]> getBooksForWeb(){
        return bookRepository.getBooksForWeb();
    }


    @Transactional(readOnly = true)
    public void EditBook(
            String ISBN,
            String title,
            String edition,
            String shelfmark,
            int numberOfPages,
            int publishYear,
            byte[] coverImage,
            String language,
            String publisher,
            String note,
            int ID
    ) {
        bookRepository.EditBook(
                ISBN,
                title,
                edition,
                shelfmark,
                numberOfPages,
                publishYear,
                coverImage,
                language,
                publisher,
                note,
                ID
        );
    }


    @Transactional(readOnly = true)
    public List<Object[]> SuggestBooks(int readerID){
        return bookRepository.SuggestBooks(readerID);
    }


    @Transactional(readOnly = true)
    public void createCopy(int bookID){
         bookRepository.createCopy(bookID);
    }





}
