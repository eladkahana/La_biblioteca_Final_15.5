package comMain.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Collection;

@Data
@Entity
@Table(name = "LastName", schema = "dbo", catalog = "La_biblioteca")
public class LastNameEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID", nullable = false)
    private int id;
    @Basic
    @Column(name = "LastName", nullable = false, length = 50)
    private String lastName;

}
