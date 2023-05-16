package comMain.repositories;

import comMain.entities.ReserveEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

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

}