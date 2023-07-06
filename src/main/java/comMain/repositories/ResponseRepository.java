package comMain.repositories;

import comMain.entities.RequestsEntity;
import comMain.entities.ResponesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ResponseRepository extends JpaRepository<ResponesEntity, Integer> {


    @Procedure(name = "addResponse")
    void addResponse(@Param("requestID") Integer requestID, @Param("content") String content);


    @Procedure(name = "getResponse")
    List<Object[]> getResponse();


}