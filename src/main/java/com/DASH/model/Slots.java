package com.DASH.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "slots")
public class Slots {
	
	@Id
		@GeneratedValue(strategy =  GenerationType.IDENTITY)
		private long id;

	@Column(name = "location")
		public String location;

	@Column(name = "start")
		public String start;

  @Column(name = "end")
	public String end;
  @Column(name = "date")
	  public String date;
  @Column(name = "worker")
	public String worker;
  @Column(name = "user")
	public String user;
  @Column(name = "status")
	public int status;
  @Column(name = "service")
  	public String service;
  public long getId() {
	  return id;
  }
  public void setId(long id) {
	  this.id = id;
  }
  public int getstatus() {
	  return status;
  }
  public void setstatus(int status) {
	  this.status=status;
  }
  public String getlocation() {
	  return location;
  }
  public void setlocation(String location) {
	  this.location = location;
  }
  public String getstart() {
	  return start;
  }
  public void setstart(String start) {
	  this.start = start;
  }
  public String getend() {
	  return end;
  }
  public void setend(String end) {
	  this.end = end;
  }
  public String getdate() {
	  return date;
  }
  public void setdate(String date) {
	  this.date = date;
  }
  public String getworker() {
	  return worker;
  }
  public void setworker(String worker) {
	  this.worker = worker;
  }
  public String getuser() {
	  return user;
  }
  public void setuser(String user) {
	  this.user = user;
  }
  public String getservice() {
	  return service;
  }
  public void setservice(String service) {
	  this.service = service;
  }
}