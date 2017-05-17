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
@Table(name="Chat")
public class Chat implements Serializable {
	public Chat() {
	}
	
	@Column(name="id_chat", nullable=false, length=20)	
	@Id	
	@GeneratedValue(generator="com.emc.emergency.data.entity_CHAT_ID_CHAT_GENERATOR")	
	@org.hibernate.annotations.GenericGenerator(name="com.emc.emergency.data.entity_CHAT_ID_CHAT_GENERATOR", strategy="native")	
	private int id_chat;
	
	@ManyToOne(targetEntity=com.emc.emergency.data.entity.Accident.class, fetch=FetchType.LAZY)	
	@org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.LOCK})	
	@JoinColumns({ @JoinColumn(name="id_Acc", referencedColumnName="id_AC", nullable=false) })	
	private com.emc.emergency.data.entity.Accident id_Acc;
	
	@ManyToOne(targetEntity=com.emc.emergency.data.entity.User.class, fetch=FetchType.LAZY)	
	@org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.LOCK})	
	@JoinColumns({ @JoinColumn(name="id_user", referencedColumnName="id_user", nullable=false) })	
	private com.emc.emergency.data.entity.User id_user;
	
	@Column(name="comment", nullable=true, length=50)	
	private String comment;
	
	@Column(name="date_chat", nullable=true)	
	@Temporal(TemporalType.DATE)	
	private java.util.Date date_chat;
	
	private void setId_chat(int value) {
		this.id_chat = value;
	}
	
	public int getId_chat() {
		return id_chat;
	}
	
	public int getORMID() {
		return getId_chat();
	}
	
	public void setComment(String value) {
		this.comment = value;
	}
	
	public String getComment() {
		return comment;
	}
	
	public void setDate_chat(java.util.Date value) {
		this.date_chat = value;
	}
	
	public java.util.Date getDate_chat() {
		return date_chat;
	}
	
	public void setId_Acc(com.emc.emergency.data.entity.Accident value) {
		this.id_Acc = value;
	}
	
	public com.emc.emergency.data.entity.Accident getId_Acc() {
		return id_Acc;
	}
	
	public void setId_user(com.emc.emergency.data.entity.User value) {
		this.id_user = value;
	}
	
	public com.emc.emergency.data.entity.User getId_user() {
		return id_user;
	}
	
	public String toString() {
		return String.valueOf(getId_chat());
	}
	
}
