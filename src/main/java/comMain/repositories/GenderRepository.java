package comMain.repositories;

import comMain.entities.GenderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenderRepository extends JpaRepository<GenderEntity, Integer>  {

}