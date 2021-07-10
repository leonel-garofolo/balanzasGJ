package com.balanzasgj.app.informes;

import ar.com.fdvs.dj.domain.DynamicReport;
import com.balanzasgj.app.model.GlobalParameter;
import com.balanzasgj.app.model.Tare;
import com.balanzasgj.app.model.User;
import net.sf.jasperreports.engine.JRBand;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.design.*;
import net.sf.jasperreports.engine.type.HorizontalTextAlignEnum;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

public class Ticket extends ReportBase{
    private final JRDesignStyle textStyle;
    private List<Tare> taras;
    public Ticket(PAGE_FORMAT page, Map<String, Object> params, List<Tare> taras) {
        super(page, params);
        this.taras =taras;
        this.textStyle= style("textStyle", "Arial", 10);
        setTextStyle(textStyle);
    }

    public void build() throws Exception {
        logger.info("Print: " + TicketPrinter.class.getName());
        jasperDesign.setName("Ticket");
        int margin = 14;
        jasperDesign.setLeftMargin(margin);
        jasperDesign.setRightMargin(margin);
        jasperDesign.setTopMargin(margin);
        jasperDesign.setBottomMargin(margin);

        jasperDesign.addStyle(textStyle);
        jasperDesign.setTitle(buildTitle());
        jasperDesign.setPageHeader(buildHeader());
        JRDesignBand detail = buildDetail(taras.get(0));
        int y = 160;
        JRDesignStaticText textStatic = staticText(Unit.px, 0, y, parameter.get(GlobalParameter.P_EMPRESA_NOMBRE));
        textStatic.setWidth(200);
        detail.addElement(textStatic);

        y= newLine(y);
        String company = "" +
                parameter.get(GlobalParameter.P_EMPRESA_DIR) + " - " +
                parameter.get(GlobalParameter.P_EMPRESA_LOC) + " - " +
                parameter.get(GlobalParameter.P_EMPRESA_PROV)
                ;
        textStatic = staticText(Unit.px, 0, y, company);
        textStatic.setWidth(200);
        detail.addElement(textStatic);
        y = newLine(y);

        textStatic = staticText(Unit.px, 0,y, "Tel:" + parameter.get(GlobalParameter.P_EMPRESA_TEL));
        textStatic.setWidth(200);
        detail.addElement(textStatic);

        ((JRDesignSection) jasperDesign.getDetailSection()).addBand(detail);
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
        return y + 15;
    }

    private JRDesignBand buildTitle() throws JRException {
        JRDesignBand band = new JRDesignBand();
        band.setHeight(20);
        JRDesignStyle titleStyle= style("titleStyle", "Arial", 14);
        titleStyle.setHorizontalTextAlign(HorizontalTextAlignEnum.CENTER);
        JRDesignStaticText staticText = staticText(Unit.px, titleStyle, 0, 0, "Comprobante de Pesaje");
        staticText.setWidth(jasperDesign.getPageWidth());
        band.addElement(staticText);
        titleStyle.setBold(true);
        jasperDesign.addStyle(titleStyle);
        return band;
    }

    private JRDesignBand buildHeader(){
        JRDesignBand band = new JRDesignBand();
        band.setHeight(50);
        int y = 0;
        band.addElement(staticText(Unit.px, 0,y,  parameter.get(GlobalParameter.P_EMPRESA_NOMBRE_BAL)));
        band.addElement(staticText(Unit.px, 400,y,  parameter.get(GlobalParameter.P_REPORT_COPY)));

        y = newLine(y);
        String datosCliente = parameter.get(GlobalParameter.P_EMPRESA_DIR_BAL) + " - " +
                parameter.get(GlobalParameter.P_EMPRESA_LOC_BAL) + " - " +
                parameter.get(GlobalParameter.P_EMPRESA_PROV_BAL);
        band.addElement(staticText(Unit.px, 0,y, datosCliente));
        y =newLine(y);
        JRDesignStaticText textStatic = staticText(Unit.px, 0,y, "Tel:" + parameter.get(GlobalParameter.P_EMPRESA_TEL_BAL));
        textStatic.setWidth(200);
        band.addElement(textStatic);
        return band;
    }

    private JRDesignBand buildDetail(Tare t) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");

        JRDesignBand band = new JRDesignBand();
        band.setHeight((new Double(210)).intValue());
        int col1 = 0;
        int col1Value = 80;
        int col2 = 210;
        int col2Value = col2 + 55;
        int col3 = 360;
        int col3Value = col3 + 82;

        int y = 0;
        JRDesignStaticText textStatic = null;
        band.addElement(staticText(Unit.px, col1,y, "Entrada: "));
        band.addElement(staticText(Unit.px, col1Value,y, dateFormat.format(t.getFechaEntrada())));

        band.addElement(staticText(Unit.px, col2,y, "Operador: "));
        band.addElement(staticText(Unit.px, col2Value,y, User.getUsuarioLogeado()));

        band.addElement(staticText(Unit.px, col3,y, "TICKET: "));
        band.addElement(staticText(Unit.px, col3Value,y, t.getTransaccion()));

        y = newLine(y);
        band.addElement(staticText(Unit.px, col1,y, "Salida: "));
        if(t.getFechaSalida() != null)
            band.addElement(staticText(Unit.px, col1Value,y, dateFormat.format(t.getFechaSalida())));

        band.addElement(staticText(Unit.px, col2,y, "Balanza: "));
        band.addElement(staticText(Unit.px, col2Value,y, t.getBalanza()));

        band.addElement(staticText(Unit.px, col3,y, "Conductor: "));
        band.addElement(staticText(Unit.px, col3Value,y, t.getConductor()));

        y= newLine(y);
        band.addElement(staticText(Unit.px, col1,y, "Producto:"));
        if(t.getProducto() != null)
            band.addElement(staticText(Unit.px, col1Value,y, t.getProducto().getNombre()));

        band.addElement(staticText(Unit.px, col3,y, "DNI: "));
        band.addElement(staticText(Unit.px, col3Value,y, t.getNumDoc()));

        y= newLine(y);
        band.addElement(staticText(Unit.px, col1,y, "Cliente:"));
        if(t.getCliente() != null)
            band.addElement(staticText(Unit.px, col1Value,y, t.getCliente().getNombre()));

        band.addElement(staticText(Unit.px, col3,y, "Nac.: "));
        band.addElement(staticText(Unit.px, col3Value,y, t.getNacionalidad()));

        y= newLine(y);
        band.addElement(staticText(Unit.px, col1,y, "Transporte:"));
        if(t.getTransporte() != null)
            band.addElement(staticText(Unit.px, col1Value,y, t.getTransporte().getNombre()));

        band.addElement(staticText(Unit.px, col3,y, "Patente: "));
        band.addElement(staticText(Unit.px, col3Value,y, t.getPatente().getCodigo()));

        y= newLine(y);
        band.addElement(staticText(Unit.px, col1,y, "Procedencia:"));
        if(t.getProcedencias() != null)
            band.addElement(staticText(Unit.px, col1Value,y, t.getProcedencias().getNombre()));
        band.addElement(staticText(Unit.px, col3,y, "Acoplado: "));
        band.addElement(staticText(Unit.px, col3Value,y, t.getPatenteAceptado()));

        y= newLine(y);
        y= newLine(y);
        band.addElement(staticText(Unit.px, col1,y, "Comprobante: "));
        band.addElement(staticText(Unit.px, col1Value,y, t.getComprobanteNun1()));

        band.addElement(staticText(Unit.px, col3,y, "PESO ENTRADA: "));
        band.addElement(staticText(Unit.px, col3Value,y, t.getPesoEntrada()));

        y= newLine(y);
        band.addElement(staticText(Unit.px, col1,y, "Observación: "));
        textStatic= staticText(Unit.px, col1Value,y, t.getObservacion());
        textStatic.setWidth(150);
        textStatic.setHeight(40);
        band.addElement(textStatic);

        band.addElement(staticText(Unit.px, col3,y, "PESO SALIDA: "));
        band.addElement(staticText(Unit.px, col3Value,y, t.getPesoSalida()));

        y= newLine(y);
        band.addElement(staticText(Unit.px, col3,y, "PESO NETO: "));
        band.addElement(staticText(Unit.px, col3Value,y, t.getPesoNeto()));
        return band;
    }
}
