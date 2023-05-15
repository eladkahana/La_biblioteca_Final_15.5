package comMain.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Date;
@Data
@Entity
@Table(name = "Respones", schema = "dbo", catalog = "La_biblioteca")
public class ResponesEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID", nullable = false)
    private int id;
    @Basic
    @Column(name = "answer", nullable = false, length = 2147483647)
    private String answer;
    @Basic
    @Column(name = "sendDate", nullable = false)
    private Date sendDate;
    @Basic
    @Column(name = "requestID", nullable = false)
    private int requestId;

}
