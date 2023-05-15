package comMain.entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "SeriesVSBook", schema = "dbo", catalog = "La_biblioteca")
public class SeriesVsBookEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID", nullable = false)
    private int id;
    @Basic
    @Column(name = "BookSeriesID", nullable = false)
    private int bookSeriesId;
    @Basic
    @Column(name = "BookID", nullable = false)
    private int bookId;
    @Basic
    @Column(name = "BookIndexInSeries", nullable = false)
    private int bookIndexInSeries;

}
