package comMain.repositories;

import comMain.entities.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface BookRepository extends JpaRepository<BookEntity, Integer> {


    @Procedure(name = "addCompleteBook")
    List<Object[]> addCompleteBook
            (@Param("ISBN") String ISBN,
             @Param("title") String title,
             @Param("edition") String edition,
             @Param("shelfmark") String shelfmark,
             @Param("numberOfPages") int numberOfPages,
             @Param("publishYear") int publishYear,
             @Param("coverImage") byte[] coverImage,
             @Param("language") String language,
             @Param("publisher") String publisher,
             @Param("note") String note);

    @Procedure(name = "MostReservedBook")
    List<Object[]> MostReservedBook();


    @Procedure(name = "getBooksForWeb")
    List<Object[]> getBooksForWeb();



    @Procedure(name = "EditBook")
    void EditBook
            (@Param("ISBN") String ISBN,
             @Param("title") String title,
             @Param("edition") String edition,
             @Param("shelfmark") String shelfmark,
             @Param("numberOfPages") int numberOfPages,
             @Param("publishYear") int publishYear,
             @Param("coverImage") byte[] coverImage,
             @Param("language") String language,
             @Param("publisher") String publisher,
             @Param("note") String note,
             @Param("ID") int ID);


    @Procedure(name = "SuggestBooks")
    List<Object[]> SuggestBooks(@Param("readerID") int readerID);


    @Procedure(name = "createCopy")
    List<Object[]> createCopy(@Param("bookID") int bookID);

    @Procedure(name = "DeleteBook")
    void DeleteBook(@Param("copyID") int copyID);

    @Procedure(name = "getAllCopiesByBook")
    List<Object[]> getAllCopiesByBook(@Param("BookId") int BookId);

    @Procedure(name = "getCopyHistory")
    List<Object[]> getCopyHistory(@Param("copyId") int copyId);

    @Procedure(name = "getBookRating")
    List<Object[]> getBookRating(@Param("BookId") int BookId);


    @Procedure(name = "newBooks")
    List<Object[]> newBooks();

    @Procedure(name = "newBooksByMonth")
    List<Object[]> newBooksByMonth();

    @Procedure(name = "readingTrend")
    List<Object[]> readingTrend(@Param("BookID") int BookID);



}