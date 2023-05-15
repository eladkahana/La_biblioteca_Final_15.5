package comMain.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Date;
import java.util.Arrays;
import java.util.Collection;
@Data
@Entity
@Table(name = "Book", schema = "dbo", catalog = "La_biblioteca")
public class BookEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID", nullable = false)
    private int id;
    @Basic
    @Column(name = "ISBN", nullable = false, length = 30)
    private String isbn;
    @Basic
    @Column(name = "title", nullable = false, length = 200)
    private String title;
    @Basic
    @Column(name = "edition", nullable = false, length = 50)
    private String edition;
    @Basic
    @Column(name = "shelfmarkID", nullable = false)
    private int shelfmarkId;
    @Basic
    @Column(name = "numberOfPages", nullable = false)
    private int numberOfPages;
    @Basic
    @Column(name = "publishYear", nullable = false)
    private int publishYear;
    @Basic
    @Column(name = "languageID", nullable = false)
    private int languageId;
    @Basic
    @Column(name = "publisherID", nullable = false)
    private int publisherId;
    @Basic
    @Column(name = "coverImage", nullable = true)
    private byte[] coverImage;
    @Basic
    @Column(name = "note", nullable = true, length = 2147483647)
    private String note;

}
