package org.jsp.springtestapp.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.jsp.springtestapp.dto.Product;
import org.jsp.springtestapp.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ProductDao {
	@Autowired
     EntityManager manager;

public Product addProduct(Product p,int user_id ) {
	User u=manager.find(User.class, user_id);
	if(u!=null) {
		u.getProducts().add(p);
		p.setUsers(u);
	EntityTransaction t=manager.getTransaction();
	manager.persist(p);
	t.begin();
	t.commit();
	return p;
	}
	return null;
}

public Product findProduct(int id) {
	return manager.find(Product.class, id);	
}
public Product findCategory(String category) {
	Query q=manager.createQuery("select p from Product p where p.category=?1");
	q.setParameter(1, category);
	try {
		return (Product) q.getSingleResult();
	}catch (NoResultException e) {
		return null;	
		}
	
	
}
}
