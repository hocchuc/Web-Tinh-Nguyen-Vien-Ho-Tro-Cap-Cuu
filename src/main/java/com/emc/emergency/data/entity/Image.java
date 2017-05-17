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
@Table(name="Image")
public class Image implements Serializable {
	public Image() {
	}
	
	@Column(name="id_image", nullable=false, length=20)	
	@Id	
	@GeneratedValue(generator="com.emc.emergency.data.entity_IMAGE_ID_IMAGE_GENERATOR")	
	@org.hibernate.annotations.GenericGenerator(name="com.emc.emergency.data.entity_IMAGE_ID_IMAGE_GENERATOR", strategy="native")	
	private int id_image;
	
	@ManyToOne(targetEntity=com.emc.emergency.data.entity.Accident.class, fetch=FetchType.LAZY)	
	@org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.LOCK})	
	@JoinColumns({ @JoinColumn(name="id_Acc", referencedColumnName="id_AC", nullable=false) })	
	private com.emc.emergency.data.entity.Accident id_Acc;
	
	@Column(name="link", nullable=true, length=50)	
	private String link;
	
	private void setId_image(int value) {
		this.id_image = value;
	}
	
	public int getId_image() {
		return id_image;
	}
	
	public int getORMID() {
		return getId_image();
	}
	
	public void setLink(String value) {
		this.link = value;
	}
	
	public String getLink() {
		return link;
	}
	
	public void setId_Acc(com.emc.emergency.data.entity.Accident value) {
		this.id_Acc = value;
	}
	
	public com.emc.emergency.data.entity.Accident getId_Acc() {
		return id_Acc;
	}
	
	public String toString() {
		return String.valueOf(getId_image());
	}
	
}
