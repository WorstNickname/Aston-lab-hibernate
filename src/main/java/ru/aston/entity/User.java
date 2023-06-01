package ru.aston.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

import static jakarta.persistence.CascadeType.*;
import static jakarta.persistence.FetchType.LAZY;
import static jakarta.persistence.GenerationType.IDENTITY;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString(exclude = {"roles", "department", "password"})
@EqualsAndHashCode(of = "email")
@Builder
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false, length = 64)
    private String email;

    @Column(nullable = false, length = 64)
    private String password;

    @ManyToOne(fetch = LAZY, cascade = PERSIST)
    @JoinColumn(name = "department_id")
    private Department department;

    @ManyToMany(cascade = PERSIST)
    @JoinTable(name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    @Builder.Default
    private Set<Role> roles = new HashSet<>();

    public void addRole(Role role) {
        roles.add(role);
        role.addUser(this);
    }

    public void removeRole(Role role) {
        roles.remove(role);
    }

}
