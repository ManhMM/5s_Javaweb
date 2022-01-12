package net.codejava.customer.controller.web;

import net.codejava.customer.entity.*;
import net.codejava.customer.service.ArticleService;
import net.codejava.customer.service.CategoryService;
import net.codejava.customer.service.PromotionService;
import net.codejava.customer.service.SuggestionService;
import net.codejava.customer.utils.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ArticleDetailController extends HttpServlet {

	@Autowired
	private ArticleService articleService;

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private PromotionService promotionService;

	@Autowired
	private SuggestionService suggestionService;

	@RequestMapping(value = "/bai-viet")
	public ModelAndView home(HttpServletResponse response, HttpServletRequest request, HttpSession session,
			@RequestParam long id) {
		List<Article> suggestionList = new ArrayList<Article>();
		try {
			Account account = (Account) session.getAttribute("account");
			if (account != null) {
				Suggestion suggestion = suggestionService.findByUserAndArticle(account.getId().toString(), id);
				if (suggestion == null) {
					Suggestion newSugguestion = new Suggestion();
					newSugguestion.setUser_id(account.getId().toString());
					newSugguestion.setView(1);
					newSugguestion.setArticle_id(id);
					suggestionService.save(newSugguestion);
				} else {
					suggestion.setView(suggestion.getView() + 1);
					suggestionService.save(suggestion);
				}
				suggestionList = articleService.findSuggestionForUser(account.getId().toString());
			} else {
				String user = null;
				try {
					Cookie c[] = request.getCookies();
					user = c[1].getValue();
					Suggestion suggestion = suggestionService.findByUserAndArticle(user, id);
					if (suggestion == null) {
						Suggestion newSugguestion = new Suggestion();
						newSugguestion.setUser_id(user);
						newSugguestion.setView(1);
						newSugguestion.setArticle_id(id);
						suggestionService.save(newSugguestion);
					} else {
						suggestion.setView(suggestion.getView() + 1);
					}
					suggestionService.save(suggestion);
				} catch (Exception e) {
					if (user == null) {

						String random = RandomString.getAlphaNumericString(40);
						Cookie c1 = new Cookie("guest", random);
						response.addCookie(c1);

						Suggestion newSugguestion = new Suggestion();
						newSugguestion.setUser_id(random);
						newSugguestion.setArticle_id(id);
						newSugguestion.setView(1);
						suggestionService.save(newSugguestion);
					}
				}
				suggestionList = articleService.findSuggestionForUser(user);
			}
		} catch (Exception e) {
			System.out.println(e);
		}

		Article article = articleService.get(id);
		article.setView(article.getView() + 1);
		articleService.save(article);
		List<Article> findAllByHighlight = articleService.findAllByHighlight(4);
		List<Article> findTopByCreateDate = articleService.findTopByCreateDate();
		List<Category> listCategory = categoryService.listAll();
		List<Promotion> listPromotion = promotionService.listAll();

		ModelAndView mav = new ModelAndView("web/article_detail");
		mav.addObject("article", article);
		mav.addObject("suggestionList", suggestionList);
		mav.addObject("findTopByCreateDate", findTopByCreateDate);
		mav.addObject("listCategory", listCategory);
		mav.addObject("listPromotion", listPromotion);
		return mav;
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		super.doPost(req, resp);
	}
}
