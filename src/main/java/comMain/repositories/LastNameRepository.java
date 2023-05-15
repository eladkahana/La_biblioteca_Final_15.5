package comMain.repositories;

import comMain.entities.LastNameEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LastNameRepository extends JpaRepository<LastNameEntity, Integer>  {

}