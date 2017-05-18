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
@Table(name="Personal_Infomation")
public class Personal_Infomation implements Serializable {
	public Personal_Infomation() {
	}
	
	@Column(name="id_PI", nullable=false, length=20)	
	@Id	
	@GeneratedValue(generator="com.emc.emergency.data.entity_PERSONAL_INFOMATION_ID_PI_GENERATOR")	
	@org.hibernate.annotations.GenericGenerator(name="com.emc.emergency.data.entity_PERSONAL_INFOMATION_ID_PI_GENERATOR", strategy="native")	
	private int id_PI;
	
	@ManyToOne(targetEntity=com.emc.emergency.data.entity.User.class, fetch=FetchType.LAZY)	
	@org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.LOCK})	
	@JoinColumns({ @JoinColumn(name="User_id_user", referencedColumnName="id_user", nullable=false) })	
	private com.emc.emergency.data.entity.User user_id_user;
	
	@Column(name="name_PI", nullable=true, length=20)	
	private String name_PI;
	
	@Column(name="`sex_PI`", nullable=true, length=1)
	private Boolean sex__PI;
	
	@Column(name="birthday", nullable=true)	
	@Temporal(TemporalType.DATE)	
	private java.util.Date birthday;
	
	@Column(name="personal_id", nullable=true, unique=true, length=15)	
	private Integer personal_id;
	
	@Column(name="work_location", nullable=true, length=100)	
	private String work_location;
	
	@Column(name="long_PI", nullable=true)	
	private Float long_PI;
	
	@Column(name="lat_PI", nullable=true)	
	private Float lat_PI;
	
	@Column(name="phone_PI", nullable=true, length=11)
	private Integer phone_PI_int;
	
	@Column(name="`address_PI", nullable=true, length=50)
	private String address_PI_varchar;
	
	@OneToMany(mappedBy="id_PI", targetEntity=com.emc.emergency.data.entity.Medical_Info.class)	
	@org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.LOCK})	
	@org.hibernate.annotations.LazyCollection(org.hibernate.annotations.LazyCollectionOption.TRUE)	
	private java.util.Set medical_Info = new java.util.HashSet();
	
	private void setId_PI(int value) {
		this.id_PI = value;
	}
	
	public int getId_PI() {
		return id_PI;
	}
	
	public int getORMID() {
		return getId_PI();
	}
	
	public void setName_PI(String value) {
		this.name_PI = value;
	}
	
	public String getName_PI() {
		return name_PI;
	}
	
	public void setSex__PI(boolean value) {
		setSex__PI(new Boolean(value));
	}
	
	public void setSex__PI(Boolean value) {
		this.sex__PI = value;
	}
	
	public Boolean getSex__PI() {
		return sex__PI;
	}
	
	public void setBirthday(java.util.Date value) {
		this.birthday = value;
	}
	
	public java.util.Date getBirthday() {
		return birthday;
	}
	
	public void setPersonal_id(int value) {
		setPersonal_id(new Integer(value));
	}
	
	public void setPersonal_id(Integer value) {
		this.personal_id = value;
	}
	
	public Integer getPersonal_id() {
		return personal_id;
	}
	
	public void setWork_location(String value) {
		this.work_location = value;
	}
	
	public String getWork_location() {
		return work_location;
	}
	
	public void setLong_PI(float value) {
		setLong_PI(new Float(value));
	}
	
	public void setLong_PI(Float value) {
		this.long_PI = value;
	}
	
	public Float getLong_PI() {
		return long_PI;
	}
	
	public void setLat_PI(float value) {
		setLat_PI(new Float(value));
	}
	
	public void setLat_PI(Float value) {
		this.lat_PI = value;
	}
	
	public Float getLat_PI() {
		return lat_PI;
	}
	
	public void setPhone_PI_int(int value) {
		setPhone_PI_int(new Integer(value));
	}
	
	public void setPhone_PI_int(Integer value) {
		this.phone_PI_int = value;
	}
	
	public Integer getPhone_PI_int() {
		return phone_PI_int;
	}
	
	public void setAddress_PI_varchar(String value) {
		this.address_PI_varchar = value;
	}
	
	public String getAddress_PI_varchar() {
		return address_PI_varchar;
	}
	
	public void setUser_id_user(com.emc.emergency.data.entity.User value) {
		this.user_id_user = value;
	}
	
	public com.emc.emergency.data.entity.User getUser_id_user() {
		return user_id_user;
	}
	
	public void setMedical_Info(java.util.Set value) {
		this.medical_Info = value;
	}
	
	public java.util.Set getMedical_Info() {
		return medical_Info;
	}
	
	
	public String toString() {
		return String.valueOf(getId_PI());
	}
	
}
