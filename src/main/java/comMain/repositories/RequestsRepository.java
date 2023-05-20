package comMain.repositories;

import comMain.entities.RequestsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RequestsRepository extends JpaRepository<RequestsEntity, Integer>  {

    @Procedure(name = "getCheckedRequests")
    List<RequestsEntity> getCheckedRequests();

    @Procedure(name = "getUnCheckedRequests")
     List<RequestsEntity> getUnCheckedRequests();

    @Procedure(name = "requestChecked")
    void requestChecked(@Param("requestID") int requestID);


    @Procedure(name = "AddRequest")
    List<Object[]> AddRequest(@Param("readerID") int readerID,
                    @Param("content") String content,
                    @Param("topic") String topic);

}