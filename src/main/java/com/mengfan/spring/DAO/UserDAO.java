package com.mengfan.spring.DAO;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import com.mengfan.spring.exception.BankAccountException;
import com.mengfan.spring.exception.FriendsMsgException;
import com.mengfan.spring.exception.TransferException;
import com.mengfan.spring.exception.UserException;
import com.mengfan.spring.pojo.User;
import com.mengfan.spring.pojo.BankAccount;
//import com.my.spring.exception.AdvertException;
//import com.my.spring.pojo.Advert;
import com.mengfan.spring.pojo.Email;
import com.mengfan.spring.pojo.FriendsMsg;
import com.mengfan.spring.pojo.TransferHistory;

//import com.my.spring.exception.UserException;
//import com.my.spring.pojo.User;

public class UserDAO extends DAO {
	public UserDAO() {
	}

	public User get(String username, String password) throws UserException {
		try {
			begin();
			Query q = getSession().createQuery("from User where username = :username and password = :password");
			q.setString("username", username);
			q.setString("password", password);
			User user = (User) q.uniqueResult();
			commit();
			return user;
		} catch (HibernateException e) {
			rollback();
			throw new UserException("Could not get user " + username, e);
		}
	}

	public User getU(long userId) throws UserException {
		try {
			begin();
			Query q = getSession().createQuery("from User where personID= :personID");
			q.setLong("personID", userId);
			User user = (User) q.uniqueResult();
			commit();
			return user;
		} catch (HibernateException e) {
			rollback();
			throw new UserException("Could not get user " + userId, e);
		}
	}
	
	public User get(String userName) throws UserException {
		try {
			begin();
			Query q = getSession().createQuery("from User where username= :username");
			q.setString("username", userName);
			User user = (User) q.uniqueResult();
			commit();
			return user;
		} catch (HibernateException e) {
			rollback();
			throw new UserException("Could not get user " + userName, e);
		}
	}

	public User getID(String fistName, String lastName) throws UserException {
		try {
			begin();
			Query q = getSession().createQuery("from Person where firstName= :firstName and lastName=:lastName");
			q.setString("firstName", fistName);
			q.setString("lastName", lastName);
			User user = (User) q.uniqueResult();
			commit();
			return user;
		} catch (HibernateException e) {
			rollback();
			throw new UserException("Could not get user " + fistName, e);
		}
	}

	public User register(User u) throws UserException {
		try {
			begin();
			System.out.println("inside DAO");

			Email email = new Email(u.getEmail().getEmailAddress());
			User user = new User(u.getUsername(), u.getPassword());

			user.setFirstName(u.getFirstName());
			user.setLastName(u.getLastName());
			user.setEmail(email);
			email.setUser(user);
			getSession().save(user);
			commit();
			return user;

		} catch (HibernateException e) {
			rollback();
			throw new UserException("Exception while creating user: " + e.getMessage());
		}

	}

	public void delete(User user) throws UserException {
		try {
			begin();
			getSession().delete(user);
			commit();
		} catch (HibernateException e) {
			rollback();
			throw new UserException("Could not delete user " + user.getUsername(), e);
		}
	}

	public List<User> list() throws UserException {

		try {
			begin();
			Query q = getSession().createQuery("from User");
			List<User> users = q.list();
			commit();
			return users;
		} catch (HibernateException e) {
			rollback();
			throw new UserException("Could not list User", e);
		}

	}

	public User updateUser(User user) throws UserException {
		try {
			begin();
			String hql = ("update Person set firstName=:firstName,lastName=:lastName where personID=:personID");
			Query query = getSession().createQuery(hql);
			query.setParameter("firstName", user.getFirstName());
			query.setParameter("lastName", user.getLastName());
			// query.setParameter(, user.getEmail().getEmailAddress());
			// query.setParameter("password", user.getPassword());
			query.setParameter("personID", user.getPersonID());
			query.executeUpdate();
			commit();
			return user;
		} catch (HibernateException e) {
			rollback();
			throw new UserException("Could not update user " + user.getUsername(), e);
		}

	}

	public void saveorUpdate(User user) throws BankAccountException {
		try {
			begin();
			getSession().saveOrUpdate(user);
			commit();
		} catch (HibernateException e) {
			rollback();
			throw new BankAccountException("Could not update BankAccount", e);
		}
	}

	public String addFriends(String userName, long id) throws UserException {
		try {
			begin();
			System.out.println("inside DAO");
			Query q = getSession().createQuery("from User where userName= :userName");
			q.setString("userName", userName);
			User user_temp = (User) q.uniqueResult();
			Query temp_q = getSession().createQuery("from User where personID=" + id);
			User user = (User) temp_q.uniqueResult();
			user.getUsers().add(user_temp);
//			user.getUsers().forEach(action);
			Set<User> set=user.getFriends();
			
			Iterator<User> it=set.iterator();
			while(it.hasNext()){
				User u=it.next();
				System.out.println("userSet"+"is"+u.getUsername());
			}
			
			Set<User> sets=user.getUsers();
			Iterator<User> its=sets.iterator();
			while(its.hasNext()){
				User u1=its.next();
				System.out.println("friends set"+user.getUsername()+"is"+u1.getUsername());
			}
			
			
			getSession().save(user);
			getSession().save(user_temp);
			
			commit();
			
			return "Success";

		} catch (HibernateException e) {
			rollback();
			throw new UserException("Exception while creating user: " + e.getMessage());
		}
	}

	public void updateFriend(User u2) throws UserException  {
		// TODO Auto-generated method stub
		try {
			begin();
			getSession().update(u2);
			commit();
		} catch (HibernateException e) {
			rollback();
			throw new UserException("Exception while add friend user: " + e.getMessage());

		}
	}
	
	public FriendsMsg create(FriendsMsg fs) throws FriendsMsgException {
		try {
			begin();

			getSession().save(fs);

			commit();
			return fs;
		} catch (HibernateException e) {
			rollback();

			throw new FriendsMsgException("Exception while creating requestMsg: " + e.getMessage());
		}
	}
	
	public FriendsMsg isExist(String friendName, String sender) throws FriendsMsgException {
		try {
			begin();
			Query q = getSession().createQuery("from FriendsMsg where (sender= :sender or sender=:friendName) and (requestName=:friendName or requestName =:sender");
			q.setString("friendName", sender);
			q.setString("sender",sender);
			FriendsMsg fm=(FriendsMsg) q.uniqueResult();
			
			

			commit();
			return fm;
		} catch (HibernateException e) {
			rollback();

			throw new FriendsMsgException("Exception while creating requestMsg: " + e.getMessage());
		}
	}
	
	public void createFriendsRequest(FriendsMsg fm) throws  FriendsMsgException  {
		// TODO Auto-generated method stub
		try {
			begin();
			getSession().save(fm);
			commit();
		} catch (HibernateException e) {
			rollback();
			throw new  FriendsMsgException("Exception while add friend user: " + e.getMessage());

		}
	}
	public void updateorSaveFriendsRequest(FriendsMsg fm) throws  FriendsMsgException  {
		// TODO Auto-generated method stub
		try {
			begin();
			getSession().saveOrUpdate(fm);;
			commit();
		} catch (HibernateException e) {
			rollback();
			throw new  FriendsMsgException("Exception while add friend user: " + e.getMessage());

		}
	}
	
	public List<FriendsMsg> listYourReceverMsg(String requestName,String request) throws FriendsMsgException {
        try {
            begin();
            Query q = getSession().createQuery("from FriendsMsg where requestName=:requestName and status=:status");
            q.setString("requestName", requestName);
            q.setString("status", request);
            
           
            
            List<FriendsMsg> list = q.list();
            commit();
            return list;
        } catch (HibernateException e) {
            rollback();
            throw new FriendsMsgException("Could not list the msg", e);
        }
    }
	public  FriendsMsg get(Long accountNum)throws FriendsMsgException{
		try{
			begin();
			Query q = getSession().createQuery("from FriendsMsg where friendMsgID = :id ");
			q.setLong("id", accountNum);
			FriendsMsg bk=(FriendsMsg)q.uniqueResult();
			commit();
			return bk;
		}catch (HibernateException e) {
            rollback();
            throw new FriendsMsgException ("Could not obtain the named category " + accountNum + " " + e.getMessage());
        }
		
	}
	
	public  void delet(FriendsMsg msg)throws FriendsMsgException{
		try{
			begin();
			getSession().delete(msg);
			commit();
			
		}catch (HibernateException e) {
            rollback();
            throw new FriendsMsgException ("Could not obtain the named category " + msg + " " + e.getMessage());
        }
		
	}
	
}
