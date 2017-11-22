package com.mengfan.spring.DAO;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import com.mengfan.spring.exception.BankAccountException;
import com.mengfan.spring.exception.TransferException;

import com.mengfan.spring.pojo.TransferHistory;
import com.mengfan.spring.pojo.User;

public class TransferDAO extends DAO {
	public TransferDAO(){
		
	}
	
	public  TransferHistory get(String firstName,String recieverLastName)throws TransferException{
		try{
			begin();
			Query q = getSession().createQuery("from TransferHistory where sender = :sender and reciever=reciever ");
			q.setString("firstName", firstName);
			q.setString("recieverLastName", recieverLastName);
			TransferHistory tf=(TransferHistory)q.uniqueResult();
			commit();
			return tf;
		}catch (HibernateException e) {
            rollback();
            throw new TransferException("Could not obtain the named category " + firstName + " " + e.getMessage());
        }
		
	}
	public List<TransferHistory> list() throws TransferException {
        try {
            begin();
            Query q = getSession().createQuery("from TransferHistory ");
            List<TransferHistory> list = q.list();
            commit();
            return list;
        } catch (HibernateException e) {
            rollback();
            throw new TransferException("Could not list the transferHistory", e);
        }
    }
	
	public List<TransferHistory> listYourSenderHistory(String userName) throws TransferException {
        try {
            begin();
            Query q = getSession().createQuery("from TransferHistory where sender =:personID ");
            q.setString("personID",userName);
            List<TransferHistory> list = q.list();
            commit();
            return list;
        } catch (HibernateException e) {
            rollback();
            throw new TransferException("Could not list the transferHistory", e);
        }
    }
	
	public List<TransferHistory> listYourReceverHistory(String firstName, String lastName) throws TransferException {
        try {
            begin();
            Query q = getSession().createQuery("from TransferHistory where firstName =:firstName and recieverLastName=:lastName ");
            q.setString("firstName",firstName);
            q.setString("lastName",lastName);
            
            List<TransferHistory> list = q.list();
            commit();
            return list;
        } catch (HibernateException e) {
            rollback();
            throw new TransferException("Could not list the receverHistorytransferHistory", e);
        }
    }
	
	public TransferHistory create(TransferHistory tranferHistory) throws TransferException {
        try {
            begin();
//            BankAccount bank = new BankAccount(title);
            getSession().save(tranferHistory);
//            System.out.print(bankAccount.getAccountNum());
            
//           System.out.println(bankAccount.getPostedBy()); 
            commit();
            return tranferHistory;
        } catch (HibernateException e) {
            rollback();
            //throw new AdException("Could not create the category", e);
            throw new TransferException("Exception while creating history: " + e.getMessage());
        }
    }
	
	 public void delete(TransferHistory tranferHistory)
	            throws BankAccountException {
	        try {
	            begin();
	            getSession().delete(tranferHistory);
	            commit();
	        } catch (HibernateException e) {
	            rollback();
	            throw new BankAccountException("Could not delete tranferHistory", e);
	        }
	    }
	 
	 public void saveorUpdate(TransferHistory tranferHistory)
	            throws TransferException {
	        try {
	            begin();
	            getSession().saveOrUpdate(tranferHistory);
	            commit();
	        } catch (HibernateException e) {
	            rollback();
	            throw new TransferException("Could not update transferHistory", e);
	        }
	    }
	 
	 
	 public List<TransferHistory> lsitPublicHistory(String type) throws TransferException{
		 try {
	            begin();
	            Query q = getSession().createQuery("from TransferHistory where type=:type ");
	            q.setString("type", type);
	            List<TransferHistory> list = q.list();
	            commit();
	            return list;
	        } catch (HibernateException e) {
	            rollback();
	            throw new TransferException("Could not listall the transferHistory", e);
	        }
	    }
	 
	 public List<TransferHistory> ListFriendsHistory(User u,String type) throws TransferException{
		 try {
	            begin();
	            String user=u.getUsername();
	            
	            Query q = getSession().createQuery("from TransferHistory where type<>:type and (sender=:sender or firstName=:firstName)");
	            q.setString("type", type);
	            q.setString("sender",user);
	            q.setString("firstName", u.getFirstName());
	            
	            
	            List<TransferHistory> list = q.list();
	            
	            commit();
	            return list;
	        } catch (HibernateException e) {
	            rollback();
	            throw new TransferException("Could not listall the transferHistory", e);
	        }
	    }
	 }


