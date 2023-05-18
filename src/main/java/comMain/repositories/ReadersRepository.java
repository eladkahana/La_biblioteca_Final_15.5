package comMain.repositories;

import comMain.entities.ReadersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import java.sql.Date;
import java.util.List;

public interface ReadersRepository extends JpaRepository<ReadersEntity, Integer> {
    @Procedure(name = "addReader")
    List<Object[]> addReader
            (@Param("ID") String ID,
             @Param("adress") String adress,
             @Param("phoneNo") String phoneNo,
             @Param("firstName") String firstName,
             @Param("lastName") String lastName,
             @Param("birthDate") java.sql.Date birthDate,
             @Param("gender") String gender,
             @Param("Email") String Email);


    @Procedure(procedureName = "LogIn.TryToConnect")
    Integer TryToConnect(@Param("IP") String IP,
                         @Param("Mac") String Mac,
                         @Param("UserName") String UserName,
                         @Param("Password") String Password);

}