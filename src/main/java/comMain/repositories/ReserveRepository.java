package comMain.repositories;

import comMain.entities.ReserveEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import java.sql.Date;
import java.util.List;

public interface ReserveRepository extends JpaRepository<ReserveEntity, Integer>  {

    @Procedure(name = "ReserveDaysLeft")
    List<Object[]> ReserveDaysLeft
            (@Param("arrStr") String arrStr);


    @Procedure(name = "MonthlyResrvesAmount")
    List<Object[]> MonthlyResrvesAmount();


    @Procedure(name = "todayResrvesAmount")
    List<Object[]> todayResrvesAmount();

    @Procedure(name = "displayReservesByDays")
    List<Object[]> displayReservesByDays();

    @Procedure(name = "displayReservesByHours")
    List<Object[]> displayReservesByHours();


    @Procedure(name = "getAllReservations")
    List<Object[]> getAllReservations();

    @Procedure(name = "AddReserve")
    void AddReserve(@Param("readerIDno") String readerIDno, @Param("BookID") Integer BookID, @Param("dueTo") Date dueTo);


    @Procedure(name = "AddRank")
    void AddRank(@Param("IDno") String IDno, @Param("copyID") Integer copyID, @Param("rank") Integer rank);

    @Procedure(name = "AddReturnBook")
    void AddReturnBook(@Param("copyID") Integer copyID, @Param("readerIDno") String readerIDno);


    @Procedure(name = "getHistoryOfReader")
    List<Object[]> getHistoryOfReader(@Param("readerID") Integer readerID);


}