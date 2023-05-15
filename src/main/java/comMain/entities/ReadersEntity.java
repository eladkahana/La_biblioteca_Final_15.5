package comMain.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Date;
import java.util.Collection;
@Data
@Entity
@Table(name = "Readers", schema = "dbo", catalog = "La_biblioteca")
public class ReadersEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID", nullable = false)
    private int id;
    @Basic
    @Column(name = "IDno", nullable = false, length = 9)
    private String iDno;
    @Basic
    @Column(name = "adress", nullable = false, length = 100)
    private String adress;
    @Basic
    @Column(name = "phoneNo", nullable = true, length = 10)
    private String phoneNo;
    @Basic
    @Column(name = "FirstName", nullable = false)
    private int firstName;
    @Basic
    @Column(name = "LastName", nullable = false)
    private int lastName;
    @Basic
    @Column(name = "birthDate", nullable = false)
    private Date birthDate;
    @Basic
    @Column(name = "registrationDate", nullable = false)
    private Date registrationDate;
    @Basic
    @Column(name = "genderID", nullable = false)
    private int genderId;
    @Basic
    @Column(name = "Email", nullable = false, length = 100)
    private String email;

}
