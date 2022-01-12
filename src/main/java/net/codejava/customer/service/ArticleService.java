package net.codejava.customer.service;

import net.codejava.customer.entity.Article;
import net.codejava.customer.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ArticleService {

	@Autowired ArticleRepository repo;
	
	public void save(Article article) {
		repo.save(article);
	}
	
	public List<Article> listAll() {
		return (List<Article>) repo.findAll();
	}
	
	public List<Article> findAllByCategory(Long id){
		return (List<Article>) repo.findAllByCategory(id);
	}

	public List<Article> findAllBySearch(String name){
		return (List<Article>) repo.findAllBySearch(name);
	}
	
	public List<Article> findAllByView(){
		return (List<Article>) repo.findAllByView();
	}
	
	public List<Article> findAllByHighlight(int limit) {
		return (List<Article>) repo.findAllByHighlight(limit);
	}
	
	public List<Article> findTopByCreateDate() {
		return (List<Article>) repo.findTopByCreateDate();
	}
	
	public List<Article> findTopByCreateDateInDay() {
		return (List<Article>) repo.findTopByCreateDateInDay();
	}
	
	public List<Article> findSuggestionForUser(String user_id) {
		return (List<Article>) repo.findSuggestionForUser(user_id);
	}

	public Article get(Long id) {
		return repo.findById(id).get();
	}
	
	public void delete(Long id) {
		repo.deleteById(id);
	}
}
