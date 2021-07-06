package com.balanzasgj.app.view.dashboard;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.SQLException;

import javax.imageio.ImageIO;

import org.apache.log4j.Logger;

import com.balanzasgj.app.model.GlobalParameter;
import com.balanzasgj.app.services.GlobalParameterService;
import com.balanzasgj.app.view.DashboardView.PANEL;

import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class MainCenterView extends VBox {
	final static Logger logger = Logger.getLogger(MainCenterView.class);	
	
	private GlobalParameterService paramConfigurationService;
	private DashboardActions actions;
	
	public MainCenterView(DashboardActions actions) {
		this.actions = actions;
		initServices();
		initialize();
	}
	
	private void initServices() {
		this.paramConfigurationService = new GlobalParameterService();
	}
	
	private void initialize() {			
		final HBox buttons = new HBox();
		final Button tara = new Button("Tomar Pesajes");	
		tara.setContentDisplay(ContentDisplay.TOP);
		tara.setGraphic(new ImageView(new Image("images/baseline_compare_arrows_black_48dp.png")));
		tara.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				actions.showCenter(PANEL.TARA);				
			}
		});
		
		final Button settings = new Button("Configuraciones");
		settings.setContentDisplay(ContentDisplay.TOP);
		settings.setGraphic(new ImageView(new Image("images/baseline_settings_black_48dp.png")));
		settings.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				actions.showCenter(PANEL.SETTINGS);				
			}
		});
		
		final Button report = new Button("Informes");
		report.setContentDisplay(ContentDisplay.TOP);
		report.setGraphic(new ImageView(new Image("images/baseline_insert_chart_outlined_black_48dp.png")));
		report.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				actions.showCenter(PANEL.REPORT);				
			}
		});
		
		final Button systems = new Button("Sistema");
		systems.setContentDisplay(ContentDisplay.TOP);
		systems.setGraphic(new ImageView(new Image("images/baseline_security_black_48dp.png")));
		systems.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				actions.showCenter(PANEL.SYSTEMS);				
			}
		});
		
		final Button closeSession = new Button("Cerrar Sesión");
		closeSession.setContentDisplay(ContentDisplay.TOP);
		closeSession.setGraphic(new ImageView(new Image("images/baseline_vpn_key_black_48dp.png")));
		closeSession.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				actions.showCenter(PANEL.CLOSE_SESSION);				
			}
		});
		
		
		buttons.setSpacing(30);		
		buttons.setAlignment(Pos.TOP_CENTER);
		buttons.getChildren().add(tara);
		buttons.getChildren().add(settings);
		buttons.getChildren().add(report);
		buttons.getChildren().add(systems);
		buttons.getChildren().add(closeSession);
				
		final VBox showCompanyData = new VBox();
		showCompanyData.setAlignment(Pos.BOTTOM_CENTER);
		final Label lblEmpresa = new Label(paramConfigurationService.get(GlobalParameter.P_EMPRESA_NOMBRE));
		lblEmpresa.setFont(new Font("Arial", 30));
				
		showCompanyData.getChildren().add(lblEmpresa);
		showCompanyData.getChildren().add(buildImageCompany());
		this.getChildren().add(buttons);
		this.getChildren().add(showCompanyData);
		this.setSpacing(300);
	}
	
	private ImageView buildImageCompany() {
		final ImageView imgEmpresa = new ImageView();
		Blob imageBlob= this.paramConfigurationService.getBlob(GlobalParameter.P_EMPRESA_IMG);
		if(imageBlob != null) {
			//convert blob to byte[]
            InputStream input;
			try {
				input = imageBlob.getBinaryStream();
				byte[] img = new byte[new Long(imageBlob.length()).intValue()];
	            input.read(img);

	            //convert byte[] to image
	            InputStream inputStream = new ByteArrayInputStream(img);
	            BufferedImage buffer = ImageIO.read(inputStream);
	            Image image = SwingFXUtils.toFXImage(buffer, null);
	            imgEmpresa.setImage(image);
			} catch (SQLException e) {
				logger.error(e);
			} catch (IOException e) {
				logger.error(e);
				imgEmpresa.setImage(null);
			} catch(NullPointerException e) {
				logger.error(e);
				imgEmpresa.setImage(null);
			}
		}
		return imgEmpresa;
	}
}
