
package com.emc.emergency.data.model;

import org.hibernate.FetchMode;
import org.hibernate.annotations.*;
import org.hibernate.annotations.CascadeType;
import org.springframework.data.repository.cdi.Eager;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="`User`")
public class User  {
    public User() {
    }

    @Column(name="id_user", nullable=false, length=20)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_user;

    @Column(name="username", nullable=false, length=50, unique = true)
    private String username;

    @ManyToOne(fetch = FetchType.LAZY)
    @Cascade(value = CascadeType.PERSIST)
    private User_Type user_type;

    @OneToMany(mappedBy="id_user")
    private List<Accident> accident = new ArrayList<>();

    @OneToMany(mappedBy="id_user")
    private List<Chat> chat = new ArrayList<>();

    @OneToMany(mappedBy="id_user")
    private List<Personal_Infomation> personal_Infomation = new ArrayList<>();

    @Column(name="password", nullable=false, length=50 )
    private String password;

    private void setId_user(Long value) {
        this.id_user = value;
    }

    public Long getId_user() {
        return id_user;
    }


    public void setUsername(String value) {
        this.username = value;
    }

    public String getUsername() {
        return username;
    }

    public void setPassword(String value) {
        this.password = value;
    }

    public String getPassword() {
        return password;
    }

    public void setId_user_type(User_Type value) {
        this.user_type = value;
    }

    public User_Type getId_user_type() {
        return user_type;
    }

    public List<Accident> getAccident() {
        return accident;
    }

    public List<Chat> getChat() {
        return chat;
    }

    public List<Personal_Infomation> getPersonal_Infomation() {
        return personal_Infomation;
    }

    @Override
    public String toString() {
        return "User{" +
                "id_user=" + id_user +
                ", username='" + username + '\'' +
                ", id_user_type=" + user_type +
                ", accident=" + accident +
                ", chat=" + chat +
                ", personal_Infomation=" + personal_Infomation +
                ", password='" + password + '\'' +
                '}';
    }
}
