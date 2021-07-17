package com.balanzasgj.app.view.settings;

import com.balanzasgj.app.model.Entity;
import com.balanzasgj.app.services.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.javafx.form.FormBuilder;
import org.javafx.form.FormView;
import org.javafx.form.model.FormField;
import org.javafx.form.model.FormFieldType;
import org.javafx.utils.JavaFxUtilities;

import java.util.ArrayList;
import java.util.List;

public class CRUDEntityView extends FormView {

    private boolean modoEdit;

    private static enum entity{
        CLIENTES("CLIENTES"),
        PROCEDENCIAS("PROCEDENCIAS"),
        PRODUCTOS("PRODUCTOS"),
        TRANSPORTES("TRANSPORTES"),
        IMPORTACIONES("IMPORTACIONES"),
        ATA_TRANSPORTISSTA("ATA_TRANSPORTISSTA"),
        PATENTES("PATENTES")
        ;

        private String label;
        entity(String label){
            this.label = label;
        }
    }

    private HBox divider;
    private TableView<Entity> tblEntidades;
    private TableColumn<Entity, Long> colCodigo;
    private TableColumn<Entity, String> colNombre;
    private Button btnNuevoEntidad;
    private Button btnEliminarEntidad;
    private FormBuilder formBuilder;


    private ClientService clientService;
    private OriginService originService;
    private ProductService productService;
    private TransportService transportService;
    private IndicatorService indicatorService;
    private ComunicationService comunicationService;
    private ImportAndExportService importAndExportService;
    private AtaService ataService;

    public CRUDEntityView(){
        super("Entidades");
        initialize();
    }

    private void initialize(){
        this.hboxBottom.setVisible(false);
        divider= new HBox();
        tblEntidades = new TableView<>();
        colCodigo = new TableColumn<>();
        colCodigo.setPrefWidth(120);
        colCodigo.setVisible(false);
        colCodigo.setText("Codigo");
        colNombre= new TableColumn<>();
        colNombre.setPrefWidth(200);
        colNombre.setText("Nombre");
        VBox dividerLeft= new VBox();
        dividerLeft.getChildren().add(tblEntidades);

        HBox buttons = new HBox();
        buttons.setPadding(new Insets(5, 0, 0 , 0));
        buttons.setAlignment(Pos.CENTER);
        btnNuevoEntidad = new Button("Nuevo");
        ImageView img = JavaFxUtilities.getImagePNG("anadir");
        img.setFitWidth(18);
        img.setFitHeight(21);
        btnNuevoEntidad.setGraphic(img);
        btnNuevoEntidad.setOnAction(event -> cleanFormEntidades());

        btnEliminarEntidad = new Button("Eliminar");
        img = JavaFxUtilities.getImagePNG("borrar");
        img.setFitWidth(18);
        img.setFitHeight(21);
        btnEliminarEntidad.setGraphic(img);
        buttons.getChildren().add(btnNuevoEntidad);
        buttons.getChildren().add(btnEliminarEntidad);
        dividerLeft.getChildren().add(buttons);

        tblEntidades.getColumns().add(colCodigo);
        tblEntidades.getColumns().add(colNombre);
        divider.getChildren().add(dividerLeft);
        divider.getChildren().add(buildFormEditClient());

        screen.getChildren().add(divider);
    }

    private Node buildFormEditClient(){
        List<FormField> fields = new ArrayList<>();
        fields.add(new FormField("txtNombre", "Nombre", FormFieldType.STRING, null, true));
        fields.add(new FormField("txtCuit", "Cuit", FormFieldType.STRING, null, true));
        final FormBuilder builder = new FormBuilder("", fields);
        return builder.build();
    }


    private void cleanFormEntidades() {
        this.modoEdit = false;
        tblEntidades.getItems().clear();
        /*
        formBuilder.g
        txtEntidadNombre.setText("");
        txtEntidadCuitAlias.setText("");
        txtEntidadUltMov.setText("");
        txtEntidadAcumulado.setText("");
         */
    }
}
