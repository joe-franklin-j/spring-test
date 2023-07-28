package org.jsp.springtestapp.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.jsp.springtestapp.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDao {
	@Autowired
    EntityManager manager;
	
	public User saveUser(User u) {
		EntityTransaction t=manager.getTransaction();
		manager.persist(u);
		t.begin();
		t.commit();
		return u;	
	}
	public User updateUser(User u) {
		EntityTransaction t=manager.getTransaction();
		manager.merge(u);
		t.begin();
		t.commit();
		return u;
	}
	public User varifyEamilAndPassword(String email,String password) {
		Query q=manager.createQuery("select u from User u where u.email=?1 and u.password=?2");
		q.setParameter(1, email);
		q.setParameter(2, password);
	
		try {
			return(User) q.getSingleResult();
		}
		catch (NoResultException e) {
			return null;		
			}
		}
	public User varifyPhoneAndPassword(long phone,String password) {
		Query q=manager.createQuery("select u from User u where u.phone=?1 and u.password=?2");
		q.setParameter(1, phone);
		q.setParameter(2, password);
	
		try {
			return(User) q.getSingleResult();
		}
		catch (NoResultException e) {
			return null;		
			}
		}
}
