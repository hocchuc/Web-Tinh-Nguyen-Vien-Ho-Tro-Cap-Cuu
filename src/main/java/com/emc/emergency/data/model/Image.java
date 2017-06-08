package com.emc.emergency.data.model;

import org.hibernate.annotations.*;
import org.hibernate.annotations.CascadeType;

import java.io.Serializable;
import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="Image")
public class Image  {
	public Image() {
	}
	@Column(name="id_image", nullable=false, length=20)
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private Long id_image;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_AC")
	private Accident id_AC;

	@Column(name="link", nullable=true, length=50)
	private String link;

	private void setId_image(Long value) {
		this.id_image = value;
	}

	public Long getId_image() {
		return id_image;
	}

	public Long getORMID() {
		return getId_image();
	}

	public void setLink(String value) {
		this.link = value;
	}

	public String getLink() {
		return link;
	}

	public void setId_AC(Accident value) {
		this.id_AC = value;
	}

	public Accident getId_AC() {
		return id_AC;
	}

	public String toString() {
		return String.valueOf(getId_image());
	}

}

