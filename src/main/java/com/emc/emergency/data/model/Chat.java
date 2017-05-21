package com.emc.emergency.data.model;

import javax.persistence.*;
@Entity
@Table(name="Chat")
public class Chat {
	public Chat() {
	}

	@Column(name="id_chat", nullable=false, length=20)
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_chat;

	@ManyToOne
	private Accident id_Acc;

	@ManyToOne
	private User id_user;

	@Column(name="comment", nullable=true, length=50)
	private String comment;

	@Column(name="date_chat", nullable=true)
	@Temporal(TemporalType.DATE)
	private java.util.Date date_chat;

	private void setId_chat(Long value) {
		this.id_chat = value;
	}

	public Long getId_chat() {
		return id_chat;
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

	public void setId_Acc( Accident value) {
		this.id_Acc = value;
	}

	public Accident getId_Acc() {
		return id_Acc;
	}

	public void setId_user(User value) {
		this.id_user = value;
	}

	public User getId_user() {
		return id_user;
	}

	public String toString() {
		return String.valueOf(getId_chat());
	}

}

