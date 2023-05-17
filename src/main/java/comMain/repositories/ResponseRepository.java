package comMain.repositories;

import comMain.entities.ResponesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

public interface ResponseRepository extends JpaRepository<ResponesEntity, Integer> {


    @Procedure(name = "addResponse")
    void addResponse(@Param("requestID") Integer requestID, @Param("content") String content);



}