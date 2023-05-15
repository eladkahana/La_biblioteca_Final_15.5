package comMain.entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "AudienceVSBook", schema = "dbo", catalog = "La_biblioteca")
public class AudienceVsBookEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID", nullable = false)
    private int id;
    @Basic
    @Column(name = "groupID", nullable = false)
    private int groupId;
    @Basic
    @Column(name = "BookID", nullable = false)
    private int bookId;


}
