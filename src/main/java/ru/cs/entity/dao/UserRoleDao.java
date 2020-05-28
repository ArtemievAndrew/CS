package ru.cs.entity.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ru.cs.entity.SUserRole;

@Repository
@Transactional
public class UserRoleDao {

	@Autowired
	private EntityManager entityManager;

	@SuppressWarnings("unchecked")
	public List<String> getRoleNames(Long userId) {
		String selectStr = "select ur.role.name from " + SUserRole.class.getName() + " ur " //
				+ " where ur.user.id = :userId ";

		Query query = this.entityManager.createQuery(selectStr, String.class);
		query.setParameter("userId", userId);
		return query.getResultList();
	}

}