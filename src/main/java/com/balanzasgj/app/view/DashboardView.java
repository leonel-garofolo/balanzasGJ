package com.balanzasgj.app.view;

import java.io.IOException;

import javafx.event.EventHandler;
import javafx.stage.WindowEvent;
import org.apache.log4j.Logger;

import com.balanzasgj.app.App;
import com.balanzasgj.app.model.GlobalParameter;
import com.balanzasgj.app.services.GlobalParameterService;
import com.balanzasgj.app.utils.Message;
import com.balanzasgj.app.view.dashboard.DashboardActions;
import com.balanzasgj.app.view.dashboard.MainCenterView;
import com.balanzasgj.app.view.system.SystemView;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class DashboardView extends BorderPane implements DashboardActions {
	final static Logger logger = Logger.getLogger(DashboardView.class);
	private GlobalParameterService paramConfigurationService;
	private HBox msjSuccess;
	private HBox msjError;
	private Label lblTitle;

	public static enum PANEL {
		MAIN, TARA, SETTINGS, REPORT, SYSTEMS, CLOSE_SESSION, CLOSE
	};

	private FXMLLoader loader;
	private Stage stage;
	private final MainActions mainActions;

	public DashboardView(MainActions mainActions) {
		this.mainActions = mainActions;
		initializeServices();
		initialize();
	}

	private void initializeServices() {
		this.paramConfigurationService = new GlobalParameterService();
	}


	private void initialize() {
		this.stage = mainActions.getStage();
		showMain();
		buildMessage();
	}

	

	@Override
	public void showCenter(PANEL panel) {		
		switch (panel) {
		case TARA:
			String transact = paramConfigurationService.get(GlobalParameter.P_EMPRESA_TRANSACCION);
			if (transact.isEmpty()) {
				Message.error(
						"Para ingresar a la pantalla de Pesaje es requerido el campo 'Transaccion' en la pantalla de Sistemas.");
			} else {								
				setCenter(this.getPanel("PesarEntradaSalidaView", "Tara"));
			}
			break;
		case REPORT:			
			setCenter(this.getPanel("InformesView", "Informes"));
			break;
		case SETTINGS:			
			setCenter(this.getPanel("ConfiguracionesView", "Configuraciones"));
			break;
		case SYSTEMS:			
			SystemView setting = new SystemView(this);
			setCenter(setting);
			break;
		case CLOSE_SESSION:
			mainActions.showLogin();
			break;
		case CLOSE:
			System.exit(0);
			break;
		default:
			break;
		}
		setBottom(null);
	}	
	
	private void buildMessage() {
		msjSuccess = new HBox();
		msjSuccess.setId("msjSuccess");
		msjError = new HBox();
		msjError.setId("msjError");			
	}

	private VBox getPanel(String fxmlName, String title) {
		final VBox screen = new VBox();
		screen.setPadding(new Insets(5, 25, 5, 25));
		lblTitle = new Label();
		lblTitle.setId("lblTitle");
		
		HBox header = new HBox();
		lblTitle.setText(title);
		header.getChildren().add(lblTitle);
		
		
		try {
			loader = new FXMLLoader(getClass().getClassLoader().getResource("fxml/" + fxmlName + ".fxml"));
			Parent rootHerramientas = (Parent) loader.load();
			Stage stagePane = new Stage();
			stagePane.initModality(Modality.APPLICATION_MODAL);
			stagePane.resizableProperty().setValue(Boolean.FALSE);
			stagePane.setTitle(title);
			
			addStackPane(header, stagePane);
			screen.getChildren().add(header);

			if (loader.getController() instanceof PesarEntradaSalidaController) {
				Scene scene = new Scene(rootHerramientas);
				scene.getStylesheets().add(getClass().getClassLoader().getResource(App.STYLE_CSS).toExternalForm());
				stagePane.setScene(scene);

				PesarEntradaSalidaController controller = (PesarEntradaSalidaController) loader.getController();
				((IHome)controller).setDashboard(this);
			}
			screen.getChildren().add(rootHerramientas);
		} catch (IOException e) {
			e.printStackTrace();
			logger.error(e.getCause());
			logger.error(e.getMessage());
			logger.error(e);
		}
		return screen;
	}

	private void addStackPane(HBox hb, Stage stagePane) {
		StackPane stack = new StackPane();
		Rectangle helpIcon = new Rectangle(30.0, 25.0);
		helpIcon.setArcHeight(3.5);
		helpIcon.setArcWidth(3.5);

		Button close = new Button("X");
		StackPane.setMargin(close, new Insets(0, 10, 0, 0)); // Center "?"
		close.setOnAction(e -> {
			if (loader.getController() instanceof PesarEntradaSalidaController){
				PesarEntradaSalidaController controller = (PesarEntradaSalidaController) loader.getController();
				controller.closeSocket();
			}
			stagePane.close();
			showMain();
		});
		// helpText.setFont(Font.font("Verdana", FontWeight.BOLD, 18));
		stack.getChildren().add(close);
		stack.setAlignment(Pos.CENTER_RIGHT); // Right-justify nodes in stack
		

		hb.getChildren().add(stack); // Add to HBox from Example 1-2
		HBox.setHgrow(stack, Priority.ALWAYS); // Give stack any extra space
	}

	@Override
	public void showMain() {
		setCenter(new MainCenterView(this));		
	}

	static int SECOND = 100;
	
	@Override
	public void showSuccess(String msj) {
		new Thread() {
		    public void run() {
		        //Do some stuff in another thread
		        Platform.runLater(new Runnable() {
		            public void run() {
		            	msjSuccess.getChildren().add(new Label(msj));
		    			setBottom(msjSuccess);		    					    		
		            }
		        });
		    }
		}.start();
	}

	@Override
	public void showError(String msj) {
		msjError.getChildren().add(new Label(msj));
		setBottom(msjError);
	}

	@Override
	public void setTitle(String title) {
		this.lblTitle.setText(title);
	}
}
