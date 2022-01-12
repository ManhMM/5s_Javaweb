package net.codejava.customer.repository;

import net.codejava.customer.entity.Article;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ArticleRepository extends CrudRepository<Article, Long>{
	
	@Query(value = "select * from article where category_id = ?1", nativeQuery = true)
	List<Article> findAllByCategory(Long id);
	
	@Query(value = "select * from article inner join category on article.category_id = category.id where article.title like %?1% or category.name like %?1%", nativeQuery = true)
	List<Article> findAllBySearch(String search);
	
	@Query(value = "select * from article ORDER BY view DESC ", nativeQuery = true)
	List<Article> findAllByView();

	@Query(value = "select * from article where highlight = 1 limit ?1", nativeQuery = true)
	List<Article> findAllByHighlight(int limit);
	
	@Query(value = "select * from article ORDER BY createDate DESC limit 7", nativeQuery = true)
	List<Article> findTopByCreateDate();
	
	@Query(value = "select * from article where createDate = CURDATE()", nativeQuery = true)
	List<Article> findTopByCreateDateInDay();

	@Query(value = "SELECT * FROM article WHERE category_id IN (SELECT category.id FROM article INNER JOIN suggestion ON article.id = suggestion.article_id INNER JOIN category ON category.id = article.category_id WHERE article.id IN (SELECT article_id FROM suggestion WHERE user_id = ?1 ORDER BY VIEW DESC) AND user_id = ?1 GROUP BY article.id ORDER BY suggestion.view DESC) LIMIT 4", nativeQuery = true)
	List<Article> findSuggestionForUser(String user_id);
}
