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
@Table(name="Accident")
public class Accident implements Serializable {
	public Accident() {
	}
	
	@Column(name="id_AC", nullable=false, length=20)	
	@Id	
	@GeneratedValue(generator="com.emc.emergency.data.entity_ACCIDENT_ID_AC_GENERATOR")	
	@org.hibernate.annotations.GenericGenerator(name="com.emc.emergency.data.entity_ACCIDENT_ID_AC_GENERATOR", strategy="native")	
	private int id_AC;
	
	@ManyToOne(targetEntity=com.emc.emergency.data.entity.User.class, fetch=FetchType.LAZY)	
	@org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.LOCK})	
	@JoinColumns({ @JoinColumn(name="id_user", referencedColumnName="id_user", nullable=false) })	
	private com.emc.emergency.data.entity.User id_user;
	
	@Column(name="description_AC", nullable=true, length=200)	
	private String description_AC;
	
	@Column(name="date_AC", nullable=true)	
	@Temporal(TemporalType.DATE)	
	private java.util.Date date_AC;
	
	@Column(name="long_AC", nullable=true)	
	private Float long_AC;
	
	@Column(name="lat_AC", nullable=true, length=10)	
	private Integer lat_AC;
	
	@Column(name="status_AC", nullable=true, length=50)	
	private String status_AC;
	
	@OneToMany(mappedBy="id_Acc", targetEntity=com.emc.emergency.data.entity.Chat.class)	
	@org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.LOCK})	
	@org.hibernate.annotations.LazyCollection(org.hibernate.annotations.LazyCollectionOption.TRUE)	
	private java.util.Set chat = new java.util.HashSet();
	
	@OneToMany(mappedBy="id_Acc", targetEntity=com.emc.emergency.data.entity.Image.class)	
	@org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.LOCK})	
	@org.hibernate.annotations.LazyCollection(org.hibernate.annotations.LazyCollectionOption.TRUE)	
	private java.util.Set image = new java.util.HashSet();
	
	private void setId_AC(int value) {
		this.id_AC = value;
	}
	
	public int getId_AC() {
		return id_AC;
	}
	
	public int getORMID() {
		return getId_AC();
	}
	
	public void setDescription_AC(String value) {
		this.description_AC = value;
	}
	
	public String getDescription_AC() {
		return description_AC;
	}
	
	public void setDate_AC(java.util.Date value) {
		this.date_AC = value;
	}
	
	public java.util.Date getDate_AC() {
		return date_AC;
	}
	
	public void setLong_AC(float value) {
		setLong_AC(new Float(value));
	}
	
	public void setLong_AC(Float value) {
		this.long_AC = value;
	}
	
	public Float getLong_AC() {
		return long_AC;
	}
	
	public void setLat_AC(int value) {
		setLat_AC(new Integer(value));
	}
	
	public void setLat_AC(Integer value) {
		this.lat_AC = value;
	}
	
	public Integer getLat_AC() {
		return lat_AC;
	}
	
	public void setStatus_AC(String value) {
		this.status_AC = value;
	}
	
	public String getStatus_AC() {
		return status_AC;
	}
	
	public void setId_user(com.emc.emergency.data.entity.User value) {
		this.id_user = value;
	}
	
	public com.emc.emergency.data.entity.User getId_user() {
		return id_user;
	}
	
	public void setChat(java.util.Set value) {
		this.chat = value;
	}
	
	public java.util.Set getChat() {
		return chat;
	}
	
	
	public void setImage(java.util.Set value) {
		this.image = value;
	}
	
	public java.util.Set getImage() {
		return image;
	}
	
	
	public String toString() {
		return String.valueOf(getId_AC());
	}
	
}
