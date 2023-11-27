module hours_repartition_project {
	requires javafx.controls;
	requires javafx.fxml;
	requires com.jfoenix;
	requires fontawesomefx;
	requires java.persistence;
	requires java.instrument;
	requires java.sql;
	requires org.eclipse.persistence.asm;
	
	
	opens application to javafx.graphics, javafx.fxml;
	opens controllers to javafx.graphics, javafx.fxml;
	opens models to org.eclipse.persistence.core, javafx.base;
	opens utils to org.eclipse.persistence.core, javafx.base;
	
}
