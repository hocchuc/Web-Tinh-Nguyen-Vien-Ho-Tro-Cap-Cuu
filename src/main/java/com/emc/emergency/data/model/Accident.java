package com.emc.emergency.data.model;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="Accident")
public class Accident  {
	public Accident() {
	}

	public Accident(Long id_AC, String description_AC, Date date_AC, Float lat_AC , Float long_AC,String status_AC, String adress) {
		this.id_AC = id_AC;
		this.description_AC = description_AC;
		this.date_AC = date_AC;
		this.long_AC = long_AC;
		this.lat_AC = lat_AC;
		this.status_AC = status_AC;
		Adress = adress;
	}

	public Accident(Long id_AC, User id_user, String description_AC, Date date_AC,  Float lat_AC , Float long_AC, String status_AC, List<Chat> chat, List<Image> image, String adress) {
		this.id_AC = id_AC;
		this.id_user = id_user;
		this.description_AC = description_AC;
		this.date_AC = date_AC;
		this.long_AC = long_AC;
		this.lat_AC = lat_AC;
		this.status_AC = status_AC;
		this.chat = chat;
		this.image = image;
		Adress = adress;
	}

	@Column(name="id_AC", nullable=false, length=20)
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_AC;

	@ManyToOne
	@JoinColumn(name = "id_user")
	private User id_user;

	@Column(name="description_AC", nullable=true, length=200)
	private String description_AC;

	@Column(name="date_AC", nullable=true)
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/yyyy 'at' hh:mm:ss a")
	private Date date_AC;

	@Column(name="long_AC", nullable=true)
	private Float long_AC;

	@Column(name="lat_AC", nullable=true, length=10)
	private Float lat_AC;

	@Column(name="status_AC", nullable=true, length=50)
	private String status_AC;

	@OneToMany(mappedBy="id_AC")
	private List<Chat> chat = new ArrayList<>();

	@OneToMany(mappedBy="id_AC")
	private List<Image> image = new ArrayList<>();

	@Column(name="addess", nullable=true)
	private String Adress;

	private void setId_AC(Long value) {
		this.id_AC = value;
	}

	public Long getId_AC() {
		return id_AC;
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

	public void setLat_AC(Float value) {
		this.lat_AC = value;
	}

	public Float getLat_AC() {
		return lat_AC;
	}

	public void setStatus_AC(String value) {
		this.status_AC = value;
	}

	public String getStatus_AC() {
		return status_AC;
	}

	public void setId_user(User value) {
		this.id_user = value;
	}

	public User getId_user() {
		return id_user;
	}

	public List<Chat> getChat() {
		return chat;
	}

	public List<Image> getImage() {
		return image;
	}


	public String getAdress() {
		return Adress;
	}

	public void setAdress(String adress) {
		Adress = adress;
	}

	@Override
	public String toString() {
		return "Accident{" +
				"id_AC=" + id_AC +
				", id_user=" + id_user +
				", description_AC='" + description_AC + '\'' +
				", date_AC=" + date_AC +
				", long_AC=" + long_AC +
				", lat_AC=" + lat_AC +
				", status_AC='" + status_AC + '\'' +
				", Adress='" + Adress + '\'' +
				'}';
	}
}
