package net.codejava.customer.controller.admin;

import net.codejava.customer.entity.Account;
import net.codejava.customer.entity.Category;
import net.codejava.customer.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;
import java.util.Map;

//@Controller(value = "categoryControllerOfAdmin")
@Controller
public class CategoryController {

	@Autowired
	private CategoryService categoryService;
	
	@RequestMapping("/manager/category")
	public ModelAndView home() {
		List<Category> listCategory = categoryService.listAll();
		ModelAndView mav = new ModelAndView("admin/category");
		mav.addObject("listCategory", listCategory);
		return mav;
	}
	
	@RequestMapping("/manager/category/new")
	public String newCategoryForm(Map<String, Object> model,HttpServletRequest request) {   
		Category category = new Category();
		model.put("category", category);
		return "admin/new_category";
	}
	
	@RequestMapping("/manager/category/edit")
	public ModelAndView editCategoryForm(@RequestParam long id) { 
		ModelAndView mav = new ModelAndView("admin/edit_category");
		Category category = categoryService.get(id);
		mav.addObject("category", category);	
		return mav;
	}
	
	@RequestMapping(value = "/manager/category/new", method = RequestMethod.POST)
	public String saveAccount(HttpSession session, @ModelAttribute("category") Category category, HttpServletResponse response) {
		response.setCharacterEncoding("utf-8");

			Account account = (Account) session.getAttribute("account");
			if(account != null) {
				category.setCreateBy(account.getFullname());
				category.setCreateDate(new Date());
				categoryService.save(category);
			}else {
				return "redirect:/login";
			}

		return "redirect:/manager/category";
	}
	
	@RequestMapping("/manager/category/delete")
	public String deleteAccountForm(@RequestParam long id) {
		categoryService.delete(id);
		return "redirect:/manager/category";		
	}
}
