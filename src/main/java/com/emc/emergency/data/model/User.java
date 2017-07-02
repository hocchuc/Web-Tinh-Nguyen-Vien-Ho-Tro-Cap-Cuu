
package com.emc.emergency.data.model;

import org.hibernate.validator.constraints.Email;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="User")
public class User   {

    public User() {
    }
//    public User(Long id_user, String username ) {
//        this.id_user = id_user;
//        this.username = username;
//    }
//    public User(Long id_user, String username , User_Type user_type, String password) {
//        this.id_user = id_user;
//        this.username = username;
//        this.user_type = user_type;
//        this.password = password;
//    }


    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User(Long id_user, String username, User_Type user_type, String password, String token, Double lat_PI,Double long_PI) {
        this.id_user = id_user;
        this.username = username;
        this.user_type = user_type;
        this.password = password;
        this.token = token;
        this.long_PI = long_PI;
        this.lat_PI = lat_PI;
    }

//    public User(Long id_user, String username, String avatar, String token, User_Type user_type, Personal_Infomation personal_Infomation, String password, Double long_PI, Double lat_PI) {
//        this.id_user = id_user;
//        this.username = username;
//        Avatar = avatar;
//        this.token = token;
//        this.user_type = user_type;
//        this.personal_Infomation = personal_Infomation;
//        this.password = password;
//        this.long_PI = long_PI;
//        this.lat_PI = lat_PI;
//    }

    @Column(name="id_user", nullable=false, length=20)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_user;

    @Email
    @Column(name="username", nullable=false, length=50, unique = true)
    private String username;

    @ManyToOne
    @JoinColumn(name = "id_user_type")
    private User_Type user_type;

    @OneToMany(mappedBy="id_user")
    private List<Accident> accident = new ArrayList<>();

    @OneToMany(mappedBy="id_user")
    private List<Chat> chat = new ArrayList<>();

    @OneToOne(mappedBy="id_user")
    private Personal_Infomation personal_Infomation;

    @Column(name="password", nullable=false, length=50 )
    private String password;
    @Column(name="token", nullable=true, length=200 )
    private String token;


    @Column(name="long_PI", nullable=true)
    private Double long_PI;

    @Column(name="lat_PI", nullable=true)
    private Double lat_PI;

    public Double getLong_PI() {
        return long_PI;
    }

    public void setLong_PI(Double long_PI) {
        this.long_PI = long_PI;
    }

    public Double getLat_PI() {
        return lat_PI;
    }

    public void setLat_PI(Double lat_PI) {
        this.lat_PI = lat_PI;
    }

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



    @Override
    public String toString() {
        return "User{" +
                "id_user=" + id_user +
                ", username='" + username + '\'' +
                ", user_type=" + user_type +
                '}';
    }
}
