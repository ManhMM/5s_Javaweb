package net.codejava.customer.service;


import net.codejava.customer.entity.Suggestion;
import net.codejava.customer.repository.SuggestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SuggestionService {

	@Autowired SuggestionRepository repo;
	
	public void save(Suggestion suggestion) {
		repo.save(suggestion);
	}
	
	public Suggestion get(Long id) {
		return repo.findById(id).get();
	}
	
	public Suggestion findByUserAndArticle(String user_id, Long article_id) {
		return repo.findByUserAndArticle(user_id, article_id);
	}
	
	public void delete(Long id) {
		repo.deleteById(id);
	}
}
