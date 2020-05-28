package ru.cs.entity.dao;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ru.cs.entity.SUser;
 
@Repository
@Transactional
public class UserDao {

	@Autowired
	private EntityManager entityManager;

	public SUser findUserAccount(String name) {
		try {
			String select = "select e from " + SUser.class.getName() + " e " + 
						" where e.name = :name ";

			Query query = entityManager.createQuery(select, SUser.class);
			query.setParameter("name", name);

			return (SUser) query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

}
