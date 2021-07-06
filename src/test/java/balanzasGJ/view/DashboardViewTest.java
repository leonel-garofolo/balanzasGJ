package balanzasGJ.view;

import com.balanzasgj.app.App;
import com.balanzasgj.app.model.User;
import com.balanzasgj.app.view.DashboardView;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class DashboardViewTest extends Application {
	
	public static void main(String[] args) {
		launch(args);		
	}

	@Override
	public void start(Stage stage) throws Exception {
		DashboardView dashboardView = new DashboardView(null);
		
		User.getPerfilLogeado();
		User.setPerfilLogeado("admin");
		User.setUsuarioLogeado("admin");
		stage.setTitle(App.APP_NAME);
		stage.setMaximized(true);
		stage.resizableProperty().setValue(Boolean.FALSE);
		
		Scene scene = new Scene(dashboardView);
		scene.getStylesheets().add(getClass().getClassLoader().getResource(App.STYLE_CSS).toExternalForm());	    
		stage.setScene(scene);   
		stage.show();
	}
}
