
package com.emc.emergency.data.model;

import com.emc.emergency.data.repository.user_typeRepository;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.FetchMode;
import org.hibernate.annotations.*;
import org.hibernate.annotations.CascadeType;
import org.hibernate.validator.constraints.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.cdi.Eager;
import org.springframework.data.rest.core.annotation.RestResource;
import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="`User`")
public class User   {
/*    @Autowired
    user_typeRepository user_typeRepository;*/
    public User() {
    }

    public User(Long id_user, String username , User_Type user_type, String password) {
        this.id_user = id_user;
        this.username = username;

        this.user_type = user_type;
        this.password = password;
    }

    @Column(name="id_user", nullable=false, length=20)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_user;

    @Email
    @Column(name="username", nullable=false, length=50, unique = true)
    private String username;

    @Column(name="avatar", nullable=true)
    private String Avatar;
    @Column(name="token", nullable=true)
    private String token;
    /*@ManyToOne(fetch = FetchType.EAGER)
    @Cascade(value = {CascadeType.SAVE_UPDATE})*/
    @ManyToOne
    @JoinColumn(name = "id_user_type")
    private User_Type user_type;

    @OneToMany(mappedBy="id_user")
    //@Cascade(value = CascadeType.ALL)
    private List<Accident> accident = new ArrayList<>();

    @OneToMany(mappedBy="id_user")
   // @Cascade(value = CascadeType.ALL)
    private List<Chat> chat = new ArrayList<>();

    @OneToOne(mappedBy="id_user")
   // @Cascade(value = CascadeType.ALL)
    private Personal_Infomation personal_Infomation;
    //@JsonIgnore
    //@RestResource(exported = false)
    @Column(name="password", nullable=false, length=50 )
    private String password;

    private void setId_user(Long value) {
        this.id_user = value;
    }

    public Long getId_user() {
        return id_user;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
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

    public User_Type getUser_type() {
        return user_type;
    }

    public void setUser_type(User_Type user_type) {
        this.user_type = user_type;
    }

    public void setAccident(List<Accident> accident) {
        this.accident = accident;
    }

    public void setChat(List<Chat> chat) {
        this.chat = chat;
    }

    public Personal_Infomation getPersonal_Infomation() {
        return personal_Infomation;
    }

    public void setPersonal_Infomation(Personal_Infomation personal_Infomation) {
        this.personal_Infomation = personal_Infomation;
    }

    public String getAvatar() {
        return Avatar;
    }

    public void setAvatar(String avatar) {
        Avatar = avatar;
    }

    @Override
    public String toString() {
        return "User{" +
                "id_user=" + id_user +
                ", username='" + username + '\'' +
                ", user_type=" + user_type +
                '}';
    }
}
