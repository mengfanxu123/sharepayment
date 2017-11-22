package com.mengfan.spring.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
//import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.mengfan.spring.DAO.BankAccountDAO;
import com.mengfan.spring.DAO.TransferDAO;
import com.mengfan.spring.DAO.UserDAO;
import com.mengfan.spring.exception.BankAccountException;
import com.mengfan.spring.exception.TransferException;
import com.mengfan.spring.pojo.BankAccount;
import com.mengfan.spring.pojo.TransferHistory;
import com.mengfan.spring.pojo.User;
import com.mengfan.spring.validator.UserValidator;
import com.mengfan.spring.DAO.UserDAO;
import com.mengfan.spring.validator.TransferValidator;




@Controller
@RequestMapping("/transfer/*")
public class TransferController {
	@Autowired
	UserDAO userDao;
	
	
	@Autowired
	@Qualifier("transferDao")
	TransferDAO transferDao;
	
	@Autowired
	@Qualifier("tranfersValidator")
	TransferValidator tranfersValidator;
	
	
	
	
	@InitBinder
	private void initBinder(WebDataBinder binder) {
		binder.setValidator(tranfersValidator);
	}
	
	@Autowired
	@Qualifier("bankAccountDao")
	BankAccountDAO bankAccountDao;
	
	
	
	
	@RequestMapping(value="/transfer/sendMoney", method = RequestMethod.GET)
	public ModelAndView initializeForm(HttpServletRequest request) throws Exception {
		System.out.print("kkkk");
		ModelAndView mv = new ModelAndView();
		mv.addObject("users", userDao.list());
		mv.addObject("transferHistory",new TransferHistory());
		mv.setViewName("sendMoney");
		return mv;
	}
	@RequestMapping(value="/transfer/sendMoney", method = RequestMethod.POST)
	public ModelAndView sendMoney(HttpServletRequest request,@ModelAttribute("transferHistory") TransferHistory transferHistory, BindingResult result) throws Exception {
		tranfersValidator.validate(transferHistory, result);
		User u = userDao.getU(transferHistory.getPostBy());
		if (result.hasErrors()) {
			return new ModelAndView("sendMoney", "error", "true");
		}
		
		try {
			if(userDao.getID(transferHistory.getFirstName(),transferHistory.getRecieverLastName())==null){
				return new ModelAndView("sendMoney","error","true");
			}else{
//			User u = userDao.get(transferHistory.getPostBy());
			if(transferHistory.getAmount()>u.getUserMoneyAccount()){
				System.out.println("call !!");
				Boolean b=true;
				BankAccount bk=bankAccountDao.getBindAccount(u.getFirstName(),u.getLastName(), b);
				System.out.println("!!!!!");
				if(bk==null){
					return new ModelAndView("sendMoney","error","true");
				}else{
				//when sender money amount larger  than current user amount, use bankaccount to tranfer money. 
				//define sender amount
				double creditSender=bk.getAmount()-transferHistory.getAmount();
				transferHistory.setSender(u.getUsername());
				//define transfer history
				System.out.print(u.getUsername());
				Date date=new Date();
				System.out.println(date);
				transferHistory.setDate(date);
				String type=request.getParameter("type");
				System.out.println(type);
				transferHistory.setType(type);
				transferDao.saveorUpdate(transferHistory);
				bk.setAmount(creditSender);
				bankAccountDao.saveorUpdate(bk);
				//define receiver
				User reciever=userDao.getID(transferHistory.getFirstName(), transferHistory.getRecieverLastName());
				double reAmount=reciever.getUserMoneyAccount()+transferHistory.getAmount();
				System.out.println(reAmount);
				reciever.setUserMoneyAccount(reAmount);
				userDao.updateUser(reciever);
				User p=userDao.getU(transferHistory.getPostBy());
//				
//				
//				double senderAmount=userDao.get(transferHistory.getPostBy());
				
	
				
				return new ModelAndView("transferSucess-form", "transferHistory", transferHistory);
				
				
			}
			}else{
//				User u = userDao.get(transferHistory.getPostBy());
				System.out.println(transferHistory.getPostBy()+"--->");
	
				
				
				transferHistory.setUser(u);
				
				transferHistory = transferDao.create(transferHistory);
				
				double senderAmount=u.getUserMoneyAccount()-transferHistory.getAmount();
				transferHistory.setSender(u.getUsername());
				System.out.print(u.getUsername());
				Date date=new Date();
				System.out.println(date);
				transferHistory.setDate(date);
				String type=request.getParameter("type");
				System.out.println(type);
				transferHistory.setType(type);
				transferDao.saveorUpdate(transferHistory);
				
				
				u.setUserMoneyAccount(senderAmount);
				
//				
//				
				userDao.saveorUpdate(u);
				User reciever=userDao.getID(transferHistory.getFirstName(), transferHistory.getRecieverLastName());
				double reAmount=reciever.getUserMoneyAccount()+transferHistory.getAmount();
				System.out.println(reAmount);
				reciever.setUserMoneyAccount(reAmount);
				userDao.updateUser(reciever);
				return new ModelAndView("transferSucess-form", "transferHistory", transferHistory);
			}
//				try{
//			User u = userDao.get(transferHistory.getPostBy());
//			System.out.println(transferHistory.getPostBy()+"--->");
//
//			
//			
//			transferHistory.setUser(u);
//			
//			transferHistory = transferDao.create(transferHistory);
//			
//			double senderAmount=u.getUserMoneyAccount()-transferHistory.getAmount();
//			transferHistory.setSender(u.getUsername());
//			System.out.print(u.getUsername());
//			Date date=new Date();
//			System.out.println(date);
//			transferHistory.setDate(date);
//			String type=request.getParameter("type");
//			System.out.println(type);
//			transferHistory.setType(type);
//			transferDao.saveorUpdate(transferHistory);
//			
//			
//			u.setUserMoneyAccount(senderAmount);
//			
////			
////			
//			userDao.saveorUpdate(u);
//			User reciever=userDao.getID(transferHistory.getFirstName(), transferHistory.getRecieverLastName());
//			double reAmount=reciever.getUserMoneyAccount()+transferHistory.getAmount();
//			System.out.println(reAmount);
//			reciever.setUserMoneyAccount(reAmount);
//			userDao.updateUser(reciever);
//			
//			
////			User p=userDao.get(transferHistory.getPostBy());
//			
//			
////			double senderAmount=userDao.get(transferHistory.getPostBy());
//			
//
//			
//			return new ModelAndView("transferSucess-form", "transferHistory", transferHistory);
			}
			
		} catch (TransferException e) {
			System.out.println(e.getMessage());
			return new ModelAndView("error", "errorMessage", "error while login");
		}
	}
	@RequestMapping(value="/transfer/history", method = RequestMethod.GET)
	public ModelAndView listHistoryForm(HttpServletRequest request) throws Exception {
		HttpSession session = (HttpSession) request.getSession();
		  ModelAndView mv = new ModelAndView();
		  mv.addObject("historys", transferDao.list());
		  
		  
		  User u=(User) session.getAttribute("user");
		  System.out.print(u.getPersonID());
		  String firstName=u.getFirstName();
		  String lastName=u.getLastName();
		  mv.addObject("senderHistories",transferDao.listYourSenderHistory(u.getUsername()));
		  mv.addObject("recieverHistories",transferDao.listYourReceverHistory(firstName, lastName));
		  
		  mv.setViewName("history-form");
		  return mv;
		 
		  
		
		}
	
	@RequestMapping(value="/transfer/listPublic", method = RequestMethod.GET)
	public ModelAndView listPublicHistoryForm(HttpServletRequest request) throws Exception {
		HttpSession session = (HttpSession) request.getSession();
		  ModelAndView mv = new ModelAndView();
		  mv.addObject("listpublic", transferDao.lsitPublicHistory("public"));
		  
		  
//		  User u=(User) session.getAttribute("user");
//		  System.out.print(u.getPersonID());
//		  String firstName=u.getFirstName();
//		  String lastName=u.getLastName();
//		  mv.addObject("senderHistories",transferDao.listYourSenderHistory(u.getPersonID()));
//		  mv.addObject("recieverHistories",transferDao.listYourReceverHistory(firstName, lastName));
		  
		  mv.setViewName("listPublic");
		  return mv;
		 
		  
		
		}
	@RequestMapping(value="/transfer/listFriends", method = RequestMethod.GET)
	public ModelAndView listFrinedsHistoryForm(HttpServletRequest request) throws Exception {
		HttpSession session = (HttpSession) request.getSession();
		  ModelAndView mv = new ModelAndView();
		  
		  mv.addObject("public", transferDao.lsitPublicHistory("public"));
		  
		  
		  User u=(User) session.getAttribute("user");
		  
		  System.out.println(u.toString());
		  
		  Set<User> set=u.getUsers();
		  Set<User> sets=u.getUsers();
		  
		  List<List<TransferHistory>> tf = new ArrayList<List<TransferHistory>>();
			
			Iterator<User> it=set.iterator();
			while(it.hasNext()){
				User u1=it.next();
				System.out.println(u1.getPersonID());
				tf.add((transferDao.ListFriendsHistory(u1, "friends")));
				
			}
			
			Iterator<User> its=sets.iterator();
			while(its.hasNext()){
				User u2=its.next();
				System.out.println(u2.getPersonID());
				tf.add((transferDao.ListFriendsHistory(u2, "friends")));
				
			}
			//System.out.println(tf.size());
			List <TransferHistory> result = new  ArrayList<TransferHistory>();
			for(List<TransferHistory> temp : tf){
				System.out.println(temp.size());
				for (TransferHistory  temp2 : temp){
					result.add(temp2);
				}
			}
			HashSet h = new HashSet(result);
			result.clear();
			result.addAll(h);
			
			System.out.println(result.size());
			mv.addObject("friendlist",result);
//		  System.out.print(u.getPersonID());
//		  String firstName=u.getFirstName();
//		  String lastName=u.getLastName();
//		  mv.addObject("senderHistories",transferDao.listYourSenderHistory(u.getPersonID()));
//		  mv.addObject("recieverHistories",transferDao.listYourReceverHistory(firstName, lastName));
		  
		  mv.setViewName("listFriends");
		  return mv;
		 
		  
		
		}
	
//	@RequestMapping(value="/transfer/listPublic", method = RequestMethod.GET)
//	public ModelAndView listPublicForm(HttpServletRequest request) throws Exception {
//		HttpSession session = (HttpSession) request.getSession();
//		  ModelAndView mv = new ModelAndView();
//		  mv.addObject("historys", transferDao.lsitPublicHistory("public"));
//		  
//		 
//		  
//		  mv.setViewName("publicHistory-form");
//		  return mv;
//		 
//		  
//		
//		}
	
	
}
	
		


