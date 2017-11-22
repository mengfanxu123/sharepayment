package com.mengfan.spring.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
@Entity
@Table(name = "admin_Table")
@PrimaryKeyJoinColumn(name = "personID")
@Inheritance(strategy=InheritanceType.JOINED)
public class Admin extends Person {
	@Column(name = "adminName")
	private String adminName;

	@Column(name = "password")
	private String password;
	
	public Admin(String adminName,String password){
		this.adminName=adminName;
		this.password=password;
	}
	public Admin(){
		
	}
	public String getAdminName() {
		return adminName;
	}
	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	

}
