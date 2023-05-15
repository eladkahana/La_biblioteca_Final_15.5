package comMain.repositories;

import comMain.entities.RequestsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RequestsRepository extends JpaRepository<RequestsEntity, Integer>  {

}