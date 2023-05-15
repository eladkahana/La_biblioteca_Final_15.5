package comMain.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Date;
import java.util.Collection;
@Data
@Entity
@Table(name = "Requests", schema = "dbo", catalog = "La_biblioteca")
public class RequestsEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID", nullable = false)
    private int id;
    @Basic
    @Column(name = "sendDate", nullable = false)
    private Date sendDate;
    @Basic
    @Column(name = "ReaderID", nullable = false)
    private int readerId;
    @Basic
    @Column(name = "ContactContent", nullable = false, length = 2147483647)
    private String contactContent;
    @Basic
    @Column(name = "IsChecked", nullable = false)
    private boolean isChecked;

    @Basic
    @Column(name = "topic", nullable = false)
    private String topic;

}
