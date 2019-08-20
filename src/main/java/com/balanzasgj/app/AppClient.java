package com.balanzasgj.app;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;

public class AppClient extends Application {
	private Scene scene;

	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("Balanzas GJ");
		Screen screen = Screen.getPrimary();
	    Rectangle2D bounds = screen.getVisualBounds();
	    primaryStage.setWidth(bounds.getWidth());
	    primaryStage.setHeight(bounds.getHeight());
		
		Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/PrincipalView.fxml"));
		this.scene = new Scene(root);		
		scene.getStylesheets().add(getClass().getClassLoader().getResource("fxml/style.css").toExternalForm());
		
		primaryStage.setScene(scene);
		primaryStage.resizableProperty().set(false);
		primaryStage.show();
		
		primaryStage.setOnHiding(new EventHandler<WindowEvent>() {

            public void handle(WindowEvent event) {
            	System.exit(0);
            }
        });		
	}
	
	public static void iniciar(){
		String[] args = {};
		launch(args);
	}
}