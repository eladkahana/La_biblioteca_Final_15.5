package comMain.repositories;

import comMain.entities.BookVsCategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookVSCategoryRepository extends JpaRepository<BookVsCategoryEntity, Integer>  {

}