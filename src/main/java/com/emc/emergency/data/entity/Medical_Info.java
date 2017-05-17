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
@Table(name="Medical_Info")
public class Medical_Info implements Serializable {
	public Medical_Info() {
	}
	
	@Column(name="id_MI", nullable=false, length=20)	
	@Id	
	@GeneratedValue(generator="com.emc.emergency.data.entity_MEDICAL_INFO_ID_MI_GENERATOR")	
	@org.hibernate.annotations.GenericGenerator(name="com.emc.emergency.data.entity_MEDICAL_INFO_ID_MI_GENERATOR", strategy="native")	
	private int id_MI;
	
	@ManyToOne(targetEntity=com.emc.emergency.data.entity.Personal_Infomation.class, fetch=FetchType.LAZY)	
	@org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.LOCK})	
	@JoinColumns({ @JoinColumn(name="id_PI", referencedColumnName="id_PI", nullable=false) })	
	private com.emc.emergency.data.entity.Personal_Infomation id_PI;
	
	@Column(name="name_MI", nullable=true, length=50)	
	private String name_MI;
	
	@Column(name="type_MI", nullable=true, length=11)	
	private Integer type_MI;
	
	@Column(name="description", nullable=true, length=100)	
	private String description;
	
	/**
	 * T? t?ng
	 */
	private void setId_MI(int value) {
		this.id_MI = value;
	}
	
	/**
	 * T? t?ng
	 */
	public int getId_MI() {
		return id_MI;
	}
	
	public int getORMID() {
		return getId_MI();
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
	
	public void setId_PI(com.emc.emergency.data.entity.Personal_Infomation value) {
		this.id_PI = value;
	}
	
	public com.emc.emergency.data.entity.Personal_Infomation getId_PI() {
		return id_PI;
	}
	
	public String toString() {
		return String.valueOf(getId_MI());
	}
	
}
