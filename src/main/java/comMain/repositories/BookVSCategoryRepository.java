package comMain.repositories;

import comMain.entities.BookVsCategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookVSCategoryRepository extends JpaRepository<BookVsCategoryEntity, Integer>  {

    @Procedure(name = "setCategoryToBook")
    void setCategoryToBook
            (@Param("Category") String Category, @Param("ISBN") String ISBN);


    @Procedure(name = "deleteCategoryFromBook")
    void deleteCategoryFromBook
            (@Param("bookID") int bookID);


    @Procedure(name = "getAllCategoriesByBook")
    List<BookVsCategoryEntity> getAllCategoriesByBook
            (@Param("bookID") int bookID);


}