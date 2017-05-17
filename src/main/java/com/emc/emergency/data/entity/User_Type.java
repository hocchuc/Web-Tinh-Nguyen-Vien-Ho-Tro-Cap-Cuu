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
@Table(name="User_Type")
public class User_Type implements Serializable {
	public User_Type() {
	}
	
	@Column(name="id_user_type", nullable=false, length=20)	
	@Id	
	@GeneratedValue(generator="com.emc.emergency.data.entity_USER_TYPE_ID_USER_TYPE_GENERATOR")
	@org.hibernate.annotations.GenericGenerator(name="com.emc.emergency.data.entity_USER_TYPE_ID_USER_TYPE_GENERATOR", strategy="native")
	private int id_user_type;
	
	@Column(name="name_user_type", nullable=true, length=50)	
	private String name_user_type;
	
	@OneToMany(mappedBy="id_user_type", targetEntity=com.emc.emergency.data.entity.User.class)	
	@org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.LOCK})	
	@org.hibernate.annotations.LazyCollection(org.hibernate.annotations.LazyCollectionOption.TRUE)	
	private java.util.Set user = new java.util.HashSet();
	
	private void setId_user_type(int value) {
		this.id_user_type = value;
	}
	
	public int getId_user_type() {
		return id_user_type;
	}
	
	public int getORMID() {
		return getId_user_type();
	}
	
	public void setName_user_type(String value) {
		this.name_user_type = value;
	}
	
	public String getName_user_type() {
		return name_user_type;
	}
	
	public void setUser(java.util.Set value) {
		this.user = value;
	}
	
	public java.util.Set getUser() {
		return user;
	}
	
	
	public String toString() {
		return String.valueOf(getId_user_type());
	}
	
}
