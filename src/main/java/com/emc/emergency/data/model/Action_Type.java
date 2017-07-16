package com.emc.emergency.data.model;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Created by hocan on 16-Jul-17.
 */

@Entity
@Table(name="Action_Type")
public class Action_Type {
  @Column(name="id_action", nullable=false, length=20)
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id_action;

  @Column(name="name_action", nullable=false, length=255)
  private String name_action;

  @OneToMany(mappedBy="id_AC_detail")
  private List<Accident_Detail> accident_details ;

  public Action_Type() {
  }

  public Action_Type(Long id_action, String name_action) {
    this.id_action = id_action;
    this.name_action = name_action;
  }

  public Action_Type(Long id_action, String name_action,
      List<Accident_Detail> accident_details) {
    this.id_action = id_action;
    this.name_action = name_action;
    this.accident_details = accident_details;
  }

  public Long getId_action() {
    return id_action;
  }

  public void setId_action(Long id_action) {
    this.id_action = id_action;
  }

  public String getName_action() {
    return name_action;
  }

  public void setName_action(String name_action) {
    this.name_action = name_action;
  }

  public List<Accident_Detail> getAccident_details() {
    return accident_details;
  }

  public void setAccident_details(
      List<Accident_Detail> accident_details) {
    this.accident_details = accident_details;
  }

  @Override public String toString() {
    return "Action_Type{" +
        "id_action=" + id_action +
        ", name_action='" + name_action + '\'' +
        ", accident_details=" + accident_details +
        '}';
  }
}
