package com.balanzasgj.app.informes.csv;

import com.balanzasgj.app.model.Entity;
import com.balanzasgj.app.model.Tare;
import com.balanzasgj.app.utils.Utils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ExportTaraCsv extends GenerateCsv{
    private final String[] headers = {
        "Transacción",
        "FechaEntrada",
        "FechaSalida",
        "Balanza",
        "Producto",
        "Cliente",
        "Transporte",
        "Procedencia",
        "Imp. / Exp.",
        "Modalidad",
        "Comp. Num. 1",
        "Destino",
        "Conductor",
        "Tipo Doc.",
        "Num. Doc.",
        "Patente",
        "Pat. Acoplado",
        "Observación",
        "Observación Aduana",
        "Contenedor Num.",
        "Peso Entrada",
        "Peso Salida",
        "Peso Neto",
        "Ata",
        "Contenedor",
        "Manifiesto",
        "Mercaderia",
        "Nacionalidad"};
    private List<Tare> taras;
    public ExportTaraCsv(){
        super();
    }

    public static ExportTaraCsv newBuild(){
        return new ExportTaraCsv();
    }

    public ExportTaraCsv setTaras(List<Tare> taras) {
        this.taras = taras;
        setHeaders(headers);

        this.rowsData= new ArrayList<>();
        for (Tare t: taras)
            rowsData.add(buildData(t));
        return this;
    }

    public ExportTaraCsv setPath(String path) {
        this.path = path;
        return this;
    }

    public ExportTaraCsv setNameFile(String nameFile) {
        this.nameFile = nameFile;
        return this;
    }

    @Override
    public ExportTaraCsv build() {
        if(this.taras == null){
            throw new NullPointerException("Tara is require to build");
        }
        super.build();
        return this;
    }

    private List<String> buildData(Tare t){
        final List<String> row = new ArrayList<>();
        addRow(row, t.getTransaccion());
        addRow(row, t.getFechaEntrada());
        addRow(row, t.getFechaSalida());
        addRow(row, t.getBalanza());
        addRow(row, t.getProducto());
        addRow(row, t.getCliente());
        addRow(row, t.getTransporte());
        addRow(row, t.getProcedencias());
        addRow(row, t.getImpExp());
        addRow(row, t.getModalidad());

        addRow(row, t.getComprobanteNun1());
        addRow(row, t.getDestino());
        addRow(row, t.getConductor());
        addRow(row, t.getTipoDoc());
        addRow(row, t.getNumDoc());
        addRow(row, t.getPatente());
        addRow(row, t.getPatenteAceptado());
        addRow(row, t.getObservacion());
        addRow(row, t.getObservacionAduana());
        addRow(row, t.getContenedorNum());
        addRow(row, t.getPesoEntrada());
        addRow(row, t.getPesoSalida());
        addRow(row, t.getPesoNeto());
        addRow(row, t.getAta());
        addRow(row, t.getContenedor());
        addRow(row, t.getManifiesto());
        addRow(row, t.getMercaderia());
        addRow(row, t.getNacionalidad());
        return row;
    }

    private void addRow(List<String> row, String value){
        if(value == null)
            row.add("");
        else
            row.add(value);
    }

    private void addRow(List<String> row, Entity value){
        if(value == null)
            row.add("");
        else
            row.add(value.getNombre());
    }

    private void addRow(List<String> row, BigDecimal value){
        if(value == null)
            row.add("");
        else
            row.add(value.toString());
    }

    private void addRow(List<String> row, Date value){
        if(value == null)
            row.add("");
        else
            row.add(Utils.convertDateToString(value, Utils.DATE_HOUR_FORMAT));
    }
}

