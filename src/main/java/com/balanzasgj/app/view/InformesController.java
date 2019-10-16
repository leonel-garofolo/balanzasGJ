package com.balanzasgj.app.view;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;

import com.balanzasgj.app.informes.TransaccionesInforme;
import com.balanzasgj.app.model.Clientes;
import com.balanzasgj.app.model.Procedencias;
import com.balanzasgj.app.model.Productos;
import com.balanzasgj.app.model.Taras;
import com.balanzasgj.app.model.Transportes;
import com.balanzasgj.app.persistence.TarasPersistence;
import com.balanzasgj.app.persistence.impl.jdbc.TarasPersistenceJdbc;
import com.balanzasgj.app.utils.Message;
import com.balanzasgj.app.utils.ShowJasper;
import com.balanzasgj.app.utils.Utils;
import com.balanzasgj.app.view.columns.ClientesTableCell;
import com.balanzasgj.app.view.columns.ProcedenciasTableCell;
import com.balanzasgj.app.view.columns.ProductosTableCell;
import com.balanzasgj.app.view.columns.TransportesTableCell;
import com.ibm.icu.util.Calendar;

import javafx.beans.value.ObservableValueBase;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

public class InformesController {

    @FXML
    private TableColumn<Taras, String> colTransaccion;
    @FXML
    private TableColumn<Taras, Date> colFecha;
    @FXML
    private TableColumn<Taras, String> colChasis;
    @FXML
    private TableColumn<Taras, String> colAcoplado;
    @FXML
    private TableColumn<Taras, BigDecimal> colEntrada;
    @FXML
    private TableColumn<Taras, BigDecimal> colSalida;
    @FXML
    private TableColumn<Taras, BigDecimal> colNeto;
    @FXML
    private TableColumn<Taras, String> colBalanza;
    @FXML
    private TableColumn<Taras, Productos> colProducto;
    @FXML
    private TableColumn<Taras, Clientes> colCliente;
    @FXML
    private TableColumn<Taras, Transportes> colTransporte;
    @FXML
    private TableColumn<Taras, Procedencias> colProcedencia;
    @FXML
    private TableView<Taras> tblPesajes;

    @FXML
    private Button btnBuscar;

    @FXML
    private Button btnLimpiar;


    @FXML
    private TextField txtFiltroBuscar;
    @FXML
    private ComboBox<String> cbxFiltroBuscar;

    @FXML
    private Button btnImprimir;
    @FXML
    private Button btnImprimirDetalle;
    
    @FXML
    private DatePicker timeFechaDesde;
    @FXML
    private DatePicker timeFechaHasta;
    
    private TarasPersistence tarasPersistence;

    @FXML
    private void handleTblEntidadesSelected(ActionEvent event) {
    }

    @FXML
    private void handleBuscar(ActionEvent event) {
        if(cbxFiltroBuscar.getSelectionModel().isEmpty() || txtFiltroBuscar.getText().isEmpty()){
            Message.error("Debe completar los datos de filtrado.");
        }else{
            tblPesajes.getItems().clear();
            tblPesajes.getItems().addAll(tarasPersistence.findByField(cbxFiltroBuscar.getSelectionModel().getSelectedItem(), txtFiltroBuscar.getText(), false));
        }
    }

    @FXML
    private void handleLimpiar(ActionEvent event) {
        refleshTableTaras();
        cleanSearch();
    }
    
    private void cleanSearch() {
    	cbxFiltroBuscar.getSelectionModel().clearSelection();
    	cbxFiltroBuscar.setValue("Patente");
        txtFiltroBuscar.setText("");
        
        Calendar cDesde = Calendar.getInstance();
        cDesde.set(Calendar.HOUR_OF_DAY, 0);
        cDesde.set(Calendar.MINUTE, 0);
    	timeFechaDesde.setValue(Utils.convertoToLocalDate(cDesde.getTime()));
    	Calendar cHasta = Calendar.getInstance();
        cDesde.set(Calendar.HOUR_OF_DAY, 23);
        cDesde.set(Calendar.MINUTE, 59);
    	timeFechaHasta.setValue(Utils.convertoToLocalDate(cHasta.getTime()));
    }

    @FXML
    private void handleImprimir(ActionEvent event) {
        TransaccionesInforme informe = new TransaccionesInforme(tblPesajes.getItems());
        try {
            informe.generateReport();
            informe.show();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    private void handleImprimirDetalle(ActionEvent event) {
    	HashMap<String, Object> params = new HashMap<>();
		try {
			ShowJasper.openBeanDataSource("transaccionesDetalles", params, new JRBeanCollectionDataSource(tblPesajes.getItems()) );
		} catch (JRException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    private void refleshTableTaras(){
        tblPesajes.getItems().clear();
        tblPesajes.getItems().addAll(tarasPersistence.findAll());
    }


    public void initialize() {    	
        initValues();
        cleanSearch();
        initPersistence();
        refleshTableTaras();
    }

    private void initPersistence() {
        this.tarasPersistence = new TarasPersistenceJdbc();

        colTransaccion.setCellValueFactory(new PropertyValueFactory<>("transaccion"));
        colFecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));
        colChasis.setCellValueFactory(new PropertyValueFactory<>("patente"));
        colAcoplado.setCellValueFactory(new PropertyValueFactory<>("contenedorNum"));
        colEntrada.setCellValueFactory(new PropertyValueFactory<>("pesoEntrada"));
        colSalida.setCellValueFactory(new PropertyValueFactory<>("pesoSalida"));

        colNeto.setCellValueFactory(cellData -> new ObservableValueBase<BigDecimal>() {

            @Override
            public BigDecimal getValue() {
                if(cellData.getValue().getPesoEntrada() != null
                        && cellData.getValue().getPesoSalida() != null){
                    return new BigDecimal(cellData.getValue().getPesoEntrada().doubleValue() - cellData.getValue().getPesoSalida().doubleValue());
                }
                return  new BigDecimal(0);

            }
        });
        colBalanza.setCellValueFactory(new PropertyValueFactory<>("balanza"));
        colCliente.setCellValueFactory(new PropertyValueFactory<>("cliente"));
        colCliente.setCellFactory(col -> new ClientesTableCell<>());

        colTransporte.setCellValueFactory(new PropertyValueFactory<>("transporte"));
        colTransporte.setCellFactory(col -> new TransportesTableCell<>());
        colProcedencia.setCellValueFactory(new PropertyValueFactory<>("procedencia"));
        colProcedencia.setCellFactory(col -> new ProcedenciasTableCell<>());
        colProducto.setCellValueFactory(new PropertyValueFactory<>("producto"));
        colProducto.setCellFactory(col -> new ProductosTableCell<>());
    }

    private void initValues() {
        cbxFiltroBuscar.getItems().addAll(new String[]{
                "Número de Transacción", "Patente", "Producto", "Cliente", "Transporte", "Procedencia"
        });        
    }


}
