package net.codejava.customer.controller.admin;

import net.codejava.customer.entity.Account;
import net.codejava.customer.entity.Article;
import net.codejava.customer.entity.Category;
import net.codejava.customer.service.ArticleService;
import net.codejava.customer.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;
import java.util.Map;

//@Controller(value = "articleControllerOfAdmin")
@Controller
public class ArticleController {

	@Autowired
	private ArticleService articleService;
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	ServletContext context;
	
	@RequestMapping("/manager/article")
	public ModelAndView home() {
		List<Article> listArticle = articleService.listAll();
		ModelAndView mav = new ModelAndView("admin/article");
		mav.addObject("listArticle", listArticle);
		return mav;
	}
	
	@RequestMapping(value = "/manager/article/new", method = RequestMethod.GET)
	public String newArticleForm(Map<String, Object> model,HttpServletRequest request) {   
		Article article = new Article();
		List<Category> listCategory = categoryService.listAll();
		model.put("article", article);
		model.put("listCategory", listCategory);
		return "admin/new_article";
	}
	
	@RequestMapping("/manager/article/edit")
	public ModelAndView editArticleForm(@RequestParam long id) { 
		ModelAndView mav = new ModelAndView("admin/edit_article");
		Article article = articleService.get(id);
		mav.addObject("listCategory", categoryService.listAll());
		mav.addObject("article", article);	
		return mav;
	}
	
	@RequestMapping(value = "/manager/article/new", method = RequestMethod.POST)
	public String saveArticle(HttpSession session, @ModelAttribute("article") Article article, @RequestParam("file") MultipartFile multipartFile) {
		try {

				Account account = (Account) session.getAttribute("account");
				if(account != null) { 
					if (!multipartFile.isEmpty()) {
						String fileName = multipartFile.getOriginalFilename();						
						String absolutePath = context.getRealPath("/WebConent/photo/");
	
						Path uploadPath = Paths.get(absolutePath);
//						System.out.println("hello" + uploadPath);
						File file = new File("C:/Users/manhk/OneDrive/Máy tính/GameSystem (1)/GameSystem/src/main/webapp/template/photo", fileName);
//						System.out.println("hello1" + file);
						multipartFile.transferTo(file); 
						article.setPhoto(fileName);
					}
					System.out.println(article.getCategory().getId());
					Category category = categoryService.get(article.getCategory().getId());
					System.out.println(category.getName());
					article.setCategory(category);
					System.out.println(article.getCategory().getName());
					article.setCreateBy(account.getFullname());
					article.setCreateDate(new Date());
					articleService.save(article);
				}else {
					return "redirect:/login";
				}

		} catch (Exception e) {
			System.out.println("lỗi:====================" + e);
		}
		return "redirect:/manager/article";
	}
	
	@RequestMapping("/manager/article/delete")
	public String deleteArticleForm(@RequestParam long id) {
		articleService.delete(id);
		return "redirect:/manager/article";		
	}
}
