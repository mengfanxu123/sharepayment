package com.mengfan.spring.controller;

import java.util.ArrayList;
import java.io.OutputStream;
import java.net.URLDecoder;
import java.util.*;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.mengfan.spring.DAO.DAO;
import com.mengfan.spring.DAO.UserDAO;
import com.mengfan.spring.exception.BankAccountException;
import com.mengfan.spring.exception.UserException;
import com.mengfan.spring.pojo.BankAccount;
import com.mengfan.spring.pojo.FriendsMsg;
//import com.my.spring.exception.UserException;
//import com.my.spring.pojo.User;
import com.mengfan.spring.pojo.User;
import com.mengfan.spring.validator.FriendsMsgValidator;
import com.mengfan.spring.validator.UserValidator;

//import com.my.spring.dao.UserDAO;

@Controller
@RequestMapping("/user/*")
public class UserController extends DAO {
	@Autowired
	@Qualifier("userDao")
	UserDAO userDao;

	@Autowired
	@Qualifier("userValidator")
	UserValidator validator;
	
	@Autowired
	@Qualifier("friendsMsgValidator")
	FriendsMsgValidator friendsMsgValidator;

	@InitBinder("user")
	private void initBinder(WebDataBinder binder) {
		binder.setValidator(validator);
	}
	
	@InitBinder("friendsMsg")
	private void initBinder1(WebDataBinder binder) {
		binder.setValidator(friendsMsgValidator);
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	protected String goToUserHome(HttpServletRequest request) throws Exception {
		return "user-home";
	}

	@RequestMapping(value = "/user/login", method = RequestMethod.POST)
	protected String loginUser(HttpServletRequest request) throws Exception {

		HttpSession session = (HttpSession) request.getSession();

		try {

			System.out.print("loginUser");

			User u = userDao.get(request.getParameter("username"), request.getParameter("password"));

			if (u == null) {
				System.out.println("UserName/Password does not exist");
				session.setAttribute("errorMessage", "UserName/Password does not exist");
				return "index";
			}
			System.out.print(u.getPersonID());
			session.setAttribute("personID", u.getPersonID());
			session.setAttribute("user", u);

			return "user-home";

		} catch (UserException e) {
			System.out.println("Exception: " + e.getMessage());
			session.setAttribute("errorMessage", "error while login");
			return "error";
		}

	}

	@RequestMapping(value = "/user/register", method = RequestMethod.GET)
	protected ModelAndView registerUser() throws Exception {
		System.out.print("registerUser");

		return new ModelAndView("register-user", "user", new User());

	}

	@RequestMapping(value = "/user/register", method = RequestMethod.POST)
	protected ModelAndView registerNewUser(HttpServletRequest request, @ModelAttribute("user") User user,
			BindingResult result) throws Exception {

		validator.validate(user, result);

		if (result.hasErrors()) {
			return new ModelAndView("register-user", "user", user);
		}

		try {

			System.out.print("registerNewUser");

			User u = userDao.register(user);

			request.getSession().setAttribute("user", u);

			return new ModelAndView("user-home", "user", u);

		} catch (UserException e) {
			System.out.println("Exception: " + e.getMessage());
			return new ModelAndView("error", "errorMessage", "error while login");
		}
	}

	@RequestMapping(value = "/user/edit", method = RequestMethod.GET)
	protected ModelAndView editUser(HttpServletRequest request, @ModelAttribute("user") User user, BindingResult result)
			throws Exception {
		return new ModelAndView("edit-form", "user", user);

	}

	@RequestMapping(value = "/user/update", method = RequestMethod.POST)
	protected ModelAndView updateUser(HttpServletRequest request, @ModelAttribute("user") User user,
			BindingResult result) throws Exception {
		System.out.print("update User");
		HttpSession session = request.getSession();
		Long s = (Long) session.getAttribute("personID");
		System.out.print("ID" + s);
		user.setPersonID(s);

		// String s=request.getParameter("firstName");
		System.out.print(user.getPersonID());
		System.out.print(user.getLastName());

		// user.setFirstName(request.getParameter("firstName"));
		// user.setLastName(request.getParameter("lastName"));
		// user.setPassword(request.getParameter("password"));

		User u = userDao.updateUser(user);

		return new ModelAndView("user-home", "user", u);

	}

	@RequestMapping(value = "/user/addFriends", method = RequestMethod.GET)
	public ModelAndView addBankAccount() throws Exception {
		return new ModelAndView("addFriends-form", "user", new User());

	}

	@RequestMapping(value = "/user/addFriends", method = RequestMethod.POST)
	protected ModelAndView addFriends(HttpServletRequest request, @ModelAttribute("user") User user,
			BindingResult result) throws Exception {
		String s = request.getParameter("username");
		
		HttpSession session = request.getSession();
		
		long id = (Long) session.getAttribute("personID");
		
//		long userid =Long.parseLong(id);

		try {
			String temp = userDao.addFriends(s, id);
			if (temp != "Success") {
				return new ModelAndView("user-home");
			} else {
				return new ModelAndView("user-home");
			}
		} catch (HibernateException e) {
			rollback();
			e.printStackTrace();
		} finally {
			close();
		}

		return new ModelAndView("user-home");

	}
	
	@RequestMapping(value = "/user/sendRequest", method = RequestMethod.GET)
	public ModelAndView sendRequest() throws Exception {
		return new ModelAndView("sendResquest", "friendsMsg", new FriendsMsg());

	}
	
	@RequestMapping(value = "/user/sendRequest", method = RequestMethod.POST)
	public ModelAndView sendRequestForm(HttpServletRequest request,@ModelAttribute("friendsMsg") FriendsMsg friendsMsg,BindingResult result) throws Exception {
        System.out.println("!!!!");
		HttpSession session = request.getSession();
		
		
//		long id = (Long) session.getAttribute("personID");
		User u1=(User) session.getAttribute("user");
		String sender=u1.getUsername();
		FriendsMsg f=userDao.isExist(friendsMsg.getRequestName(),sender );
		if(friendsMsg.getRequestName()==null){
			return new ModelAndView("sendResquest", "error", "name is not exist");
			
		}else if(f!=null){
			return new ModelAndView("sendResquest", "error", "already send request");
			
		}else{
			Date d=new Date();
			friendsMsg.setDate(d);
			User u = userDao.getU(friendsMsg.getPostBy());
			friendsMsg.setSender(u.getUsername());
			friendsMsg.setUser(u);
			friendsMsg.setStatus("request");
			friendsMsg.setSender(sender);
			userDao.create(friendsMsg);     
			return new ModelAndView("user_home");
		}

	}
	
	@RequestMapping(value = "/user/seeRequest", method = RequestMethod.GET)
	public ModelAndView seeRequest(HttpServletRequest request) throws Exception {
		HttpSession session = (HttpSession) request.getSession();
		ModelAndView mv = new ModelAndView();
		User u=(User) session.getAttribute("user");
		String user=u.getUsername();
		System.out.println(user);
		String req="request";
		mv.addObject("requestList",userDao.listYourReceverMsg(user,req));
		mv.setViewName("addFriendsList");
		return mv;
		
	}
	
	@RequestMapping(value="/user/accpet")
	public void accpetFriends(String friendMsgID, HttpServletResponse rep) throws Exception{
		System.out.println("Call accept account!");
		try{
			String id = URLDecoder.decode(friendMsgID, "utf-8");
//			String id=friendMsgID;
			System.out.println(id);
			rep.setContentType("text/html;charset=UTF-8");
			OutputStream printWriter = null;
			printWriter = rep.getOutputStream();
			System.out.println("!!!!!!!! = " + id);
			FriendsMsg msg=userDao.get(Long.parseLong(id));
			String requestN=msg.getRequestName();
			User sender=userDao.get(msg.getSender());
			Long senderId=sender.getPersonID();
			String re=userDao.addFriends(requestN, senderId);
			msg.setStatus("accept");
			userDao.updateorSaveFriendsRequest(msg);
			
			
			if(re!=null){
				printWriter.write("success".getBytes());
			}else{
				printWriter.write("adderror".getBytes());
			}
			
		}catch (Exception e){
			System.out.println("Exception!");
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value="/user/reject")
	public void rejectFriends(String friendMsgID, HttpServletResponse rep) throws Exception{
		System.out.println("Call accept account!");
		try{
			String id = URLDecoder.decode(friendMsgID, "utf-8");
//			String id=friendMsgID;
			System.out.println(id);
			rep.setContentType("text/html;charset=UTF-8");
			OutputStream printWriter = null;
			printWriter = rep.getOutputStream();
			System.out.println("!!!!!!!! = " + id);
			FriendsMsg msg=userDao.get(Long.parseLong(id));
			userDao.delet(msg);
			printWriter.write("success".getBytes());
			
			
		}catch (Exception e){
			System.out.println("Exception!");
			e.printStackTrace();
		}
		
	}
	@RequestMapping(value="/user/deleteAll")
	public void deleteUser(String personID, HttpServletResponse rep) throws Exception{
		System.out.println("Call accept account!");
		try{
			String id = URLDecoder.decode(personID, "utf-8");
			long i= Long.parseLong(id);
//			String id=friendMsgID;
			System.out.println(id);
			rep.setContentType("text/html;charset=UTF-8");
			OutputStream printWriter = null;
			printWriter = rep.getOutputStream();
			System.out.println("!!!!!!!! = " + id);
			User user=userDao.getU(i);
			userDao.delete(user);
			
			printWriter.write("success".getBytes());
			
			
		}catch (Exception e){
			System.out.println("Exception!");
			e.printStackTrace();
		}
		
	}
	

}
