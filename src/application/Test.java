package application;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;
import models.Group;
import models.Teacher;
import models.Teacher_UE_Year;
import models.UE;
import models.Year;
import utils.CombinedDataGroup;
import utils.GenericEntity;
import utils.JPAUtil;

public class Test extends Application {
    public static void main(String[] args) {
//        launch(args);
        
    	EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
    	
    	GenericEntity<Year> yearEntity = new GenericEntity<>(entityManager, Year.class);
    	
    	GenericEntity<UE> ueEntity = new GenericEntity<>(entityManager, UE.class);
    	
    	GenericEntity<Group> groupEntity = new GenericEntity<>(entityManager, Group.class);
    	
    	GenericEntity<Teacher> teacherEntity = new GenericEntity<>(entityManager, Teacher.class);

    	
    	List<CombinedDataGroup> combinedDataList = new ArrayList<>();
    	
    	
   
    	
        
//    	Teacher teacher = new Teacher("N'Tapke", "Tchimou", "0767123456", "tchimou@gmail.com", "MAITRE DE CONFERENCES");
//    	teacherEntity.create(teacher);
//    	
//    	
//    	UE ue = new UE();
//    	ue.setLabel("Genie Logiciel Avancé");
//    	ue.setCode("GLA01");
//    	ue.setnHoursCM(30);
//    	ue.setnHoursTD(20);
//    	ue.setnHoursTP(30);
//    	
//    	ueEntity.create(ue);
//    	
////    	UE ue = ueEntity.read(351);
//    	    	
//    	Year year = yearEntity.read(1);
//    	
//    	System.out.println(year.getLabel() + ' ' + ue.getLabel());
//    	
//    	Group group = new Group(ue, year, 1, 2, 2);
////    	
//    	groupEntity.create(group);
    	    	
    	Year year = yearEntity.read(1);
//    	
//    	System.out.println(year.getGroups());
    	
    	UE ue = ueEntity.read(3);
    	
//    	System.out.println(ue);
    	
    	TypedQuery<Group> query = entityManager.createQuery("SELECT g FROM Group g WHERE g.year = :year", Group.class);
    	query.setParameter("year", year);
    	
    	List<Group> groups = query.getResultList();
    	
   
//    	System.out.println(groups);
    	
    	System.out.println(year.getGroups().size());
    	
    	System.out.println(ue.getGroups());
    	
//    	for(Group group: ue.getGroups()) {
//    		CombinedDataGroup combinedData = new CombinedDataGroup();
//    		combinedData.setCode(ue.getCode());
//    		combinedData.setLabel(ue.getLabel());
//    		combinedData.setNHoursCM(ue.getnHoursCM());
//            combinedData.setNHoursTD(ue.getnHoursTD());
//            combinedData.setNHoursTP(ue.getnHoursTP());
//            combinedData.setNGroupsCM(group.getnGroupsCM());
//            combinedData.setNGroupsTD(group.getnGroupsTD());
//            combinedData.setNGroupsTP(group.getnGroupsTP());
//            combinedDataList.add(combinedData);
//    	}
//    	
//    	
//    	for(CombinedDataGroup c: combinedDataList) {
//    		System.out.println(c.getNGroupsCM());
//    	}
    	
    	for(Year yearAnnee: yearEntity.all()) {
    		System.out.println(yearAnnee.getTeacher_UE_Years());
    	}
    	
    	System.out.println("ue.getTeacher_UE_Years() = " + ue.getTeacher_UE_Years().size());
    	
    	for(int sum: getSumAttributionHoursByUE(ue)) {
    		System.out.println(sum);
    	}
    	
    	
    	
    	
        
    }

    @Override
    public void start(Stage primaryStage) {
        TextField textField = new TextField();
        Label label = new Label("Name");
        
        
        TextField textField2 = new TextField();
        
        TranslateTransition transition = new TranslateTransition(Duration.millis(200), label);
         // Ajustez la valeur en fonction de votre mise en page
        
        
        
        textField.focusedProperty().addListener((obs, oldValue, newValue) -> {
            System.out.println(newValue);
            if(newValue) {
            	 transition.setRate(1.0);
            	 transition.setToY(-23);
                 transition.play();
                 System.out.println("focused");
            } else {
//            	transition.setRate(-1.0);
                transition.setToY(0);
                transition.play();
                System.out.println("not focused");

            }
        });
        
        VBox vBox = new VBox(30);
        
        
        StackPane root = new StackPane();
        root.getChildren().addAll(textField, label);
        
        vBox.getChildren().addAll(textField2, root);
        
        Scene scene = new Scene(vBox, 300, 200);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    private static int[] getSumAttributionHoursByUE(UE ue) {
    	int [] sum = new int[3];
    	int sumCM = 0, sumTD = 0, sumTP = 0;
    	for(Teacher_UE_Year tuy: ue.getTeacher_UE_Years()) {
//    		System.out.println(tuy.getNHoursCMAss());
    		
    		sumCM += tuy.getNHoursCMAss();
    		sumTD += tuy.getNHoursTDAss();
    		sumTP += tuy.getNHoursTPAss();
    	}
    	sum[0] = sumCM; sum[1] = sumTD; sum[2] = sumTP;
    	
    	return sum;
      
    }

}
