package chaplinskiy.jdbccrud.model;

import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "User")
@Table(name = "user")
@ToString
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    @ToString.Exclude
    private Long id;
    private String firstName;
    private String lastName;

    @OneToMany (fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(
            name="user_post",
            joinColumns = @JoinColumn( name="id_user"),
            inverseJoinColumns = @JoinColumn( name="id_post")
    )
    @ToString.Exclude
    private List<Post> posts = new ArrayList<>();

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(
            name = "region_id",
            nullable = false
    )
    private Region region;

    @Enumerated(EnumType.STRING)
    @Column(length = 16)
    private Role role;
}
