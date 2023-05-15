package comMain.entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "BookVSCategory", schema = "dbo", catalog = "La_biblioteca")
public class BookVsCategoryEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID", nullable = false)
    private int id;
    @Basic
    @Column(name = "CategoryID", nullable = false)
    private int categoryId;
    @Basic
    @Column(name = "BookID", nullable = false)
    private int bookId;

}
