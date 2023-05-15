package comMain.repositories;

import comMain.entities.BookVsAuthorEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookVSAuthorRepository extends JpaRepository<BookVsAuthorEntity, Integer> {

}