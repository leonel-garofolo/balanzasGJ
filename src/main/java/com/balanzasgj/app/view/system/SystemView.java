package com.balanzasgj.app.view.system;

import com.balanzasgj.app.db.ProcessDB;
import com.balanzasgj.app.db.UpdateDB;
import com.balanzasgj.app.model.GlobalParameter;
import com.balanzasgj.app.model.GlobalParameter.TYPE_TICKET;
import com.balanzasgj.app.services.GlobalParameterService;
import com.balanzasgj.app.utils.Message;
import com.balanzasgj.app.view.DashboardView;
import com.balanzasgj.app.view.IView;
import com.balanzasgj.app.view.MainActions;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.apache.log4j.Logger;
import org.javafx.form.FormBuilder;
import org.javafx.form.model.FormField;
import org.javafx.form.model.FormFieldList;
import org.javafx.form.model.FormFieldType;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SystemView extends VBox implements IView {
	final static Logger logger = Logger.getLogger(SystemView.class);

	private final DashboardView dashboardView;
	private GlobalParameterService paramConfigurationService;
	private static final int PADDING= 25;
	private List<FormField> fields;
	private FormBuilder formBuilder;

	private MainActions mainActions;
	private Stage stage = new Stage();
	
	public SystemView(DashboardView dashboardView) {
		this.dashboardView = dashboardView;
		this.stage = new Stage();
		initServices();
		initialize();
	}
	
	private void initServices() {
		this.paramConfigurationService = new GlobalParameterService();
	}
	
	private void initialize() {
		this.fields = new ArrayList<FormField>();
		String param = "";
		
		fields.add(new FormField(null, "Encabezado de Tickets", FormFieldType.SUBTITLE2, null));
		param = GlobalParameter.P_EMPRESA_NOMBRE_BAL;
		fields.add(new FormField(param, "Nombre", FormFieldType.STRING, paramConfigurationService.get(param), true));
		param = GlobalParameter.P_EMPRESA_DENOMINACION_BAL;
		fields.add(new FormField(param, "Denominación", FormFieldType.STRING, paramConfigurationService.get(param), true));
		fields.add(new FormField(FormFieldType.NEW_ROW));
		param = GlobalParameter.P_EMPRESA_DIR_BAL;
		fields.add(new FormField(param, "Dirección", FormFieldType.STRING, paramConfigurationService.get(param)));
		param = GlobalParameter.P_EMPRESA_LOC_BAL;
		fields.add(new FormField(param, "Localidad", FormFieldType.STRING, paramConfigurationService.get(param)));
		param = GlobalParameter.P_EMPRESA_PROV_BAL;
		fields.add(new FormField(param, "Provincia", FormFieldType.STRING, paramConfigurationService.get(param)));
		param = GlobalParameter.P_EMPRESA_TEL_BAL;
		fields.add(new FormField(param, "Telefono", FormFieldType.STRING, paramConfigurationService.get(param)));
		param = GlobalParameter.P_EMPRESA_EMAIL_BAL;
		fields.add(new FormField(param, "Email", FormFieldType.STRING, paramConfigurationService.get(param)));
		
		fields.add(new FormField(null, "Numero base de Transacción", FormFieldType.SUBTITLE2, null));
		param = GlobalParameter.P_EMPRESA_TRANSACCION;
		fields.add(new FormField(param, "Transacción", FormFieldType.NUMBER, paramConfigurationService.get(param)));
		param = GlobalParameter.P_EMPRESA_ING_MANUAL;
		fields.add(new FormField(param, "Clave Ingreso Manual", FormFieldType.PASSWORD, paramConfigurationService.get(param)));
		param = GlobalParameter.P_NUM_BALANZAS;
		fields.add(new FormField(param, "Número de Balanzas", FormFieldType.NUMBER, paramConfigurationService.get(param)));				
		fields.add(new FormField(FormFieldType.NEW_ROW));
		param = GlobalParameter.P_TICKET_ETIQUETADORA;
		//fields.add(new FormField(param, "Ticket con Formato de Etiquetadora", FormFieldType.BOOLEAN, paramConfigurationService.get(param)));
		List<Object> listTicketType = new ArrayList<Object>();
		for(TYPE_TICKET type: GlobalParameter.TYPE_TICKET.values()) {
			listTicketType.add(type.label);
		}	
		String value = paramConfigurationService.get(param);
		if(value != null) {
			if(value.equals("true") || value.equals("false"))
				if(value.equals("true"))
					value = TYPE_TICKET.FORMATO_ETIQUETADORA.label;
				else
					value = TYPE_TICKET.NORMAL.label;
		}
		fields.add(new FormFieldList(param, "Formato Ticket", listTicketType, value));
		
		fields.add(new FormField(null, "Validaciones - Pantalla de Tara", FormFieldType.SUBTITLE1, null));
		fields.add(new FormField(null, "Campos requeridos en la Entrada", FormFieldType.SUBTITLE2, null));
		loadChecksValidation(GlobalParameter.P_VALIDACION_ENTRADA);
		
		fields.add(new FormField(null, "Campos requeridos en la Salida", FormFieldType.SUBTITLE2, null));
		loadChecksValidation(GlobalParameter.P_VALIDACION_SALIDA);
		
		fields.add(new FormField(null, "Exportar CSV", FormFieldType.SUBTITLE1, null));		
		param = GlobalParameter.P_CSV_EXPORT_PATH;
		fields.add(new FormField(param, "Ubicación a exportar", FormFieldType.DIRECTORY, paramConfigurationService.get(param)));
		fields.add(new FormField(FormFieldType.NEW_ROW));
		param = GlobalParameter.P_USER_WINDOWS;
		fields.add(new FormField(param, "Usuario de Windows", FormFieldType.STRING, paramConfigurationService.get(param)));
		param = GlobalParameter.P_PASS_WINDOWS;
		fields.add(new FormField(param, "Clave de Windows", FormFieldType.PASSWORD, paramConfigurationService.get(param)));
		
		fields.add(new FormField(null, "Tramiento de Base de datos", FormFieldType.SUBTITLE1, null));
		param = GlobalParameter.P_EMPRESA_AUTOMATICO;
		fields.add(new FormField(param, "Backup Automatico", FormFieldType.STRING, paramConfigurationService.get(param), false, "HH:mm"));
		fields.add(new FormField(FormFieldType.NEW_ROW));
		param = GlobalParameter.P_EMPRESA_BACKUP;
		fields.add(new FormField(param, "Ubicación", FormFieldType.DIRECTORY, paramConfigurationService.get(param)));
		FormField field =new FormField("btnBackupUbicacion", "Generar", FormFieldType.BUTTON, null);
		field.setAction(event -> {
			save();
			final String pathBackup = paramConfigurationService.get(GlobalParameter.P_EMPRESA_BACKUP);
			if (!pathBackup.isEmpty()) {
				try {
					ProcessDB.generarBackup(pathBackup);
					Message.info("Backup Finalizado.");
				} catch (IOException e) {
					e.printStackTrace();
					Message.error("Se produjo un error al generar el backup.");
				}
			}
		});
		fields.add(field);
		fields.add(new FormField(FormFieldType.NEW_ROW));
		param = GlobalParameter.P_EMPRESA_RESTORE;
		fields.add(new FormField(param, "Restaurar", FormFieldType.FILE, paramConfigurationService.get(param)));
		field = new FormField("btnBackupRestaurar", "Restaurar", FormFieldType.BUTTON, null);
		field.setAction(event -> {
			final String pathRestore = paramConfigurationService.get(GlobalParameter.P_EMPRESA_RESTORE);
			if(!pathRestore.isEmpty()) {
				try {
					UpdateDB db = new UpdateDB();
					db.dropDatabase();
					//Utils.restaurarBackup(txtPathRst.getText());
					boolean result = ProcessDB.restoreDB(pathRestore);
					logger.info("Corriendo actualizacion RUN: " + result);
					//db.run();
					Message.info("Restauración Finalizada. El sistema se cerrara al presionar OK.");
					System.exit(0);
				} catch (Exception e) {
					e.printStackTrace();
					Message.error("Se produjo un error al restaurar el backup.");
				}
			}
		});
		fields.add(field);
		fields.add(new FormField(FormFieldType.NEW_ROW));
		FormField btnCleanDatabase = new FormField(null, "Limpiar DB", FormFieldType.BUTTON, null);
		btnCleanDatabase.setAction(e->{
			boolean choice = Message.optionYesNo("Quieres limpiar los datos de la base de datos?");
			if(choice){
				ProcessDB db = new ProcessDB();
				boolean result = db.cleanDB();
				if(result) {
					formBuilder.getField(GlobalParameter.P_EMPRESA_TRANSACCION).setValue("0");
					formBuilder.refresh();
					Message.info("La base de datos se limpio correctamente!");
				}
			}
		});
		fields.add(btnCleanDatabase);
		param = GlobalParameter.P_ACTIVAR_DEBUG;
		fields.add(new FormField(param, "Activar Debug", FormFieldType.BOOLEAN, paramConfigurationService.get(param)));
		
		fields.add(new FormField(null, "Datos de la Empresa", FormFieldType.SUBTITLE1, null));
		param = GlobalParameter.P_EMPRESA_NOMBRE;
		fields.add(new FormField(param, "Nombre", FormFieldType.STRING, paramConfigurationService.get(param)));
		fields.add(new FormField(FormFieldType.NEW_ROW));
		param = GlobalParameter.P_EMPRESA_DIR;
		fields.add(new FormField(param, "Dirección", FormFieldType.STRING, paramConfigurationService.get(param)));
		param = GlobalParameter.P_EMPRESA_LOC;
		fields.add(new FormField(param, "Localidad", FormFieldType.STRING, paramConfigurationService.get(param)));
		param = GlobalParameter.P_EMPRESA_PROV;
		fields.add(new FormField(param, "Provincia", FormFieldType.STRING, paramConfigurationService.get(param)));
		param = GlobalParameter.P_EMPRESA_TEL;
		fields.add(new FormField(param, "Telefono", FormFieldType.STRING, paramConfigurationService.get(param)));
		fields.add(new FormField(FormFieldType.NEW_ROW));
		param = GlobalParameter.P_EMPRESA_EMAIL;
		fields.add(new FormField(param, "Email", FormFieldType.STRING, paramConfigurationService.get(param)));
		
		this.formBuilder = new FormBuilder("Configuraciones", fields);
		getChildren().add(formBuilder.build());
		
		final HBox hbox = new HBox();
		hbox.setId("BoxButton");
		hbox.setSpacing(10);	
		hbox.setPadding(new Insets(5, PADDING, 5, PADDING));
		final Button btnPrimary = new Button("Guardar");
		btnPrimary.setOnAction(v -> {
			save();
			stage.close();
			dashboardView.showMain();		
			dashboardView.showSuccess("Se guardo correctamente");
		});
		btnPrimary.setId("btnPrimary");
		final Button btnCancelar = new Button("Cancelar");
		btnCancelar.setId("btnSecundary");
		btnCancelar.setOnAction(v -> {
			stage.close();
			dashboardView.showMain();			
		});
		hbox.getChildren().add(btnPrimary);
		hbox.getChildren().add(btnCancelar);
		getChildren().add(hbox);				
	}
	
	private void loadChecksValidation(String validationType) {
		String validation = paramConfigurationService.get(validationType);
		showFiedValidation(validationType, validation, GlobalParameter.V_DOCUMENTO, "Documento");
		showFiedValidation(validationType, validation, GlobalParameter.V_CONDUCTOR, "Conductor");
		showFiedValidation(validationType, validation, GlobalParameter.V_NACIONALIDAD, "Nacionalidad");
		showFiedValidation(validationType, validation, GlobalParameter.V_CHASIS, "Acoplado");
		showFiedValidation(validationType, validation, GlobalParameter.V_FACTURA, "Comprobante");
		fields.add(new FormField(FormFieldType.NEW_ROW));
		showFiedValidation(validationType, validation, GlobalParameter.V_OBSERVACION, "Observacion");
		showFiedValidation(validationType, validation, GlobalParameter.V_PRODUCTO, "Producto");
		showFiedValidation(validationType, validation, GlobalParameter.V_TRANSPORTE, "Transporte");
		showFiedValidation(validationType, validation, GlobalParameter.V_CLIENTE, "Cliente");
		showFiedValidation(validationType, validation, GlobalParameter.V_PROCEDENCIA, "Procedencia");
		
	}
	
	private void showFiedValidation(String name, String validation, int checkType, String label) {
		if(validation.contains(String.valueOf(checkType))) {
			fields.add(new FormField(name + checkType, label, FormFieldType.BOOLEAN, true));
		} else
			fields.add(new FormField(name + checkType, label, FormFieldType.BOOLEAN, false));
	}
	
	private void save() {
		String checkEntradaList ="";
		String checkSalidaList ="";
		List<FormField> fieldsValue = formBuilder.getFields();
		for (FormField formField : fieldsValue) {
			if(formField.getId() == null || (formField.getId() != null && formBuilder.getValue(formField.getId()) == null)) {
				continue;
			}
			final Object value = formBuilder.getValue(formField.getId());
			
			System.out.println(formField.getId() + " || " + value);			
			if(formField.getId().contains(GlobalParameter.P_VALIDACION_ENTRADA) || formField.getId().contains(GlobalParameter.P_VALIDACION_SALIDA)) {
				if(Boolean.valueOf(value.toString())) {			
					if(formField.getId().contains(GlobalParameter.P_VALIDACION_ENTRADA)) 
						checkEntradaList += concatSplit(checkEntradaList, formField.getId().replaceAll(GlobalParameter.P_VALIDACION_ENTRADA, ""));
					else
						checkSalidaList += concatSplit(checkSalidaList, formField.getId().replaceAll(GlobalParameter.P_VALIDACION_SALIDA, ""));
				}
			} else
				if(formField.getValue() != null)
					paramConfigurationService.save(formField.getId(), value.toString());			
		}
		paramConfigurationService.save(GlobalParameter.P_VALIDACION_ENTRADA, checkEntradaList);
		paramConfigurationService.save(GlobalParameter.P_VALIDACION_SALIDA, checkSalidaList);				
	}
	
	private String concatSplit(String concat, String newValue){		
		return (concat.isEmpty()? "" : ",") + newValue;
	}

	@Override
	public void setMainActions(MainActions mainActions) {
		this.mainActions= mainActions;		
	}
}
