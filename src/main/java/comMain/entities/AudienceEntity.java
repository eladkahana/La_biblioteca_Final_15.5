package comMain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "Audience", schema = "dbo", catalog = "La_biblioteca")
public class AudienceEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID", nullable = false)
    private int id;
    @Basic
    @Column(name = "teamName", nullable = false, length = 20)
    private String teamName;
    @Basic
    @Column(name = "startAge", nullable = false)
    private int startAge;
    @Basic
    @Column(name = "EndAge", nullable = false)
    private int endAge;


}
