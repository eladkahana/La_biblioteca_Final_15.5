package comMain.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Collection;

@Data
@Entity
@Table(name = "FirstName", schema = "dbo", catalog = "La_biblioteca")
public class FirstNameEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID", nullable = false)
    private int id;
    @Basic
    @Column(name = "FirstName", nullable = false, length = 50)
    private String firstName;

}
