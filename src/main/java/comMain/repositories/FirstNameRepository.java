package comMain.repositories;

import comMain.entities.FirstNameEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FirstNameRepository extends JpaRepository<FirstNameEntity, Integer>  {

}