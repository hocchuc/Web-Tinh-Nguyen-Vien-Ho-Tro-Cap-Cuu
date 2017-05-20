
package com.emc.emergency.model;

import javax.persistence.*;
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

    @Column(name="username", nullable=true, length=50)
    private String username;

    @ManyToOne
    private User_Type id_user_type;

    @OneToMany(mappedBy="id_user")
    private List<Accident> accident = new ArrayList<>();

    @OneToMany(mappedBy="id_user")
    private List<Chat> chat = new ArrayList<>();

    @OneToMany(mappedBy="user_id_user")
    private List<Personal_Infomation> personal_Infomation = new ArrayList<>();

    @Column(name="password", nullable=false, length=50)
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
        this.id_user_type = value;
    }

    public User_Type getId_user_type() {
        return id_user_type;
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

    public String toString() {
        return String.valueOf(getId_user());
    }

}
