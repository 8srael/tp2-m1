package application;
	
import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.util.Duration;


public class Tp2Main extends Application {
	double x, y = 0;
	@Override
	public void start(Stage primaryStage) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/views/home.fxml"));
			primaryStage.setTitle("Hours Repartition Project");
	        Image icon = new Image("C:\\M1_WORKSPACE\\hours_repartition_project\\src\\pics\\dash-pic.png");

//			primaryStage.initStyle(StageStyle.UNDECORATED);
			FadeTransition fadeIn = new FadeTransition(Duration.seconds(2), root);
		    fadeIn.setFromValue(0);
		    fadeIn.setToValue(1);
		
		    fadeIn.play();
			
			root.setOnMousePressed((e) -> {
				x = e.getSceneX();
				y = e.getSceneY();
			});
			
			root.setOnMouseDragged((e) -> {
				primaryStage.setX(e.getSceneX() - x);
				primaryStage.setY(e.getSceneY() - y);
			});
			primaryStage.setScene(new Scene(root));
	        primaryStage.getIcons().add(icon); 
			primaryStage.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
