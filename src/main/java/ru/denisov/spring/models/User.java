package ru.denisov.spring.models;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

    @Column(name = "name", length = 30)
    @NotEmpty(message = "Имя не может быть пустым")
    @Size(min = 2,max = 30, message = "Имя должно содержать от 2 до 30 символов")
    private String name;

    @Column (name = "last_name", length = 60)
    @NotEmpty(message = "Фамилия не может быть пустым")
    @Size(min = 2,max = 60, message = "Фамилия должна содержать от 2 до 60 символов")
    private String lastName;

    @Column(name = "email", length = 60)
    @NotEmpty(message = "Email не может быть пустым")
    @Email(message = "Неправильный формат")
    private String email;

    @Column(name = "age")
    @Min(value = 0, message = "возраст должен быть больше 0")
    private int age;

    @ManyToMany(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinTable(name = "users_roles", joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

    public User(String name, String lastName, String email, int age, Set<Role> roles) {
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.age = age;
        this.roles = roles;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public User() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
