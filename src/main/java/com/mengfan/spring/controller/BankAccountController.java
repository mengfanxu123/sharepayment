package com.mengfan.spring.controller;

import java.io.OutputStream;
import java.net.URLDecoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.mengfan.spring.DAO.BankAccountDAO;
import com.mengfan.spring.DAO.UserDAO;
import com.mengfan.spring.exception.BankAccountException;
import com.mengfan.spring.exception.UserException;
import com.mengfan.spring.pojo.BankAccount;
import com.mengfan.spring.pojo.User;
import com.mengfan.spring.validator.BankAccountValiator;
//import com.my.spring.pojo.Category;
import com.mengfan.spring.validator.UserValidator;
//import com.my.spring.exception.AdvertException;
//import com.my.spring.pojo.Category;


@Controller
@RequestMapping("/bankAccount/*")
public class BankAccountController {
	
	@Autowired
	UserDAO userDAO;
	
	@Autowired
	@Qualifier("bankAccountDao")
	BankAccountDAO bankAccountDao;
	
	@Autowired
	@Qualifier("bankAccountValiator")
	BankAccountValiator bankAccountValiator;
	
	@InitBinder
	private void initBinder(WebDataBinder binder) {
		binder.setValidator(bankAccountValiator);
	}
	
	@RequestMapping(value = "/bankAccount/add", method = RequestMethod.GET)
	public ModelAndView addBankAccount()throws Exception {
		return new ModelAndView("bankAccount-form","bankAccount",new BankAccount());
		
	}
	
	@RequestMapping(value = "/bankAccount/addAccount", method = RequestMethod.POST)
	protected ModelAndView addNewAccount(HttpServletRequest request,  @ModelAttribute("bankAccount") BankAccount bankAccount, BindingResult result) throws Exception {
		System.out.println("111");
		bankAccountValiator.validate(bankAccount, result);
		if (result.hasErrors()) {
			return new ModelAndView("bankAccount-form", "bankAccount", bankAccount);
		}

		try {			

			System.out.println(bankAccount.getPostedBy()+"--->");

			User u = userDAO.getU(bankAccount.getPostedBy());
			bankAccount.setUser(u);
			if(bankAccountDao.listYouAccount(u.getPersonID()).size()==0){
				bankAccount.setBind(true);
			}
			bankAccount.setAmount(10000);
			bankAccount = bankAccountDao.create(bankAccount);
			
			

			
			return new ModelAndView("accountSucess-form", "bankAccount", bankAccount);
			
		} catch (BankAccountException e) {
			System.out.println(e.getMessage());
			return new ModelAndView("error", "errorMessage", "error while login");
		}
	}
	
	@RequestMapping(value = "/bankAccount/list", method = RequestMethod.GET)
	protected ModelAndView listBankAccount(HttpServletRequest request) throws Exception {
		HttpSession session = (HttpSession) request.getSession();
		ModelAndView mv = new ModelAndView();
		User u=(User) session.getAttribute("user");
		Long id=u.getPersonID();
		System.out.println("id");
		mv.addObject("bankAccountList",bankAccountDao.listYouAccount(id));
		mv.setViewName("listBankAccount");
		return mv;
		
		
	}
//	@RequestMapping(value="/bankAcount/delete")
//	
//	public BankAccount deleteSmartphone(@PathVariable long accountNum) throws BankAccountException {
//		BankAccount bk=bankAccountDao.get(accountNum);
//		bankAccountDao.delete(bk);
//		bankAccountDao.saveorUpdate(bk);
//		return bk;
//	}
	
	@RequestMapping(value="/bankAccount/delete")
	public void deleteBankAccount(String accountNum,HttpServletResponse rep) throws BankAccountException{
		System.out.println("Call delete account!");
		try{
			String account = URLDecoder.decode(accountNum, "utf-8");
			rep.setContentType("text/html;charset=UTF-8");
			OutputStream printWriter = null;
			printWriter = rep.getOutputStream();
			System.out.println("!!!!!!!! = " + account);
			BankAccount bk=bankAccountDao.get(Long.parseLong(account));
			bankAccountDao.delete(bk);
			printWriter.write("success".getBytes());
		}catch (Exception e){
			System.out.println("Exception!");
			e.printStackTrace();
		}
		
	}
	
	@RequestMapping(value="/bankAccount/bind")
	public void bindBankAccount(String accountNum,HttpServletResponse rep) throws BankAccountException{
		System.out.println("Call bind account!");
		try{
			String account = URLDecoder.decode(accountNum, "utf-8");
			rep.setContentType("text/html;charset=UTF-8");
			OutputStream printWriter = null;
			printWriter = rep.getOutputStream();
			System.out.println("!!!!!!!! = " + account);
			BankAccount bankAccount=bankAccountDao.get(Long.parseLong(account));
			if(bankAccount.isBind()==true){
				printWriter.write("isbind".getBytes());
			}else{
				
				BankAccount isBind=bankAccountDao.findBindCard();
				isBind.setBind(false);
				bankAccountDao.saveorUpdate(isBind);
				bankAccount.setBind(true);
				bankAccountDao.saveorUpdate(bankAccount);
				printWriter.write("change".getBytes());
				
				
			}
//			
			
		}catch (Exception e){
			System.out.println("Exception!");
			e.printStackTrace();
		}
		
	}
	
}
	


