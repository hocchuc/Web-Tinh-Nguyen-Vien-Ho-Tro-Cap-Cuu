
package com.emc.emergency.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;
@Entity
@Table(name="User_Type")
public class User_Type implements Serializable {
	public User_Type() {
	}

	@Column(name="id_user_type", nullable=false, length=20)
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_user_type;

	@Column(name="name_user_type", nullable=true, length=50)
	private String name_user_type;

	@OneToMany(mappedBy="id_user_type")
	private List<User> users = new ArrayList<>();

	private void setId_user_type(Long value) {
		this.id_user_type = value;
	}

	public Long getId_user_type() {
		return id_user_type;
	}

	public void setName_user_type(String value) {
		this.name_user_type = value;
	}

	public String getName_user_type() {
		return name_user_type;
	}

	public List<User> getUsers() {
		return users;
	}


	public String toString() {
		return String.valueOf(getId_user_type());
	}

}
