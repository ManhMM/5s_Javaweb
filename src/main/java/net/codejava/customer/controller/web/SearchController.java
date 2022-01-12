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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller(value = "searchControllerOfWeb")
public class SearchController {
	
	@Autowired
	private ArticleService articleService;
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private PromotionService promotionService;

	@RequestMapping("/tim-tin")
	public ModelAndView home(@RequestParam String search) {
		List<Article> listArticle = articleService.findAllBySearch(search);
		List<Article> findTopByCreateDate = articleService.findTopByCreateDate();
		List<Category> listCategory = categoryService.listAll();
		List<Promotion> listPromotion = promotionService.listAll();
		
		ModelAndView mav = new ModelAndView("web/search");
		mav.addObject("listArticle", listArticle);
		mav.addObject("findTopByCreateDate", findTopByCreateDate);
		mav.addObject("listCategory", listCategory);
		mav.addObject("listPromotion", listPromotion);
		return mav;
	}
}
