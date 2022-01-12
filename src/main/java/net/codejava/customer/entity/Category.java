package net.codejava.customer.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Entity
public class Category {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String slug;
	private Date createDate;
	private String createBy;
	
	@OneToMany(mappedBy = "category", cascade = CascadeType.MERGE)
	private List<Article> articles = new ArrayList<Article>();

	public Category() {}

	public Category(Long id, String name, String slug, Date createDate, String createBy, List<Article> articles) {
		this.id = id;
		this.name = name;
		this.slug = slug;
		this.createDate = createDate;
		this.createBy = createBy;
		this.articles = articles;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSlug() {
		return slug;
	}

	public void setSlug(String slug) {
		this.slug = slug;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public List<Article> getArticles() {
		return articles;
	}

	public void setArticles(List<Article> articles) {
		this.articles = articles;
	}
}
