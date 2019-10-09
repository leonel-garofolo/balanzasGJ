package com.balanzasgj.app;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class AppClient extends Application {
	private Scene scene;

	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("Sistemas de Balanzas");
		/*
		Screen screen = Screen.getPrimary();
	    Rectangle2D bounds = screen.getVisualBounds();
	    primaryStage.setWidth(bounds.getWidth());
	    primaryStage.setHeight(bounds.getHeight());
	    */
		
		FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("fxml/LoginView.fxml"));
		Parent rootLogin = (Parent)loader.load();
		this.scene = new Scene(rootLogin);		
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