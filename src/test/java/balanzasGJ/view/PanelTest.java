package balanzasGJ.view;

import java.io.IOException;

import com.balanzasgj.app.App;
import com.balanzasgj.app.view.PesarEntradaSalidaController;

import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class PanelTest {
	
	public VBox getPanel(String fxmlName, String title) {
		final VBox screen = new VBox();
		screen.setPadding(new Insets(5, 25, 5, 25));
		final Label lblTitle = new Label();
		lblTitle.setId("lblTitle");
		
		HBox header = new HBox();
		lblTitle.setText(title);
		header.getChildren().add(lblTitle);
		
		
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("fxml/" + fxmlName + ".fxml"));
			Parent rootHerramientas = (Parent) loader.load();
			Stage stage = new Stage();
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.resizableProperty().setValue(Boolean.FALSE);
			stage.setTitle(title);
						
			screen.getChildren().add(header);

			if (loader.getController() instanceof PesarEntradaSalidaController) {
				Scene scene = new Scene(rootHerramientas);
				scene.getStylesheets().add(getClass().getClassLoader().getResource(App.STYLE_CSS).toExternalForm());
				stage.setScene(scene);

				PesarEntradaSalidaController controller = (PesarEntradaSalidaController) loader.getController();
				stage.setOnCloseRequest(E -> {
					controller.closeSocket();
				});
				//controller.setStage(stage);
			}
			screen.getChildren().add(rootHerramientas);
			//this.stage = stage;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return screen;
	}

}
