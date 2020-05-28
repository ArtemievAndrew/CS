package ru.cs.entity.dao;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ru.cs.entity.Acc;

@Repository
@Transactional
public class AccDao {
	
	/*
	 * В spring это можно делать проще, но пока так
	 * */
	
	@Autowired
	private EntityManager em;
	
	public void createAcc(Acc acc) {
		System.out.println("create - " + acc);
		//em.persist(acc);
	}
	
	public void updateAcc(Acc acc) {
		System.out.println("update - " + acc);
	}
	
	/*
	 * есть в репозитории
	public void deleteAcc(Long accId) {
		System.out.println("delete - " + accId);
	}
	*/
	
	
	/*
	 * есть в репозитории
	public Acc getAccById(Long accId) {
		System.out.println("select - " + accId);
		return null;
	}
	*/
	
}
