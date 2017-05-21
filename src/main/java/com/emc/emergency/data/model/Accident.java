package com.emc.emergency.data.model;

import org.hibernate.annotations.CascadeType;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="Accident")
public class Accident  {
	public Accident() {
	}

	@Column(name="id_AC", nullable=false, length=20)
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_AC;

	@ManyToOne
	private User id_user;

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

	@OneToMany(mappedBy="id_Acc")
	private List<Chat> chat = new ArrayList<>();

	@OneToMany(mappedBy="id_Acc")
	private List<Image> image = new ArrayList<>();

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

	public String toString() {
		return String.valueOf(getId_AC());
	}

}
