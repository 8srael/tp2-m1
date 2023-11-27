package utils;

import javax.persistence.EntityManager;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import models.Teacher;
import models.UE;
import models.Year;

public class Utils {
	
	private static  EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
	
	private static  GenericEntity<Teacher> teacherEntity = new GenericEntity<>(entityManager, Teacher.class);
	private static  GenericEntity<UE> ueEntity = new GenericEntity<>(entityManager, UE.class);
	private static  GenericEntity<Year> yearEntity = new GenericEntity<>(entityManager, Year.class);

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
	
//	public static ObservableList<CombinedDataAttribution> getCombinedDataAttributionObsList(){
//		return combinedDataObsList;
//	}

}
