package com.emc.emergency.data.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.*;

@Entity
@Table(name="Personal_Infomation")
public class Personal_Infomation  {
	public Personal_Infomation() {
	}

	public Personal_Infomation(Long id_PI, User id_user, String name_PI, Boolean sex__PI, Date birthday, Integer personal_id, String work_location, Float long_PI, Float lat_PI, Integer phone_PI, String address_PI, String email_PI, List<Medical_Info> medical_Info) {
		this.id_PI = id_PI;
		this.id_user = id_user;
		this.name_PI = name_PI;
		this.sex__PI = sex__PI;
		this.birthday = birthday;
		this.personal_id = personal_id;
		this.work_location = work_location;
		this.long_PI = long_PI;
		this.lat_PI = lat_PI;
		this.phone_PI = phone_PI;
		this.address_PI = address_PI;
		this.email_PI = email_PI;
		this.medical_Info = medical_Info;
	}

	@Column(name="id_PI", nullable=false, length=20)
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_PI;

	@ManyToOne
	private User id_user;

	@Column(name="name_PI", nullable=true, length=20)
	private String name_PI;

	@Column(name="`sex _PI`", nullable=true, length=1)
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

	@Column(name="`phone_PI`", nullable=true, length=11)
	private Integer phone_PI;

	@Column(name="address_PI", nullable=true, length=50)
	private String address_PI;

	@Column(name="email_PI", nullable=true, length=50)
	private String email_PI;

	@OneToMany(mappedBy="id_PI")
	private List<Medical_Info> medical_Info = new ArrayList<>();

	private void setId_PI(Long value) {
		this.id_PI = value;
	}

	public Long getId_PI() {
		return id_PI;
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

	public void setPhone_PI(Integer value) {
		this.phone_PI = value;
	}

	public Integer getPhone_PI() {
		return phone_PI;
	}

	public void setAddress_PI_varchar(String value) {
		this.address_PI= value;
	}

	public String getAddress_PI_varchar() {
		return address_PI;
	}

	public void setUser_id_user(User value) {
		this.id_user = value;
	}

	public User getUser_id_user() {
		return id_user;
	}

	public List<Medical_Info> getMedical_Info() {
		return medical_Info;
	}


	public String toString() {
		return String.valueOf(getId_PI());
	}

	public String getAddress_PI() {
		return address_PI;
	}

	public void setAddress_PI(String address_PI) {
		this.address_PI = address_PI;
	}

	public String getEmail_PI() {
		return email_PI;
	}

	public void setEmail_PI(String email_PI) {
		this.email_PI = email_PI;
	}
}
