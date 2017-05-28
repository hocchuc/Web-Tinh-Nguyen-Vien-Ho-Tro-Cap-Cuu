package com.emc.emergency.data.model;

import org.hibernate.annotations.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.Valid;

@Entity
@Table(name="Personal_Infomation")
public class Personal_Infomation implements Serializable  {
	public Personal_Infomation() {
	}

	public Personal_Infomation( String name_PI, Boolean sex__PI, Date birthday, Long personal_id, String work_location, Float long_PI, Float lat_PI, Long phone_PI, String address_PI, String email_PI) {

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
	}

	@Column(name="id_PI", nullable=false, length=20)
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_PI;



	@Column(name="name_PI", nullable=true, length=20)
	private String name_PI;

	@Column(name="`sex _PI`", nullable=true, length=1)
	private Boolean sex__PI;

	@Column(name="birthday", nullable=true)
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private java.util.Date birthday;

	@Column(name="personal_id", nullable=true, unique=true, length=15)
	private Long personal_id;

	@Column(name="work_location", nullable=true, length=100)
	private String work_location;

	@Column(name="long_PI", nullable=true)
	private Float long_PI;

	@Column(name="lat_PI", nullable=true)
	private Float lat_PI;

	@Column(name="`phone_PI`", nullable=true, length=15)
	private Long phone_PI;

	@Column(name="address_PI", nullable=true, length=50)
	private String address_PI;

	@Column(name="email_PI", nullable=true, length=50)
	private String email_PI;
	@OneToOne(fetch = FetchType.EAGER)
	//@JoinColumn(nullable = false,referencedColumnName = "id_user",name = "id_user")
	//@Cascade(value = {org.hibernate.annotations.CascadeType.PERSIST, org.hibernate.annotations.CascadeType.REFRESH, org.hibernate.annotations.CascadeType.MERGE})
	@JoinColumn(name = "id_user")
	private User id_user;

	@OneToMany(mappedBy="id_PI")
	//@Cascade(value = org.hibernate.annotations.CascadeType.ALL)
	private List<Medical_Info> medical_Info = new ArrayList<>();

	public Personal_Infomation(String name_pi, Boolean sex__pi, Date birthday, Long personal_id, String work_location, Float long_pi, Float lat_pi, Long phone_pi, String address_pi, String email_pi, List<Medical_Info> medical_info) {
	}

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

	public User getId_user() {
		return id_user;
	}

	public void setId_user(User id_user) {
		this.id_user = id_user;
	}

	public Long getPersonal_id() {
		return personal_id;
	}

	public void setPersonal_id(Long personal_id) {
		this.personal_id = personal_id;
	}

	public Long getPhone_PI() {
		return phone_PI;
	}

	public void setPhone_PI(Long phone_PI) {
		this.phone_PI = phone_PI;
	}

	public void setMedical_Info(List<Medical_Info> medical_Info) {
		this.medical_Info = medical_Info;
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

	@Override
	public String toString() {
		return "Personal_Infomation{" +
				"id_PI=" + id_PI +
				", id_user=" + id_user +
				", name_PI='" + name_PI + '\'' +
				", sex__PI=" + sex__PI +
				", birthday=" + birthday +
				", personal_id=" + personal_id +
				", work_location='" + work_location + '\'' +
				", long_PI=" + long_PI +
				", lat_PI=" + lat_PI +
				", phone_PI=" + phone_PI +
				", address_PI='" + address_PI + '\'' +
				", email_PI='" + email_PI + '\'' +
				", medical_Info=" + medical_Info +
				'}';
	}
}
