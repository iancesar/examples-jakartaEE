package br.com.jakartaEE.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.jakartaEE.entities.Users;

@Stateless
public class UsersDAO {

	@PersistenceContext
	private EntityManager entityManager;

	public List<Users> findAll() {
		return entityManager.createQuery("select u from Users u", Users.class).getResultList();
	}

	public Users save(Users user) {
		entityManager.persist(user);
		return user;
	}

}
