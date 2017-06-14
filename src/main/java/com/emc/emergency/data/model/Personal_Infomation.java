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

	public Personal_Infomation( User id_user,
								String name_PI,
								Boolean sex__PI,
								Date birthday,
								Long personal_id,
								String work_location,
								String phone_PI,
								String address_PI,
								String email_PI) {
		this.id_user = id_user;
		this.name_PI = name_PI;
		this.sex__PI = sex__PI;
		this.birthday = birthday;
		this.personal_id = personal_id;
		this.work_location = work_location;
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
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private java.util.Date birthday;

	@Column(name="personal_id", nullable=true, unique=true, length=15)
	private Long personal_id;

	@Column(name="work_location", nullable=true, length=100)
	private String work_location;

	@Column(name="`phone_PI`", nullable=true, length=15)
	private String phone_PI;

	@Column(name="address_PI", nullable=true, length=50)
	private String address_PI;

	@Column(name="email_PI", nullable=true, length=50)
	private String email_PI;
	@OneToOne
	@JoinColumn(name = "id_user")
	private User id_user;

	@OneToMany(mappedBy="id_PI")
	private List<Medical_Info> medical_Info = new ArrayList<>();

	public Long getId_PI() {
		return id_PI;
	}

	public void setId_PI(Long id_PI) {
		this.id_PI = id_PI;
	}

	public String getName_PI() {
		return name_PI;
	}

	public void setName_PI(String name_PI) {
		this.name_PI = name_PI;
	}

	public Boolean getSex__PI() {
		return sex__PI;
	}

	public void setSex__PI(Boolean sex__PI) {
		this.sex__PI = sex__PI;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public Long getPersonal_id() {
		return personal_id;
	}

	public void setPersonal_id(Long personal_id) {
		this.personal_id = personal_id;
	}

	public String getWork_location() {
		return work_location;
	}

	public void setWork_location(String work_location) {
		this.work_location = work_location;
	}

	public String getPhone_PI() {
		return phone_PI;
	}

	public void setPhone_PI(String phone_PI) {
		this.phone_PI = phone_PI;
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

	public User getId_user() {
		return id_user;
	}

	public void setId_user(User id_user) {
		this.id_user = id_user;
	}

	public List<Medical_Info> getMedical_Info() {
		return medical_Info;
	}

	public void setMedical_Info(List<Medical_Info> medical_Info) {
		this.medical_Info = medical_Info;
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
				", phone_PI=" + phone_PI +
				", address_PI='" + address_PI + '\'' +
				", email_PI='" + email_PI + '\'' +
				", medical_Info=" + medical_Info +
				'}';
	}
}
