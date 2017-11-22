package com.mengfan.spring.pojo;

import java.util.Date;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;



@Entity
@Table(name="transferHistory_table")
public class TransferHistory {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="transferID", unique = true, nullable = false)
    private long transferID;
	
	@Column(name="sender")
    private String sender;
	
	@Column(name="recieverLastName")
    private String recieverLastName;
	
	@Column(name="firstName")
    private String firstName;
	
	@Column(name="date")
    private Date date;
	
	@Column(name="amount")
    private double amount;
	
	@Column(name="type")
    private String type;
	
	@Column(name="comment")
    private String comment;
	
	@ManyToOne
	private User user;
	
	@Transient
	int postBy;
	
	public TransferHistory(long transferID,String recieverLastName,double amount,Date date,User user,String firstName,String sender,String type,String comment){
		this.transferID=transferID;
		this.sender=sender;
		this.recieverLastName=recieverLastName;
		this.amount=amount;
		this.user=user;
		this.firstName=firstName;
		this.type=type;
		this.comment=comment;
		
	}
	
	public TransferHistory(){
		
	}

	public long getTransferID() {
		return transferID;
	}

	public void setTransferID(long transferID) {
		this.transferID = transferID;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getRecieverLastName() {
		return recieverLastName;
	}

	public void setRecieverLastName(String recieverLastName) {
		this.recieverLastName = recieverLastName;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
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

	public int getPostBy() {
		return postBy;
	}

	public void setPostBy(int postBy) {
		this.postBy = postBy;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
	
	
	

//	public Set<BankAccount> getBankAccounts() {
//		return bankAccounts;
//	}
//
//	public void setBankAccounts(Set<BankAccount> bankAccounts) {
//		this.bankAccounts = bankAccounts;
//	}
	
	
	
	
	

}
