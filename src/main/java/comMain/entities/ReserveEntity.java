package comMain.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Date;
import java.util.Collection;
import java.util.List;

@NamedStoredProcedureQuery(name = "RserveDaysLeft", procedureName = "RserveDaysLeft",
        parameters =
       {@StoredProcedureParameter(name = "arrStr", type = String.class, mode = ParameterMode.IN),
        @StoredProcedureParameter(name = "LateTable", type = List.class, mode = ParameterMode.OUT)})

@Data
@Entity
@Table(name = "Reserve", schema = "dbo", catalog = "La_biblioteca")
public class ReserveEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID", nullable = false)
    private int id;
    @Basic
    @Column(name = "reserveDate", nullable = false)
    private Date reserveDate;
    @Basic
    @Column(name = "DueDate", nullable = false)
    private Date dueDate;
    @Basic
    @Column(name = "ReturnDate", nullable = true)
    private Date returnDate;
    @Basic
    @Column(name = "ReaderID", nullable = false)
    private int readerId;
    @Basic
    @Column(name = "CopyID", nullable = false)
    private int CopyID;
    @Basic
    @Column(name = "extensionID", nullable = true)
    private Integer extensionId;

}
