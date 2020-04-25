package br.com.jakartaEE.dao;

import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import br.com.jakartaEE.entities.Entity;
import br.com.jakartaEE.exceptions.BusinessException;

@SuppressWarnings("rawtypes")
public class DAO<T extends Entity>
{

	@PersistenceContext
	private EntityManager	entityManager;

	private Class<T>			persistentClass;

	@SuppressWarnings("unchecked")
	public DAO()
	{
		this.persistentClass = (Class<T>) ((ParameterizedType) getClass()//
			.getGenericSuperclass())//
				.getActualTypeArguments()[0];
	}

	public Optional<T> findById(Object id)
	{

		Optional<T> optional = Optional.ofNullable(entityManager.find(persistentClass, id));

		optional.orElseThrow(() -> new BusinessException("Registro não exite"));

		return optional;
	}

	public List<T> findAll()
	{
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<T> query = builder.createQuery(persistentClass);
		Root<T> root = query.from(persistentClass);
		query.select(root);

		return entityManager.createQuery(query).getResultList();

	}

	public T save(T t)
	{
		if(t.getId() != null)
		{
			t = update(t);
		}
		else
		{
			entityManager.persist(t);
		}
		entityManager.flush();
		return t;
	}

	public T update(T t)
	{

		findById(t.getId()); //Check if regisister exists

		t = entityManager.merge(t);
		return t;
	}

	public void delete(Object id)
	{

		Optional<T> optional = findById(id);

		entityManager.remove(optional.get());
	}

}
