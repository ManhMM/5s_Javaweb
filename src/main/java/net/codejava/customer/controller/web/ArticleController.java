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

@Controller(value = "articleControllerOfWeb")
public class ArticleController {
	
	@Autowired
	private ArticleService articleService;
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private PromotionService promotionService;

	@RequestMapping("/the-loai")
	public ModelAndView home(@RequestParam long id) {
		List<Article> listArticle = articleService.findAllByCategory(id);
		List<Article> findTopByCreateDate = articleService.findTopByCreateDate();
		List<Category> listCategory = categoryService.listAll();
		List<Promotion> listPromotion = promotionService.listAll();
		
		ModelAndView mav = new ModelAndView("web/category");
		mav.addObject("listArticle", listArticle);
		mav.addObject("findTopByCreateDate", findTopByCreateDate);
		mav.addObject("listCategory", listCategory);
		mav.addObject("listPromotion", listPromotion);
		return mav;
	}
	
	@RequestMapping("/tin-nong")
	public ModelAndView hotNews() {
		List<Article> listArticle = articleService.findAllByView();
		List<Article> findTopByCreateDate = articleService.findTopByCreateDate();
		List<Category> listCategory = categoryService.listAll();
		List<Promotion> listPromotion = promotionService.listAll();
		
		ModelAndView mav = new ModelAndView("web/category");
		mav.addObject("listArticle", listArticle);
		mav.addObject("findTopByCreateDate", findTopByCreateDate);
		mav.addObject("listCategory", listCategory);
		mav.addObject("listPromotion", listPromotion);
		return mav;
	}
	
	@RequestMapping("/tin-moi")
	public ModelAndView createNews() {
		List<Article> listArticle = articleService.findTopByCreateDateInDay();
		List<Article> findTopByCreateDate = articleService.findTopByCreateDate();
		List<Category> listCategory = categoryService.listAll();
		List<Promotion> listPromotion = promotionService.listAll();
		
		ModelAndView mav = new ModelAndView("web/category");
		mav.addObject("listArticle", listArticle);
		mav.addObject("findTopByCreateDate", findTopByCreateDate);
		mav.addObject("listCategory", listCategory);
		mav.addObject("listPromotion", listPromotion);
		return mav;
	}
	
}
