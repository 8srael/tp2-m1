package utils;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import javafx.collections.FXCollections;
import models.Group;
import models.UE;
import models.Year;


public class GenericEntity <E> {
	private final EntityManager entityManager ;
	private final Class<E> entityClass;
	
	public GenericEntity(EntityManager entityManager, Class<E> entityClass) {
		this.entityManager = entityManager;
		this.entityClass = entityClass;		
	}
	
	 public void create(E entity) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(entity);
        transaction.commit();
		System.out.println("------------------------------------- "+ entityClass.getName() +" created sucessfully -------------------------------------");
    }
	 
	public E read(long id) {
		return this.entityManager.find(entityClass, id);
	}
	

	public E update(E entity) {
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		E mergeEntity = entityManager.merge(entity);
		transaction.commit();
		System.out.println("------------------------------------- "+ entityClass.getName() +" updated sucessfully -------------------------------------");
		
		return mergeEntity;
		
	}
	
	public void delete(long id) {
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		E entity = entityManager.find(entityClass, id);
		if (entity != null) {
			entityManager.remove(entity);
		}
		
		transaction.commit();
		
		System.out.println("------------------------------------- "+ entityClass.getName() +" deleted sucessfully -------------------------------------");
	}
	
	@SuppressWarnings("unchecked")
	public List<E> all() {
		return FXCollections.observableArrayList(entityManager.createQuery("SELECT t FROM " + this.entityClass.getSimpleName() + " t").getResultList());
	}
	
	public Group getGroupByYearAndUe(UE ue, Year year) {
		TypedQuery<Group> query = entityManager.createQuery(
			    "SELECT g FROM Group g WHERE g.ue = :ue AND g.year = :year",
			    Group.class
		);

		query.setParameter("ue", ue);
		query.setParameter("year", year); 
		return query.getResultList().get(0);
	}
	
}
