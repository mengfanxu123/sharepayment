package com.mengfan.spring.pojo;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.JoinColumn;

//import com.my.spring.pojo.Email;


	@Entity
	@Table(name = "user_table")
	@PrimaryKeyJoinColumn(name = "personID")
	@Inheritance(strategy=InheritanceType.JOINED)
	public class User extends Person {

		@Column(name = "userName")
		private String username;

		@Column(name = "password")
		private String password;
		
		@Column(name = "userMoneyAccount")
		private double userMoneyAccount;

		@OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
		private Email email;
		
		 @ManyToMany(cascade={CascadeType.ALL})
		 @JoinTable(name="FRIENDS",
		                joinColumns={@JoinColumn(name="personID")},
		                inverseJoinColumns={@JoinColumn(name="FRIEND_ID")})
		private Set<User> users = new HashSet<User>();
//		 
		 @ManyToMany(mappedBy="users")
		    private Set<User> friends = new HashSet<User>();
		
		public User(String username, String password) {
			this.username = username;
			this.password = password;
//			this.userMoneyAccount=userMoneyAccount;
		}

		public User() {
		
		}

		public String getUsername() {
			return username;
		}

		public void setUsername(String username) {
			this.username = username;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		public Email getEmail() {
			return email;
		}

		public void setEmail(Email email) {
			this.email = email;
		}

		public double getUserMoneyAccount() {
			return userMoneyAccount;
		}

		public void setUserMoneyAccount(double userMoneyAccount) {
			this.userMoneyAccount = userMoneyAccount;
		}

		public Set<User> getUsers() {
			return users;
		}

		public void setUsers(Set<User> users) {
			this.users = users;
		}

		public Set<User> getFriends() {
			return friends;
		}

		public void setFriends(Set<User> friends) {
			this.friends = friends;
		}
//		
		

}
