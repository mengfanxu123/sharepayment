package com.mengfan.spring.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.mengfan.spring.DAO.TransferDAO;
import com.mengfan.spring.DAO.UserDAO;
import com.mengfan.spring.exception.UserException;
import com.mengfan.spring.pojo.Admin;
import com.mengfan.spring.pojo.TransferHistory;
import com.mengfan.spring.pojo.User;

@Controller
@RequestMapping("/admin/*")
public class AdminController {
	@Autowired
	@Qualifier("userDao")
	UserDAO userDao;
	
	@Autowired
	@Qualifier("transferDao")
	TransferDAO transferDao;
	
	@RequestMapping(value = "/admin/login", method = RequestMethod.POST)
	protected ModelAndView loginAdmin(HttpServletRequest request) throws Exception {
		System.out.print("loginadmin");
		HttpSession session = (HttpSession) request.getSession();

		try {

			System.out.print("loginadmin");
			
			String userName=request.getParameter("username");
			String password=request.getParameter("password");
			System.out.println(userName+password);
			if(userName.equals("admin")&& password.equals("admin")){
				System.out.println(111111);
				return new ModelAndView("admin-page");
			}else{
				return new ModelAndView("admin-login","error","you are not admin");
			}
		}catch (Exception e) {
			System.out.println("Exception: " + e.getMessage());
			session.setAttribute("errorMessage", "error while login");
			return new ModelAndView("admin-login","error","you are not admin");
		}
				
			



	}
		
	
	@RequestMapping(value = "/admin/login", method = RequestMethod.GET)
	protected ModelAndView registerUser() throws Exception {
		System.out.print("admin");

		return new ModelAndView("admin-login", "admin", new Admin());

	}
	
	@RequestMapping(value = "admin/transfer", method = RequestMethod.GET)
	protected ModelAndView listuserAccount(HttpServletRequest request) throws Exception {
		HttpSession session = (HttpSession) request.getSession();
		ModelAndView mv = new ModelAndView();
		List<TransferHistory> trans=transferDao.list();
		
		
		mv.addObject("history",trans);
		mv.setViewName("transfer-Manage");
		return mv;
		
		
	}
	
	@RequestMapping(value = "admin/acccountlist", method = RequestMethod.GET)
	protected ModelAndView listTransfer(HttpServletRequest request) throws Exception {
		HttpSession session = (HttpSession) request.getSession();
		ModelAndView mv = new ModelAndView();
		List<User> users=userDao.list();
		
		
		mv.addObject("users",users);
		mv.setViewName("user-manage");
		return mv;
		
		
	}
	


}
