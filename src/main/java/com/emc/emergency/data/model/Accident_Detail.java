package com.emc.emergency.data.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * Created by hocan on 16-Jul-17.
 */
@Entity
@Table(name="Accident_Detail")
public class Accident_Detail {

  @Column(name="id_AC_detail", nullable=false, length=20)
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id_AC_detail;

  @ManyToOne
  @JoinColumn(name = "id_user")
  private User id_user;

  @ManyToOne
  @JoinColumn(name = "id_AC")
  private Accident id_AC;

  @ManyToOne
  @JoinColumn(name = "id_action_type")
  private Action_Type action_type;

  @Column(name="date_create", nullable=true)
  @Temporal(TemporalType.TIMESTAMP)
  @DateTimeFormat(pattern = "dd/MM/yyyy 'at' hh:mm:ss a")
  private Date date_create;

  public Accident_Detail() {
  }

  public Accident_Detail(Long id_AC_detail, Date date_create) {
    this.id_AC_detail = id_AC_detail;
    this.date_create = date_create;
  }

  public Accident_Detail(Long id_AC_detail, Date date_create, User id_user, Accident id_AC,
      Action_Type action_type) {
    this.id_AC_detail = id_AC_detail;
    this.id_user = id_user;
    this.id_AC = id_AC;
    this.action_type = action_type;
    this.date_create = date_create;
  }

  public Long getId_AC_detail() {
    return id_AC_detail;
  }

  public void setId_AC_detail(Long id_AC_detail) {
    this.id_AC_detail = id_AC_detail;
  }

  public User getId_user() {
    return id_user;
  }

  public void setId_user(User id_user) {
    this.id_user = id_user;
  }

  public Accident getId_AC() {
    return id_AC;
  }

  public void setId_AC(Accident id_AC) {
    this.id_AC = id_AC;
  }

  public Action_Type getAction_type() {
    return action_type;
  }

  public void setAction_type(Action_Type action_type) {
    this.action_type = action_type;
  }

  public Date getDate_create() {
    return date_create;
  }

  public void setDate_create(Date date_create) {
    this.date_create = date_create;
  }

  @Override public String toString() {
    return "Accident_Detail{" +
        "id_AC_detail=" + id_AC_detail +
        ", id_user=" + id_user +
        ", id_AC=" + id_AC +
        ", action_type=" + action_type +
        ", date_create=" + date_create +
        '}';
  }
}
