package com.balanzasgj.app;

import com.balanzasgj.app.view.IView;
import com.balanzasgj.app.view.LoginController;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class AppClient extends Application {
	private Scene scene;

	@Override
	public void start(Stage primaryStage) throws Exception {		
		primaryStage.setTitle("Sistemas de Balanzas v1.26");
		
		FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("fxml/LoginView.fxml"));
		Parent rootLogin = (Parent)loader.load();
		this.scene = new Scene(rootLogin);	
		
		scene.getStylesheets().add(getClass().getClassLoader().getResource("fxml/style.css").toExternalForm());		
		
		if(loader.getController() instanceof LoginController) {
	    	IView controller = (LoginController)loader.getController();
	    	controller.setStage(primaryStage);
	    }
		
		primaryStage.setScene(scene);				
		//Image ico = new Image(this.getClass().getResource(App.PATH_ICONO).getFile()); 
		//primaryStage.getIcons().add(ico);
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