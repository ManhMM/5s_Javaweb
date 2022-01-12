package net.codejava.customer.controller.admin;

import net.codejava.customer.entity.Account;
import net.codejava.customer.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller(value = "loginControllerOfAdmin")
public class LoginController {
	
	@Autowired
	private AccountService accountService;

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(HttpSession session, ModelMap model,
			@RequestParam(value = "error", required = false) String error) {
		session.setAttribute("user", null);
		try {
			if (error.equals("true")) {
				model.put("error", "Tên đăng nhập hoặc mật khẩu không đúng !!");
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return "login";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(HttpSession session, ModelMap model, @RequestParam("email") String email,
			@RequestParam("password") String password) {

		if (email.equals("") || password.equals("")) {
			return "redirect:/login?error=true";
		}

		Account account = new Account();
		account = accountService.login(email, password);
		session.setAttribute("account", account);
		if (account == null) {
			return "redirect:/login?error=true";
		}

		if (account.getRole() == 1) {
			return "redirect:/manager/home";
		} else if (account.getRole() == 0) {
			return "redirect:/home";
		}
		return "redirect:/login?error=true";
	}
	
	
	@RequestMapping(value = "logout", method = RequestMethod.GET)
	public String logout(HttpSession session, ModelMap model,
			@RequestParam(value = "error", required = false) String error) {
		session.removeAttribute("account");
		return "login";
	}
}
