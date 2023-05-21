package comMain.repositories;

import comMain.entities.AudienceVsBookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AudienceVSBookRepository extends JpaRepository<AudienceVsBookEntity, Integer>{


    @Procedure(name = "setAudienceToBook")
    void setAudienceToBook
            (@Param("team") String team, @Param("ISBN") String ISBN);


    @Procedure(name = "deleteAudienceFromBook")
    void deleteAudienceFromBook
            (@Param("bookID") int bookID);



    @Procedure(name = "getAllAudiencesByBook")
    List<AudienceVsBookEntity> getAllAudiencesByBook
            (@Param("bookID") int bookID);

}