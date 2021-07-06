package balanzasGJ.view;

import com.balanzasgj.app.App;
import com.balanzasgj.app.model.User;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class SettingsViewTest extends Application {
	
	public static void main(String[] args) {
		launch(args);		
	}

	@Override
	public void start(Stage stage) throws Exception {		
		User.getPerfilLogeado();
		User.setPerfilLogeado("admin");
		User.setUsuarioLogeado("admin");
		
		Scene scene = new Scene(new PanelTest().getPanel("ConfiguracionesView", "Configuraciones"),750,500, Color.web("#666970"));
		scene.getStylesheets().add(getClass().getClassLoader().getResource(App.STYLE_CSS).toExternalForm());		
		stage.setScene(scene);  		
		stage.show();
	}
}
