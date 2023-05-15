package comMain.repositories;

import comMain.entities.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookRepository extends JpaRepository<BookEntity, Integer> {

    @Procedure(name = "AddCompleteBook")
    List<Object[]> AddCompleteBook
            (@Param("NISBN") String NISBN,
             @Param("title") String title,
             @Param("edition") String edition,
             @Param("shelfmark") String shelfmark,
             @Param("numberOfPages") int numberOfPages,
             @Param("publishYear") int publishYear,
             @Param("coverImage") byte[] coverImage,
             @Param("language") String language,
             @Param("publisher") String publisher,
             @Param("note") String note);

}