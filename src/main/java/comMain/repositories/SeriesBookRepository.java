package comMain.repositories;

import comMain.entities.SeriesBookEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeriesBookRepository extends JpaRepository<SeriesBookEntity, Integer>  {

}