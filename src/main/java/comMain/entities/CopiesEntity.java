package comMain.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Date;

@Data
@Entity
@Table(name = "Copies", schema = "dbo", catalog = "La_biblioteca")
public class CopiesEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID", nullable = false)
    private int id;
    @Basic
    @Column(name = "originalID", nullable = false)
    private int originalId;
    @Basic
    @Column(name = "insertDate", nullable = false)
    private Date insertDate;
    @Basic
    @Column(name = "isExist", nullable = false)
    private boolean isExist;

}
