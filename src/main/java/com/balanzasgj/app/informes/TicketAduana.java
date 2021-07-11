package com.balanzasgj.app.informes;

import ar.com.fdvs.dj.domain.DynamicReport;
import com.balanzasgj.app.model.GlobalParameter;
import com.balanzasgj.app.model.Tare;
import com.balanzasgj.app.model.User;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.design.*;
import net.sf.jasperreports.engine.type.HorizontalTextAlignEnum;
import net.sf.jasperreports.engine.type.ModeEnum;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class TicketAduana extends ReportBase{
    private int col1 = 5;
    private int col1Value = col1 + 100;
    private int col2;
    private int col2Value;

    private final SimpleDateFormat dateFormat;
    private JRDesignStaticText textStatic;
    private final JRDesignStyle subTitleStyle;
    private final JRDesignStyle textStyle;
    private final Tare tare;
    public TicketAduana(PAGE_FORMAT page, Map<String, Object> params, List<Tare> taras) {
        super(page, params);
        this.tare =taras.get(0);
        this.textStyle= style("textStyle", "Arial", 10);
        setTextStyle(textStyle);
        this.dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        this.subTitleStyle = style("subTitleStyle", "Arial", 12);
        this.subTitleStyle.setBold(true);

    }

    public void build() throws Exception {
        logger.info("Print: " + TicketPrinter.class.getName());
        this.col2 = this.jasperDesign.getPageWidth() / 2;
        this.col2Value = col2 + 100;
        jasperDesign.setName("TicketAduana");
        int margin = 14;
        jasperDesign.setLeftMargin(margin);
        jasperDesign.setTopMargin(margin);
        jasperDesign.setBottomMargin(margin);

        jasperDesign.addStyle(subTitleStyle);
        jasperDesign.addStyle(textStyle);
        jasperDesign.setTitle(buildTitle());
        jasperDesign.setPageHeader(buildHeader());
        ((JRDesignSection) jasperDesign.getDetailSection()).addBand(buildDetail(tare));
        jasperDesign.setLastPageFooter(null);
        jasperDesign.setPageFooter(buildFooter());
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
        JRDesignStaticText staticText = staticText(Unit.px, titleStyle, 0, 0, "Razon social de la Firma");
        staticText.setWidth(jasperDesign.getPageWidth());
        band.addElement(staticText);
        titleStyle.setBold(true);
        jasperDesign.addStyle(titleStyle);
        return band;
    }

    private JRDesignBand buildHeader(){
        JRDesignBand band = new JRDesignBand();

        final JRDesignRectangle rectangle = new JRDesignRectangle();
        rectangle.setRadius(5);
        rectangle.setMode(ModeEnum.TRANSPARENT);
        rectangle.setWidth(jasperDesign.getColumnWidth());
        band.addElement(rectangle);

        int y = 4;
        band.addElement(staticText(Unit.px, col1,y, "Empresa: " + parameter.get(GlobalParameter.P_EMPRESA_NOMBRE_BAL)));
        band.addElement(staticText(Unit.px, col2Value,y,  parameter.get(GlobalParameter.P_REPORT_COPY)));

        y = newLine(y);
        String datosCliente = "Dirección: " + parameter.get(GlobalParameter.P_EMPRESA_DIR_BAL) + " - " +
                parameter.get(GlobalParameter.P_EMPRESA_LOC_BAL) + " - " +
                parameter.get(GlobalParameter.P_EMPRESA_PROV_BAL);
        band.addElement(staticText(Unit.px, col1,y, datosCliente));
        y =newLine(y);
        textStatic = staticText(Unit.px, col1,y, "Tel:" + parameter.get(GlobalParameter.P_EMPRESA_TEL_BAL));
        textStatic.setWidth(200);
        band.addElement(textStatic);

        textStatic = staticText(Unit.px, col2Value ,y,  "TICKET DE EMPRESA: Nº " + tare.getTransaccion());
        textStatic.setWidth(150);
        band.addElement(textStatic);

        rectangle.setHeight(y + 14);
        band.setHeight(rectangle.getHeight() + 8);
        logger.info("PAGE HEADER HEIGHT: " + (rectangle.getHeight() + 8));
        return band;
    }

    private JRDesignBand buildDetail(Tare t) {
        JRDesignBand band = new JRDesignBand();
        int y = 2;
        y = buildRectDataAduana(band, y);
        y = buildRectDataTare(band, y);
        y = buildRectContBulto(band, y);
        y = buildRectObservation(band, y);
        band.setHeight((new Double(y + 50 )).intValue());
        return band;
    }

    private int buildRectDataAduana(JRDesignBand band, int y){
        final SimpleDateFormat dateFormatDate = new SimpleDateFormat("dd/MM/yyyy");
        final SimpleDateFormat dateFormatHour = new SimpleDateFormat("HH:mm");
        final JRDesignRectangle rectangle = new JRDesignRectangle();
        rectangle.setX(0);
        rectangle.setY(0);
        rectangle.setRadius(5);
        rectangle.setMode(ModeEnum.TRANSPARENT);
        rectangle.setWidth(jasperDesign.getColumnWidth());
        band.addElement(rectangle);

        band.addElement(staticText(Unit.px, col1,y, "Fecha: "));
        band.addElement(staticText(Unit.px, col1Value,y, dateFormatDate.format(new Date())));
        band.addElement(staticText(Unit.px, col2,y, "Hora: "));
        band.addElement(staticText(Unit.px, col2Value, y, dateFormatHour.format(new Date())));

        y = newLine(y) +2;
        textStatic = staticText(Unit.px, subTitleStyle, col1 ,y ,"Exportador / Importador");
        textStatic.setWidth(jasperDesign.getColumnWidth());
        band.addElement(textStatic);
        y = buildRazonSocial(band, y, "Razon Social: ", tare.getImpExp() == null ? "" : tare.getImpExp().getNombre(), "CUIT: ", tare.getImpExp().getCuit());

        y = newLine(y) +2;
        textStatic = staticText(Unit.px, subTitleStyle, col1,y, "Destinación / Operación Aduana");
        textStatic.setWidth(jasperDesign.getColumnWidth());
        band.addElement(textStatic);
        y = buildRazonSocial(band, y, "Número: ", tare.getDestino(), "Mercaderia: ", tare.getProducto() == null ? "": tare.getProducto().getNombre());
        y = buildRazonSocial(band, y, "Código Aduana: ", parameter.get(GlobalParameter.A_CODIGO_ADUANA).toString(), "Código LOT: ",  parameter.get(GlobalParameter.A_CODIGO_LOG).toString());
        rectangle.setHeight(y + 15);
        return y;
    }

    private int buildRectDataTare(JRDesignBand band, int y){
        y = newLine(y) + 5;
        final JRDesignRectangle rectangle = new JRDesignRectangle();
        rectangle.setX(0);
        rectangle.setY(y);
        rectangle.setRadius(5);
        rectangle.setMode(ModeEnum.TRANSPARENT);
        rectangle.setWidth(jasperDesign.getColumnWidth());
        band.addElement(rectangle);

        y = y + 2;
        textStatic = staticText(Unit.px, subTitleStyle, col1 ,y ,"Transportista");
        textStatic.setWidth(jasperDesign.getColumnWidth());
        band.addElement(textStatic);
        y = buildRazonSocial(band, y, "Razon Social: ", tare.getTransporte() == null ? "" : tare.getTransporte().getNombre(), "CUIT: ", tare.getTransporte() == null ? "" : tare.getTransporte() == null ? "": tare.getTransporte().getCuit());

        y = newLine(y) +2;
        textStatic = staticText(Unit.px, subTitleStyle, col1,y, "ATA");
        textStatic.setWidth(jasperDesign.getColumnWidth());
        band.addElement(textStatic);
        y = buildRazonSocial(band, y, "Razon Social: ", tare.getAta() == null ? "" : tare.getAta().getNombre(), "CUIT: ", tare.getTransporte() == null ? "" : tare.getAta() == null ? "": tare.getAta().getCuit());

        y = newLine(y) +2;
        textStatic = staticText(Unit.px, subTitleStyle, col1,y, "Chofer");
        textStatic.setWidth(jasperDesign.getColumnWidth());
        band.addElement(textStatic);
        y = buildRazonSocial(band, y, "Apellido y Nombre: ", tare.getConductor(), "Nacionalidad: ", tare.getNacionalidad());
        y = buildRazonSocial(band, y, "Nº Documento: ", tare.getNumDoc(), "Tipo: ", tare.getTipoDoc()== null? "DNI" : tare.getTipoDoc());

        y = newLine(y) +2;
        textStatic = staticText(Unit.px, subTitleStyle, col1,y, "Vehiculo");
        textStatic.setWidth(jasperDesign.getColumnWidth());
        band.addElement(textStatic);
        y = buildRazonSocial(band, y, "Patente Tractor: ", tare.getPatente().getCodigo(), "País Destino / : ", tare.getProcedencias() == null ? "": tare.getProcedencias().getNombre());
        y = buildRazonSocial(band, y, "Patente Acoplado: ", tare.getPatenteAceptado(), "Procedencia", "");
        y = buildRazonSocial(band, y, "Entrada: ", dateFormat.format(tare.getFechaEntrada()), "Salida: ", tare.getFechaSalida() == null ? "" : dateFormat.format(tare.getFechaSalida()));

        y = newLine(y) +2;
        textStatic = staticText(Unit.px, subTitleStyle, col1,y, "Pesada");
        textStatic.setWidth(jasperDesign.getColumnWidth());
        band.addElement(textStatic);
        y = buildRazonSocial(band, y, "Operador: ", User.getUsuarioLogeado(), "", "");
        y = buildRazonSocial(band, y, "PESO ENTRADA: ", tare.getPesoEntrada().toString(), "Balanza: ", tare.getBalanza());
        y = buildRazonSocial(band, y, "PESO SALIDA: ", tare.getPesoSalida() == null ? "": tare.getPesoSalida().toString(), "Nº Certificado Habilitación: ", parameter.get(GlobalParameter.A_CERTIFICADO).toString());
        y = buildRazonSocial(band, y, "PESO NETO: ", tare.getPesoNeto() == null ? "": tare.getPesoNeto().toString(), "Vencimiento: ", parameter.get(GlobalParameter.A_VENCIMIENTO).toString());

        rectangle.setHeight(y - 80);
        return y;
    }

    private int buildRectContBulto(JRDesignBand band, int y){
        y = newLine(y) + 10;
        final JRDesignRectangle rectContainer = new JRDesignRectangle();
        rectContainer.setX(0);
        rectContainer.setY(y);
        rectContainer.setRadius(5);
        rectContainer.setMode(ModeEnum.TRANSPARENT);
        rectContainer.setWidth((jasperDesign.getColumnWidth() / 2) - 5);
        rectContainer.setHeight(50);
        band.addElement(rectContainer);

        int xBulk = (jasperDesign.getColumnWidth() / 2) + 5;
        final JRDesignRectangle rectBulk = new JRDesignRectangle();
        rectBulk.setX(xBulk);
        rectBulk.setY(y);
        rectBulk.setRadius(5);
        rectBulk.setMode(ModeEnum.TRANSPARENT);
        rectBulk.setWidth((jasperDesign.getColumnWidth() / 2) - 5);
        rectBulk.setHeight(50);
        band.addElement(rectBulk);

        y = y + 2;
        textStatic = staticText(Unit.px, subTitleStyle, col1,y, "Contenedor");
        textStatic.setWidth(jasperDesign.getColumnWidth());
        band.addElement(textStatic);
        textStatic = staticText(Unit.px, subTitleStyle, col2,y, "Bulto");
        textStatic.setWidth(jasperDesign.getColumnWidth());
        band.addElement(textStatic);

        y = buildRazonSocial(band, y, "Contenedor: ", tare.getContenedor(), "Identificador: ", tare.getMercaderia());
        y = buildRazonSocial(band, y, "Tara Contenedor: ", tare.getContenedorNum(), "Manifiesto: ", tare.getManifiesto());
        return y;
    }

    private int buildRectObservation(JRDesignBand band, int y){
        y = newLine(y) + 10;
        final JRDesignRectangle rectangle = new JRDesignRectangle();
        rectangle.setX(0);
        rectangle.setY(y);
        rectangle.setRadius(5);
        rectangle.setMode(ModeEnum.TRANSPARENT);
        rectangle.setWidth(jasperDesign.getColumnWidth());
        band.addElement(rectangle);

        band.addElement(staticText(Unit.px, col1, y, "Observación: "));
        textStatic = staticText(Unit.px, col1Value, y, tare.getObservacion());
        textStatic.setWidth(jasperDesign.getColumnWidth());
        band.addElement(textStatic);
        y = newLine(y);
        band.addElement(staticText(Unit.px, col1, y, "Obs. Aduana: "));
        textStatic = staticText(Unit.px, col1Value, y, tare.getObservacionAduana());
        textStatic.setWidth(jasperDesign.getColumnWidth());
        band.addElement(textStatic);
        rectangle.setHeight(30);
        return y;
    }

    private int buildRazonSocial(JRDesignBand band, int y, String label1, String value1, String label2, String value2){
        y = newLine(y);
        textStatic = staticText(Unit.px, col1, y, label1);
        textStatic.setWidth(100);
        band.addElement(textStatic);
        band.addElement(staticText(Unit.px, col1Value, y, value1 == null ? "" : value1));

        textStatic = staticText(Unit.px, col2, y, label2);
        textStatic.setWidth(100);
        band.addElement(textStatic);

        band.addElement(staticText(Unit.px, col2Value, y, value2 == null ? "" : value2));
        return y;
    }

    private JRDesignBand buildFooter(){
        JRDesignBand band = new JRDesignBand();
        int y = 0;
        textStatic = staticText(Unit.px, 0, y, parameter.get(GlobalParameter.P_EMPRESA_NOMBRE));
        textStatic.setWidth(200);
        band.addElement(textStatic);

        y= newLine(y);
        String company = "" +
                parameter.get(GlobalParameter.P_EMPRESA_DIR) + " - " +
                parameter.get(GlobalParameter.P_EMPRESA_LOC) + " - " +
                parameter.get(GlobalParameter.P_EMPRESA_PROV)
                ;
        textStatic = staticText(Unit.px, 0, y, company);
        textStatic.setWidth(200);
        band.addElement(textStatic);
        y = newLine(y);

        textStatic = staticText(Unit.px, 0,y, "Tel:" + parameter.get(GlobalParameter.P_EMPRESA_TEL));
        textStatic.setWidth(200);
        band.addElement(textStatic);
        band.setHeight(y +20);
        logger.info("PAGE FOOTER HEIGHT: " + (y +20));
        return band;
    }
}
