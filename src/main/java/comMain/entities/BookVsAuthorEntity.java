package comMain.entities;

import jakarta.persistence.*;
import lombok.Data;


@Data
@Entity
@Table(name = "BookVSAuthor", schema = "dbo", catalog = "La_biblioteca")
public class BookVsAuthorEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID", nullable = false)
    private int id;
    @Basic
    @Column(name = "bookID", nullable = false)
    private int bookId;
    @Basic
    @Column(name = "AuthorID", nullable = false)
    private int authorId;

}
