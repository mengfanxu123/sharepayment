package com.mengfan.spring.pojo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "bankAccount_table")
//@PrimaryKeyJoinColumn(name = "userID")
public class BankAccount {
	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="accountNum", unique = true, nullable = false)
	private long accountNum;
	
	@Column(name="amount")
    private double amount;
	@Column(name="validate")
    private Date validate;
	@Column(name="csv")
    private int csv;
	@Column(name="firstName")
    private String firstName;
	@Column(name="lastName")
    private String lastName;
	
	@Column(name="isBind")
    private boolean isBind;
	@ManyToOne
	private User user;
	
	@Transient
	int postedBy;
	
	public BankAccount(double amount, User user, long accountNum,boolean isBind){
		this.amount=amount;
		this.user=user;
		this.accountNum=accountNum;
		this.isBind=isBind;
		}
	public BankAccount(){
		
	}
	public long getAccountNum() {
		return accountNum;
	}
	public void setAccountNum(long accountNum) {
		this.accountNum = accountNum;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public int getPostedBy() {
		return postedBy;
	}
	public void setPostedBy(int postedBy) {
		this.postedBy = postedBy;
	}
	public Date getValidate() {
		return validate;
	}
	public void setValidate(Date validate) {
		this.validate = validate;
	}
	public int getCsv() {
		return csv;
	}
	public void setCsv(int csv) {
		this.csv = csv;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public boolean isBind() {
		return isBind;
	}
	public void setBind(boolean isBind) {
		this.isBind = isBind;
	}
	
	
	
}
