package utils;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import javafx.collections.FXCollections;


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
}
