package comMain.entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "Ranks", schema = "dbo", catalog = "La_biblioteca")
public class RanksEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID", nullable = false)
    private int id;
    @Basic
    @Column(name = "BookID", nullable = false)
    private int bookId;
    @Basic
    @Column(name = "ReaderID", nullable = false)
    private int readerId;
    @Basic
    @Column(name = "Rank", nullable = false)
    private int rank;

}
