package comMain.repositories;

import comMain.entities.ReadersEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReadersRepository extends JpaRepository<ReadersEntity, Integer> {

}