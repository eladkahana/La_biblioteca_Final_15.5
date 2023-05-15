package comMain.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Collection;
@Data
@Entity
@Table(name = "Publisher", schema = "dbo", catalog = "La_biblioteca")
public class PublisherEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID", nullable = false)
    private int id;
    @Basic
    @Column(name = "publisherName", nullable = false, length = 100)
    private String publisherName;

}
