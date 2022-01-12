package net.codejava.customer.controller.web;

import net.codejava.customer.entity.Article;
import net.codejava.customer.entity.Category;
import net.codejava.customer.entity.Promotion;
import net.codejava.customer.service.ArticleService;
import net.codejava.customer.service.CategoryService;
import net.codejava.customer.service.PromotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller(value = "homeControllerOfWeb")
public class HomeController {
	
	@Autowired
	private ArticleService articleService;
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private PromotionService promotionService;

	@RequestMapping("/home")
	public ModelAndView home() {
		List<Article> listArticle = articleService.listAll();
		List<Article> findAllByHighlight = articleService.findAllByHighlight(4);
		List<Article> findTopByCreateDate = articleService.findTopByCreateDate();
		List<Category> listCategory = categoryService.listAll();
		List<Promotion> listPromotion = promotionService.listAll();
		
		ModelAndView mav = new ModelAndView("web/home");
		mav.addObject("listArticle", listArticle);
		mav.addObject("findAllByHighlight", findAllByHighlight);
		mav.addObject("findTopByCreateDate", findTopByCreateDate);
		mav.addObject("listCategory", listCategory);
		mav.addObject("listPromotion", listPromotion);
		return mav;
	}
	
}
