package net.codejava.customer.controller.admin;

import net.codejava.customer.entity.Account;
import net.codejava.customer.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

@Controller(value = "accountControllerOfAdmin")
public class AccountController {

	@Autowired
	private AccountService accountService;
	
	@RequestMapping("/manager/account")
	public ModelAndView home() {
		List<Account> listAccount = accountService.listAll();
		ModelAndView mav = new ModelAndView("admin/account");
		mav.addObject("listAccount", listAccount);
		return mav;
	}
	
	@RequestMapping("/manager/account/new")
	public String newAccountForm(Map<String, Object> model,HttpServletRequest request) {   
		Account account = new Account();
		model.put("account", account);
		return "admin/new_account";
	}

	
	@RequestMapping("/manager/account/edit")
	public ModelAndView editCustomerForm(@RequestParam long id) {
		ModelAndView mav = new ModelAndView("admin/edit_account");
		Account account = accountService.get(id);
		mav.addObject("account", account);
		
		return mav;
	}
	
	@RequestMapping(value = "/manager/account/new", method = RequestMethod.POST)
	public String saveAccount(@ModelAttribute("account") Account account, HttpServletResponse response) {
		response.setCharacterEncoding("utf-8");  
		accountService.save(account);
		return "redirect:/manager/account";
	}
	
	@RequestMapping("/manager/account/delete")
	public String deleteAccountForm(@RequestParam long id) {
		accountService.delete(id);
		return "redirect:/manager/account";		
	}
}
