package comMain.repositories;

import comMain.entities.BookVsCategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

public interface BookVSCategoryRepository extends JpaRepository<BookVsCategoryEntity, Integer>  {

    @Procedure(name = "setCategoryToBook")
    void setCategoryToBook
            (@Param("Category") String Category, @Param("ISBN") String ISBN);

}