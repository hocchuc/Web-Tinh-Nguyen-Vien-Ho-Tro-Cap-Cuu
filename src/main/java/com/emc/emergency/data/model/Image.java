package com.emc.emergency.data.model;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name="Image")
public class Image  {
	public Image() {
	}

	@Column(name="id_image", nullable=false, length=20)
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_image;

	@ManyToOne
	private Accident id_Acc;

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

	public void setId_Acc(Accident value) {
		this.id_Acc = value;
	}

	public Accident getId_Acc() {
		return id_Acc;
	}

	public String toString() {
		return String.valueOf(getId_image());
	}

}

