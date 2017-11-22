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
@Table(name="FriendMsg_table")
public class FriendsMsg {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="FriendMsgID", unique = true, nullable = false)
    private long friendMsgID;	
	
	@Column(name="date")
	private Date date;
	
	@Column(name="requestName")
	private String requestName;
	
	@Column(name="sender")
	private String sender;
	
	@Column(name="status")
	private String status;
	
	@ManyToOne
	private User user;
	
	@Transient
	int postBy;
	
	public FriendsMsg(long friendMsgID,Date date,String requestName,String status,User user ){
		this.friendMsgID=friendMsgID;
		this.date=date;
		this.requestName=requestName;
		this.status=status;
		this.user=user;
	}
	public FriendsMsg(){
		
	}
	public long getFriendMsgID() {
		return friendMsgID;
	}
	public void setFriendMsgID(long friendMsgID) {
		this.friendMsgID = friendMsgID;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getRequestName() {
		return requestName;
	}
	public void setRequestName(String requestName) {
		this.requestName = requestName;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
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
	public String getSender() {
		return sender;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
	
	

}
