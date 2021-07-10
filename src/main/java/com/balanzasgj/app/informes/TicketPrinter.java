package com.balanzasgj.app.informes;

import ar.com.fdvs.dj.domain.DynamicReport;
import com.balanzasgj.app.model.GlobalParameter;
import com.balanzasgj.app.model.Tare;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.design.*;

import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

public class TicketPrinter extends ReportBase{
    private List<Tare> taras;
    public TicketPrinter(PAGE_FORMAT page, Map<String, Object> params, List<Tare> taras) {
        super(page, params);
        this.taras =taras;
    }

    public void build() throws Exception {
        logger.info("Print: " + TicketPrinter.class.getName());
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        jasperDesign.setName("TicketEtiquetadora");
        int margin = 10;
        jasperDesign.setLeftMargin(margin);
        jasperDesign.setRightMargin(1);
        jasperDesign.setTopMargin(margin);
        jasperDesign.setBottomMargin(1);
        jasperDesign.setColumnWidth(page.with- (margin * 2));

        int valueWidth = 70;
        JRDesignStyle titleStyle= style("titleStyle", "Arial", 8);
        titleStyle.setBold(true);
        jasperDesign.addStyle(titleStyle);
        JRDesignStyle textStyle= style("textStyle", "Arial", 8);
        setTextStyle(textStyle);
        jasperDesign.addStyle(textStyle);
        Tare t = taras.get(0);

        JRDesignBand band = new JRDesignBand();
        band.setHeight((new Double(page.height - (page.height * 0.1))).intValue());

        int y = 0;
        JRDesignStaticText textStatic = null;
        band.addElement(staticText(Unit.px, titleStyle, 0, y, "Comprobante de Pesaje"));

        y =newLine(y);
        band.addElement(staticText(Unit.px, 0,y,  parameter.get(GlobalParameter.P_EMPRESA_NOMBRE_BAL)));
        y =newLine(y);
        String datosCliente = parameter.get(GlobalParameter.P_EMPRESA_DIR_BAL) + " - " +
                parameter.get(GlobalParameter.P_EMPRESA_LOC_BAL) + " - " +
                parameter.get(GlobalParameter.P_EMPRESA_PROV_BAL);
        band.addElement(staticText(Unit.px, 0,y, datosCliente));
        y =newLine(y);
        textStatic = staticText(Unit.px, 0,y, "Tel:" + parameter.get(GlobalParameter.P_EMPRESA_TEL_BAL));
        textStatic.setWidth(valueWidth);
        band.addElement(textStatic);


        y =newLine(y);
        y =newLine(y);
        band.addElement(staticText(Unit.px, 0,y, "Entrada: "));
        band.addElement(staticText(Unit.px, 40,y, dateFormat.format(t.getFechaEntrada())));

        band.addElement(staticText(Unit.px, 120,y, "TICKET: "));
        textStatic = staticText(Unit.px, 160,y, t.getTransaccion());
        textStatic.setWidth(30);
        band.addElement(textStatic);

        y = newLine(y);
        band.addElement(staticText(Unit.px, 0,y, "Salida: "));
        if(t.getFechaSalida() != null)
            band.addElement(staticText(Unit.px, 40,y, dateFormat.format(t.getFechaSalida())));

        y= newLine(y);
        band.addElement(staticText(Unit.px, 0,y, "Producto:"));
        if(t.getProducto() != null)
            band.addElement(staticText(Unit.px, 70,y, t.getProducto().getNombre()));

        y= newLine(y);
        band.addElement(staticText(Unit.px, 0,y, "Cliente:"));
        if(t.getCliente() != null)
            band.addElement(staticText(Unit.px, 70,y, t.getCliente().getNombre()));

        y= newLine(y);
        band.addElement(staticText(Unit.px, 0,y, "Transporte:"));
        if(t.getTransporte() != null)
            band.addElement(staticText(Unit.px, 70,y, t.getTransporte().getNombre()));

        y= newLine(y);
        band.addElement(staticText(Unit.px, 0,y, "Procedencia:"));
        if(t.getProcedencias() != null)
        band.addElement(staticText(Unit.px, 70,y, t.getProcedencias().getNombre()));

        y= newLine(y);
        band.addElement(staticText(Unit.px, 0,y, "PESO ENTRADA: "));
        band.addElement(staticText(Unit.px, 70,y, t.getPesoEntrada()));

        y= newLine(y);
        band.addElement(staticText(Unit.px, 0,y, "PESO SALIDA: "));
        band.addElement(staticText(Unit.px, 70,y, t.getPesoSalida()));

        y= newLine(y);
        band.addElement(staticText(Unit.px, 0,y, "PESO NETO: "));
        band.addElement(staticText(Unit.px, 70,y, t.getPesoNeto()));

        int footerWidth= 170;
        y = newLine(y);
        y =newLine(y);
        textStatic = staticText(Unit.px, titleStyle, 0, y, parameter.get(GlobalParameter.P_EMPRESA_NOMBRE));
        textStatic.setWidth(footerWidth);
        band.addElement(textStatic);
        y = newLine(y);
        String company = "" +
                parameter.get(GlobalParameter.P_EMPRESA_DIR) + " - " +
                parameter.get(GlobalParameter.P_EMPRESA_LOC) + " - " +
                parameter.get(GlobalParameter.P_EMPRESA_PROV)
                ;
        textStatic = staticText(Unit.px, 0, y, company);
        textStatic.setWidth(footerWidth);
        band.addElement(textStatic);
        y = newLine(y);

        textStatic = staticText(Unit.px, 0,y, "Tel:" + parameter.get(GlobalParameter.P_EMPRESA_TEL));
        textStatic.setWidth(footerWidth);
        band.addElement(textStatic);
        ((JRDesignSection) jasperDesign.getDetailSection()).addBand(band);
    }

    @Override
    public DynamicReport buildReport() throws Exception {
        return null;
    }

    @Override
    public JRDataSource getDataSource() {
        return new JREmptyDataSource();
    }

    private int newLine(int y){
        return y + 10;
    }
}
