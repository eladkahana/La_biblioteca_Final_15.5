package comMain.repositories;

import comMain.entities.BookVsAuthorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookVSAuthorRepository extends JpaRepository<BookVsAuthorEntity, Integer> {

    @Procedure(name = "setAuthorToBook")
    void setAuthorToBook
            (@Param("AuthorFN") String AuthorFN, @Param("AuthorLN") String AuthorLN,@Param("ISBN") String ISBN);

    @Procedure(name = "deleteAuthorFromBook")
    void deleteAuthorFromBook
            (@Param("bookID") int bookID);


    @Procedure(name = "getAllAuthorsByBook")
    List<BookVsAuthorEntity> getAllAuthorsByBook
            (@Param("bookID") int bookID);


}