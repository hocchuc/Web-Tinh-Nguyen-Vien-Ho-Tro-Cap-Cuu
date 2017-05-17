/**
 * "Visual Paradigm: DO NOT MODIFY THIS FILE!"
 * 
 * This is an automatic generated file. It will be regenerated every time 
 * you generate persistence class.
 * 
 * Modifying its content may cause the program not work, or your work may lost.
 */

/**
 * Licensee: 
 * License Type: Evaluation
 */
package com.emc.emergency.data.entity;

import java.io.Serializable;
import javax.persistence.*;
@Entity
@org.hibernate.annotations.Proxy(lazy=false)
@Table(name="`User`")
public class User implements Serializable {
	public User() {
	}
	
	@Column(name="id_user", nullable=false, length=20)	
	@Id	
	@GeneratedValue(generator="com.emc.emergency.data.entity_USER_ID_USER_GENERATOR")	
	@org.hibernate.annotations.GenericGenerator(name="com.emc.emergency.data.entity_USER_ID_USER_GENERATOR", strategy="native")	
	private int id_user;
	
	@Column(name="username", nullable=true, length=50)	
	private String username;
	
	@Column(name="password", nullable=true, length=50)	
	private String password;
	
	@ManyToOne(targetEntity=com.emc.emergency.data.entity.User_Type.class, fetch=FetchType.LAZY)	
	@org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.LOCK})	
	@JoinColumns({ @JoinColumn(name="id_user_type", referencedColumnName="id_user_type", nullable=false) })	
	private com.emc.emergency.data.entity.User_Type id_user_type;
	
	@OneToMany(mappedBy="id_user", targetEntity=com.emc.emergency.data.entity.Accident.class)	
	@org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.LOCK})	
	@org.hibernate.annotations.LazyCollection(org.hibernate.annotations.LazyCollectionOption.TRUE)	
	private java.util.Set accident = new java.util.HashSet();
	
	@OneToMany(mappedBy="id_user", targetEntity=com.emc.emergency.data.entity.Chat.class)	
	@org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.LOCK})	
	@org.hibernate.annotations.LazyCollection(org.hibernate.annotations.LazyCollectionOption.TRUE)	
	private java.util.Set chat = new java.util.HashSet();
	
	@OneToMany(mappedBy="user_id_user", targetEntity=com.emc.emergency.data.entity.Personal_Infomation.class)	
	@org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.LOCK})	
	@org.hibernate.annotations.LazyCollection(org.hibernate.annotations.LazyCollectionOption.TRUE)	
	private java.util.Set personal_Infomation = new java.util.HashSet();
	
	private void setId_user(int value) {
		this.id_user = value;
	}
	
	public int getId_user() {
		return id_user;
	}
	
	public int getORMID() {
		return getId_user();
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
	
	public void setId_user_type(com.emc.emergency.data.entity.User_Type value) {
		this.id_user_type = value;
	}
	
	public com.emc.emergency.data.entity.User_Type getId_user_type() {
		return id_user_type;
	}
	
	public void setAccident(java.util.Set value) {
		this.accident = value;
	}
	
	public java.util.Set getAccident() {
		return accident;
	}
	
	
	public void setChat(java.util.Set value) {
		this.chat = value;
	}
	
	public java.util.Set getChat() {
		return chat;
	}
	
	
	public void setPersonal_Infomation(java.util.Set value) {
		this.personal_Infomation = value;
	}
	
	public java.util.Set getPersonal_Infomation() {
		return personal_Infomation;
	}
	
	
	public String toString() {
		return String.valueOf(getId_user());
	}
	
}
