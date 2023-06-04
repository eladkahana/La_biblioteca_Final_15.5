package comMain.entities;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table(name = "TryLogin")
public class TryLoginEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "IP", nullable = false, length = 100)
    private String ip;

    @Column(name = "Mac", nullable = false , length = 100)
    private String mac;

    @Column(name = "LoginTime", nullable = false)
    private Date loginTime;

    @Column(name = "UserName", nullable = false, length = 100)
    private String userName;

    @Column(name = "Password", nullable = false, length = 100)
    private String password;

    @Column(name = "userID")
    private Integer userID;

}
