package net.codejava.customer.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller(value = "homeControllerOfAdmin")
public class HomeController {

	@RequestMapping("/manager/home")
	public ModelAndView home() {
//		List<Customer> listCustomer = customerService.listAll();
		ModelAndView mav = new ModelAndView("admin/home");
//		mav.addObject("listCustomer", listCustomer);
		return mav;
	}

}
