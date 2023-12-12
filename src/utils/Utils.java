package utils;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import models.Group;
import models.Teacher;
import models.Teacher_UE_Year;
import models.UE;
import models.Year;

public class Utils {
	
	private static  EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
	
	private static GenericEntity<Teacher> teacherEntity = new GenericEntity<>(entityManager, Teacher.class);
	private static GenericEntity<UE> ueEntity = new GenericEntity<>(entityManager, UE.class);
	private static GenericEntity<Year> yearEntity = new GenericEntity<>(entityManager, Year.class);
	private static GenericEntity<Group> groupEntity = new GenericEntity<>(entityManager, Group.class);
	private static GenericEntity<Teacher_UE_Year> tuyEntity = new GenericEntity<Teacher_UE_Year>(entityManager, Teacher_UE_Year.class);

	

	private static ObservableList<Teacher> obsListTeacher = FXCollections.observableArrayList(teacherEntity.all());
	private static ObservableList<UE> obsListUE = FXCollections.observableArrayList(ueEntity.all());
	private static ObservableList<Year> obsListYear = FXCollections.observableArrayList(yearEntity.all());


	
	
	public static ObservableList<Teacher> getObsListTeacher() {
		return obsListTeacher;
	}
	
	public static ObservableList<UE> getObsListUE() {
		return obsListUE;
	}
	
	public static ObservableList<Year> getObsListYear() {
		return obsListYear;
	}
	
	public static GenericEntity<Teacher> getTeacherEntity() {
		return teacherEntity;
	}
	
	public static GenericEntity<UE> getUeEntity() {
		return ueEntity;
	}
	
	public static GenericEntity<Year> getYearEntity() {
		return yearEntity;
	}
	
	public static GenericEntity<Group> getGroupEntity() {
		return groupEntity;
	}
	
	public static GenericEntity<Teacher_UE_Year> getTuyEntity() {
		return tuyEntity;
	}
	
//	public static ObservableList<CombinedDataAttribution> getCombinedDataAttributionObsList(){
//		return combinedDataObsList;
//	}
	
	public static Group getGroupByYearAndUe(UE ue, Year year) {
		TypedQuery<Group> query = entityManager.createQuery(
			    "SELECT g FROM Group g WHERE g.ue = :ue AND g.year = :year",
			    Group.class
		);

		query.setParameter("ue", ue);
		query.setParameter("year", year); 
		System.out.println("In getGroupByYearAndUe : " + query.getResultList().size());
	
		return (query.getResultList().size() == 0) ? null : query.getResultList().get(0);
	}
	
	public static List<Teacher_UE_Year> getTuyByYearAndUe(UE ue, Year year) {
		TypedQuery<Teacher_UE_Year> query = entityManager.createQuery(
			    "SELECT t FROM Teacher_UE_Year t WHERE t.ue = :ue AND t.year = :year",
			    Teacher_UE_Year.class
		);

		query.setParameter("ue", ue);
		query.setParameter("year", year); 
		System.out.println("In getTuyByYearAndUe : " + query.getResultList().size());
		return query.getResultList();
	}
	
	
	// This request updated the tuy list after adding an element
	public static List<Teacher_UE_Year> getTeacherUEYearsByUE(UE ue) {
	    TypedQuery<Teacher_UE_Year> query = entityManager.createQuery(
	        "SELECT t FROM Teacher_UE_Year t WHERE t.ue = :ue", Teacher_UE_Year.class);
	    query.setParameter("ue", ue);
	    return query.getResultList();
	}
	
	public static boolean checkAssigned(Year year, UE ue, Teacher teacher) {
		TypedQuery<Teacher_UE_Year> query = entityManager.createQuery(
		        "SELECT t FROM Teacher_UE_Year t WHERE t.ue = :ue AND t.year = :year AND t.teacher = :teacher", Teacher_UE_Year.class);
		 query.setParameter("ue", ue);
		 query.setParameter("year", year);
		 query.setParameter("teacher", teacher);
		 		 
		 return (query.getResultList().size() == 0) ? false : true ;
	}
	
	public static Teacher_UE_Year getTUYByTeacherUEandYear(Year year, UE ue, Teacher teacher) {
		TypedQuery<Teacher_UE_Year> query = entityManager.createQuery(
		        "SELECT t FROM Teacher_UE_Year t WHERE t.ue = :ue AND t.year = :year AND t.teacher = :teacher", Teacher_UE_Year.class);
		 query.setParameter("ue", ue);
		 query.setParameter("year", year);
		 query.setParameter("teacher", teacher);
		 		 
		 return query.getSingleResult();
	}
	
	
	public static List<Teacher_UE_Year> getTuyByTeacherAndYear(Teacher teacher, Year year) {
	    TypedQuery<Teacher_UE_Year> query = entityManager.createQuery(
	        "SELECT t FROM Teacher_UE_Year t WHERE t.teacher = :teacher and t.year = :year", Teacher_UE_Year.class);
	    query.setParameter("teacher", teacher);
	    query.setParameter("year", year);

	    return query.getResultList();
	}
	
	public static Object[] getSum(Teacher teacher, Year year) {	
		TypedQuery<Object[]> query = entityManager.createQuery(
		        "SELECT SUM(t.nHoursCMAss), SUM(t.nHoursTDAss), SUM(t.nHoursTPAss) FROM Teacher_UE_Year t WHERE t.teacher = :teacher AND t.year = :year", Object[].class);
		
		query.setParameter("teacher", teacher);
		query.setParameter("year", year);
		
		return query.getSingleResult();
		
	}
	
	public static void labelCenter(Label ...labels) {
    	for(Label label: labels) {
//    		System.out.println(label.getText().length());
    		if(label.getText().length() == 1)
    			label.setLayoutX(24);
    		else
    			label.setLayoutX(20);
    	}
    }
}
