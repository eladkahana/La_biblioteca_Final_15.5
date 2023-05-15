package comMain.repositories;

import comMain.entities.BookVsAuthorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

public interface BookVSAuthorRepository extends JpaRepository<BookVsAuthorEntity, Integer> {

    @Procedure(name = "setAuthorToBook")
    void setAuthorToBook
            (@Param("AuthorFN") String AuthorFN, @Param("AuthorLN") String AuthorLN,@Param("ISBN") String ISBN);

}