package com.emc.emergency.data.model;

import org.hibernate.annotations.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name="Chat")
public class Chat {
	public Chat() {
	}

	public Chat(Long id_chat, Accident id_Ac, User id_user, String comment, Date date_chat) {
		this.id_chat = id_chat;
		this.id_AC = id_Ac;
		this.id_user = id_user;
		this.comment = comment;
		this.date_chat = date_chat;
	}

	@Column(name="id_chat", nullable=false, length=20)
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_chat;

	@ManyToOne(fetch = FetchType.EAGER)
/*	@Cascade(value = {org.hibernate.annotations.CascadeType.PERSIST, org.hibernate.annotations.CascadeType.REFRESH, org.hibernate.annotations.CascadeType.MERGE})
	@JoinColumn(nullable = false,referencedColumnName = "id_AC",name = "id_AC")*/
	@JoinColumn(name = "id_AC")
	private Accident id_AC;

	@ManyToOne(fetch = FetchType.EAGER)
	/*@Cascade(value = {org.hibernate.annotations.CascadeType.PERSIST, org.hibernate.annotations.CascadeType.REFRESH, org.hibernate.annotations.CascadeType.MERGE})
	@JoinColumn(nullable = false,referencedColumnName = "id_user",name = "id_user")*/
	@JoinColumn(name = "id_user")
	private User id_user;

	@Column(name="comment", nullable=true, length=50)
	private String comment;

	@Column(name="date_chat", nullable=true)
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/yyyy 'at' hh:mm:ss a")
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

	public void setId_AC( Accident value) {
		this.id_AC = value;
	}

	public Accident getId_AC() {
		return id_AC;
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

