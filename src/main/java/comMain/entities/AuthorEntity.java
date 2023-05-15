package comMain.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Collection;
@Data
@Entity
@Table(name = "Author", schema = "dbo", catalog = "La_biblioteca")
public class AuthorEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID", nullable = false)
    private int id;
    @Basic
    @Column(name = "firstName", nullable = false)
    private int firstName;
    @Basic
    @Column(name = "lastName", nullable = false)
    private int lastName;


}
