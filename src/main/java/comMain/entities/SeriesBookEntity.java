package comMain.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Collection;
@Data
@Entity
@Table(name = "SeriesBook", schema = "dbo", catalog = "La_biblioteca")
public class SeriesBookEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID", nullable = false)
    private int id;
    @Basic
    @Column(name = "seriesTitle", nullable = false, length = 100)
    private String seriesTitle;

}
