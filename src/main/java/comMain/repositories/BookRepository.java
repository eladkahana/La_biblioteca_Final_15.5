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

}