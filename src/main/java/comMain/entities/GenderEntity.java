package comMain.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Collection;

@Data
@Entity
@Table(name = "Gender", schema = "dbo", catalog = "La_biblioteca")
public class GenderEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID", nullable = false)
    private int id;
    @Basic
    @Column(name = "gender", nullable = false, length = 10)
    private String gender;

}
