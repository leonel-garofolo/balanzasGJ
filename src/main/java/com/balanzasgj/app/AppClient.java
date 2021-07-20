package com.balanzasgj.app;

import java.io.IOException;

import javafx.stage.Modality;
import javafx.stage.StageStyle;
import org.apache.log4j.Logger;

import com.balanzasgj.app.view.DashboardView;
import com.balanzasgj.app.view.LoginController;
import com.balanzasgj.app.view.MainActions;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class AppClient extends Application implements MainActions {
	final static Logger logger = Logger.getLogger(AppClient.class);	
	private Stage primaryStage;

	@Override
	public void start(Stage primaryStage) throws Exception {	
		Platform.setImplicitExit(true);
		this.primaryStage = new Stage();
		showLogin();
	}
	
	public static void iniciar(){
		String[] args = {};
		launch(args);
	}

	@Override
	public void showLogin() {
		openLogin("LoginView", App.APP_NAME);
	}

	@Override
	public void showDashboard() {
		primaryStage.close();
		DashboardView dashboardView = new DashboardView(this);
		primaryStage.setMaximized(true);

		Scene scene = new Scene(dashboardView);
		scene.getStylesheets().add(getClass().getClassLoader().getResource(App.STYLE_CSS).toExternalForm());	    
		primaryStage.setScene(scene);   
		primaryStage.show();
		primaryStage.setOnCloseRequest(e->
			close()
		);
	}

	public void close() {
		System.exit(0);
	}

	@Override
	public Stage getStage() {
		return this.primaryStage;
	}	
	
	private void openLogin(String fxmlName, String title) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("fxml/" + fxmlName + ".fxml"));
			Parent rootHerramientas = (Parent)loader.load();
			primaryStage.close();

			primaryStage.setMaximized(false);
			primaryStage.setWidth(350);
			primaryStage.setHeight(180);
			primaryStage.centerOnScreen();
			primaryStage.resizableProperty().setValue(Boolean.FALSE);
			primaryStage.setTitle(title);
		    Scene scene = new Scene(rootHerramientas);
		    scene.getStylesheets().add(getClass().getClassLoader().getResource(App.STYLE_CSS).toExternalForm());
		    primaryStage.setScene(scene);
			primaryStage.setOnCloseRequest(e->
				close()
			);
		    
		    LoginController controller = (LoginController)loader.getController();			
		    controller.setMainActions(this);
		    primaryStage.show();
		} catch (IOException e) {
			e.printStackTrace();
			logger.error(e.getCause());
			logger.error(e.getMessage());
			logger.error(e);						
		}
	}
}