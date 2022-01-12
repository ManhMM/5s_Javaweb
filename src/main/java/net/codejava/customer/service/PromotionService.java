package net.codejava.customer.service;

import net.codejava.customer.entity.Promotion;
import net.codejava.customer.repository.PromotionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PromotionService {

	@Autowired PromotionRepository repo;
	
	public void save(Promotion promotion) {
		repo.save(promotion);
	}
	
	public List<Promotion> listAll() {
		return (List<Promotion>) repo.findAll();
	}

	public Promotion get(Long id) {
		return repo.findById(id).get();
	}
	
	public void delete(Long id) {
		repo.deleteById(id);
	}
}
