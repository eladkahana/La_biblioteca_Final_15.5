package comMain.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Collection;
@Data
@Entity
@Table(name = "shelfmark", schema = "dbo", catalog = "La_biblioteca")
public class ShelfmarkEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID", nullable = false)
    private int id;
    @Basic
    @Column(name = "mark", nullable = false, length = 10)
    private String mark;

}
