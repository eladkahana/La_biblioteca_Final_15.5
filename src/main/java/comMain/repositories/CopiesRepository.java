package comMain.repositories;

import comMain.entities.CopiesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CopiesRepository extends JpaRepository<CopiesEntity, Integer>  {


}