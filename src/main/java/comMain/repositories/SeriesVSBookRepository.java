package comMain.repositories;

import comMain.entities.SeriesVsBookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SeriesVSBookRepository extends JpaRepository<SeriesVsBookEntity, Integer> {
    @Procedure(name = "setBookToSeries")
    void setBookToSeries
            (@Param("BookSeries") String team, @Param("ISBN") String ISBN, @Param("BookIndexInSeries") int BookIndexInSeries);


    @Procedure(name = "deleteBookFromSeries")
    void deleteBookFromSeries
            (@Param("bookID") int bookID);


    @Procedure(name = "getSeriesByBook")
    List<Object[]> getSeriesByBook
            (@Param("bookID") int bookID);



}