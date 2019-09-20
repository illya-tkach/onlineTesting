package net.onlinetesting.model;


import lombok.*;
import org.hibernate.validator.constraints.NotEmpty;
import javax.persistence.*;
import java.util.Set;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

//    @Pattern(regexp="^\\d*$", message = "Password must be numeric")
    @Column(name = "password", nullable = false)
    private String password;

    @Transient
    private String confirmPassword;

    @Column(name = "firstName", nullable = false)
    private String firstName;

    @Column(name = "lastName", nullable = false)
    private String lastName;

    @NotEmpty(message = "{user.email.empty}")
    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @ManyToMany
    @JoinTable (name = "user_roles", joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "roles_id"))
    private Set<Role> roles;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<TestRating> testRatings;

}