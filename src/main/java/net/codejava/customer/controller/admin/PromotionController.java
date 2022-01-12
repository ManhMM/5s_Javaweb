package net.codejava.customer.controller.admin;

import net.codejava.customer.entity.Account;
import net.codejava.customer.entity.Promotion;
import net.codejava.customer.service.PromotionService;
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
public class PromotionController {

	@Autowired
	private PromotionService promotionService;
	
	@Autowired
	ServletContext context;
	
	@RequestMapping("/manager/promotion")
	public ModelAndView home() {
		List<Promotion> listPromotion = promotionService.listAll();
		ModelAndView mav = new ModelAndView("admin/promotion");
		mav.addObject("listPromotion", listPromotion);
		return mav;
	}
	
	@RequestMapping(value = "/manager/promotion/new", method = RequestMethod.GET)
	public String newPromotionForm(Map<String, Object> model,HttpServletRequest request) {   
		Promotion promotion = new Promotion();
		model.put("promotion", promotion);
		return "admin/new_promotion";
	}
	
//	@RequestMapping("/manager/article/edit")
//	public ModelAndView editArticleForm(@RequestParam long id) { 
//		ModelAndView mav = new ModelAndView("admin/edit_article");
//		Article article = articleService.get(id);
//		mav.addObject("article", article);	
//		return mav;
//	}
	
	@RequestMapping(value = "/manager/promotion/new", method = RequestMethod.POST)
	public String savePromotion(HttpSession session, @ModelAttribute("promotion") Promotion promotion, @RequestParam("file") MultipartFile multipartFile) {

		try {
			if(promotion.getId() == null) {
				Account account = (Account) session.getAttribute("account");
				if(account != null) { 
					if (!multipartFile.isEmpty()) {
						String fileName = multipartFile.getOriginalFilename();						
						String absolutePath = context.getRealPath("/WebConent/photo/");
	
						Path uploadPath = Paths.get(absolutePath);
						System.out.println("hello" + uploadPath);
						File file = new File("C:/Users/manhk/OneDrive/Máy tính/GameSystem (1)/GameSystem/src/main/webapp/template/photo", fileName);
						System.out.println("hello1" + file);
						multipartFile.transferTo(file); 
						promotion.setPhoto(fileName);
					}
					promotion.setCreateBy(account.getFullname());
					promotion.setCreateDate(new Date());
					promotionService.save(promotion);
				}else {
					return "redirect:/login";
				}
			}
		} catch (Exception e) {
			System.out.println("lỗi:====================" + e);
		}
		return "redirect:/manager/promotion";
	}
	
	@RequestMapping("/manager/promotion/delete")
	public String deletePromotionForm(@RequestParam long id) {
		promotionService.delete(id);
		return "redirect:/manager/promotion";		
	}
}
