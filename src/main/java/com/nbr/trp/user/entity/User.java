package com.nbr.trp.user.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Setter
@Getter
@NoArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(columnDefinition = "uniqueidentifier default newid()")
    private String uuid;


    @Column(name = "Username",nullable = false,unique = true)
    public String Username;

    @Column(name = "Password")
    public String Password;

    @Column(name = "FirstName")
    public String FirstName;

    @Column(name = "LastName")
    public String LastName;

    @Column(name = "Email")
    public String Email;

    @Column(name = "AddedBy")
    public String AddedBy;

    @Column(name = "AddedDate")
    @CreationTimestamp
    public Date AddedDate;

    @Column(name = "AddedFromIP")
    public String AddedFromIP;

    @Column(name = "UpdatedBy")
    public String UpdatedBy;

    @Column(name = "UpdatedDate")
    @UpdateTimestamp
    public Date  UpdatedDate;

    @Column(name = "UpdatedFromIP")
    public String UpdatedFromIP;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles = new HashSet<>();

    public User(String username, String password, String Firstname, String Lastname, String email, String AddedBy, String AddedFromIp, Set<Role> roles) {
        //this.id = id;
        this.Username = username;
        this.Email = email;
        this.Password = password;
        this.FirstName = Firstname;
        this.LastName = Lastname;
        this.AddedBy = AddedBy;
        this.AddedFromIP = AddedFromIp;
        this.roles = roles;
    }

}
