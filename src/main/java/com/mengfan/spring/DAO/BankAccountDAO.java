package com.mengfan.spring.DAO;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import com.mengfan.spring.exception.BankAccountException;
import com.mengfan.spring.exception.UserException;
import com.mengfan.spring.pojo.BankAccount;
import com.mengfan.spring.pojo.User;
//import com.my.spring.exception.AdvertException;
//import com.my.spring.exception.CategoryException;
//import com.my.spring.exception.CategoryException;
//import com.my.spring.pojo.Category;


public class BankAccountDAO extends DAO {
	public BankAccountDAO(){
	}
	public  BankAccount get(Long accountNum)throws BankAccountException{
		try{
			begin();
			Query q = getSession().createQuery("from BankAccount where accountNum = :accountNum ");
			q.setLong("accountNum", accountNum);
			BankAccount bk=(BankAccount)q.uniqueResult();
			commit();
			return bk;
		}catch (HibernateException e) {
            rollback();
            throw new BankAccountException("Could not obtain the named category " + accountNum + " " + e.getMessage());
        }
		
	}
	
	 public List<BankAccount> list() throws BankAccountException {
	        try {
	            begin();
	            Query q = getSession().createQuery("from BankAccount");
	            List<BankAccount> list = q.list();
	            commit();
	            return list;
	        } catch (HibernateException e) {
	            rollback();
	            throw new BankAccountException("Could not list the BankAccount", e);
	        }
	    }
	 
	 public List<BankAccount> listYouAccount(Long personID) throws BankAccountException {
	        try {
	            begin();
	            Query q = getSession().createQuery("from BankAccount where user_personID=:personID");
	            q.setLong("personID", personID);
	            List<BankAccount> list = q.list();
	            commit();
	            return list;
	        } catch (HibernateException e) {
	            rollback();
	            throw new BankAccountException("Could not list the BankAccount", e);
	        }
	    }
	 
	 
	 public BankAccount get(int userId) throws UserException {
			try {
				begin();
				Query q = getSession().createQuery("from BankAccount where personID= :personID");
				q.setInteger("personID", userId);		
				BankAccount bankAccount = (BankAccount) q.uniqueResult();
				commit();
				return bankAccount;
			} catch (HibernateException e) {
				rollback();
				throw new UserException("Could not get BankAccount " + userId, e);
			}
		}
	 
	 public BankAccount getBindAccount(String firstName,String lastName,boolean b) throws UserException {
			try {
				begin();
				Query q = getSession().createQuery("from BankAccount where firstName= :firstName and lastName=:lastName and  isBind =:isBind");
				q.setString("firstName", firstName);
				q.setString("lastName", lastName);
				q.setBoolean("isBind", b);
				BankAccount bankAccount = (BankAccount) q.uniqueResult();
				commit();
				return bankAccount;
			} catch (HibernateException e) {
				rollback();
				throw new UserException("Could not get BankAccount " + lastName, e);
			}
		}
	 public BankAccount create(BankAccount bankAccount) throws BankAccountException {
	        try {
	            begin();
//	            BankAccount bank = new BankAccount(title);
	            getSession().save(bankAccount);
	            System.out.print(bankAccount.getAccountNum());
	            
	           System.out.println(bankAccount.getPostedBy()); 
	            commit();
	            return bankAccount;
	        } catch (HibernateException e) {
	            rollback();
	            //throw new AdException("Could not create the category", e);
	            throw new BankAccountException("Exception while creating bankAccount: " + e.getMessage());
	        }
	    }
	 public void delete(BankAccount bankAccount)
	            throws BankAccountException {
	        try {
	            begin();
	            getSession().delete(bankAccount);
	            commit();
	        } catch (HibernateException e) {
	            rollback();
	            throw new BankAccountException("Could not delete BankAccount", e);
	        }
	    }
	 public void saveorUpdate(BankAccount bankAccount)
	            throws BankAccountException {
	        try {
	            begin();
	            getSession().saveOrUpdate(bankAccount);
	            commit();
	        } catch (HibernateException e) {
	            rollback();
	            throw new BankAccountException("Could not update BankAccount", e);
	        }
	    }

	 public BankAccount findBindCard()
	            throws BankAccountException {
	        try {
	            begin();
	            Query q = getSession().createQuery("from BankAccount where isBind=true");
//	            q.setLong("accountNum", bankAccount.getAccountNum());
	            BankAccount bk=(BankAccount) q.uniqueResult();
	            System.out.println(bk.getAccountNum());
	            
//	            long accountNum=bk.getAccountNum();
//	            long accountNum1=bankAccount.getAccountNum();
//	            Query q1=getSession().createQuery("set isBind=:isBind where accountNum=:accountNum ");
//	            q1.setBoolean("isBind", false);
//	            q1.setLong("accountNum",accountNum);
//	            Query q2=getSession().createQuery("set isBind=:isBind1 where accountNum=:accountNum1 ");
//	            q2.setBoolean("isBind1", true);
//	            q1.setLong("accountNum1",accountNum1);
	            
	            commit();
	            return bk;
	        } catch (HibernateException e) {
	            rollback();
	            throw new BankAccountException("Could not delete BankAccount", e);
	        }
	    }
	    	
	    

}
