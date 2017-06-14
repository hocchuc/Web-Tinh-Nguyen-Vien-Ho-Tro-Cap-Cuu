
package com.emc.emergency.data.model;


import java.util.List;
import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="User_Type")
public class User_Type   {
	public User_Type() {
	}

	public User_Type(Long id_user_type, String name_user_type) {
		this.id_user_type = id_user_type;
		this.name_user_type = name_user_type;
	}

	@Column(name="id_user_type", nullable=false, length=20)
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_user_type;

	@Column(name="name_user_type", nullable=false, length=50)
	private String name_user_type;


	@OneToMany(mappedBy="user_type")
	private List<User> users ;

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
