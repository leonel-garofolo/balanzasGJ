package balanzasGJ.view.settings;

import com.balanzasgj.app.App;
import com.balanzasgj.app.view.settings.CRUDEntityView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class CRUDEntityViewTest extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        CRUDEntityView crudEntityView = new CRUDEntityView();

        Scene scene = new Scene(crudEntityView,750,500, Color.web("#666970"));
        scene.getStylesheets().add(getClass().getClassLoader().getResource(App.STYLE_CSS).toExternalForm());
        stage.setScene(scene);
        stage.show();
    }
}
