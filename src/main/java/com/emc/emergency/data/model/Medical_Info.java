
package com.emc.emergency.data.model;


import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="Medical_Info")
public class Medical_Info  {
	public Medical_Info() {
	}

	@Column(name="id_MI", nullable=false, length=20)
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_MI;

	@ManyToOne
	/*@Cascade(value = {org.hibernate.annotations.CascadeType.PERSIST, org.hibernate.annotations.CascadeType.REFRESH, org.hibernate.annotations.CascadeType.MERGE})
	@JoinColumn(nullable = false,referencedColumnName = "id_PI",name = "id_PI")*/
	@JoinColumn(name = "id_PI")
	private Personal_Infomation id_PI;

	@Column(name="name_MI", nullable=true, length=50)
	private String name_MI;

	@Column(name="type_MI", nullable=true, length=11)
	private Integer type_MI;

	@Column(name="description", nullable=true, length=100)
	private String description;

	private void setId_MI(Long value) {
		this.id_MI = value;
	}


	public Long getId_MI() {
		return id_MI;
	}

	public void setName_MI(String value) {
		this.name_MI = value;
	}

	public String getName_MI() {
		return name_MI;
	}

	public void setType_MI(int value) {
		setType_MI(new Integer(value));
	}

	public void setType_MI(Integer value) {
		this.type_MI = value;
	}

	public Integer getType_MI() {
		return type_MI;
	}

	public void setDescription(String value) {
		this.description = value;
	}

	public String getDescription() {
		return description;
	}

	public void setId_PI(Personal_Infomation value) {
		this.id_PI = value;
	}

	public Personal_Infomation getId_PI() {
		return id_PI;
	}

	@Override
	public String toString() {
		return "Medical_Info{" +
				"id_MI=" + id_MI +
				", id_PI=" + id_PI +
				", name_MI='" + name_MI + '\'' +
				", type_MI=" + type_MI +
				", description='" + description + '\'' +
				'}';
	}
}
